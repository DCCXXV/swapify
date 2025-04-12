package es.ucm.fdi.iw.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.ucm.fdi.iw.model.Message;
import es.ucm.fdi.iw.model.Swap;
import es.ucm.fdi.iw.model.User;
import es.ucm.fdi.iw.repository.MessageRepository;
import es.ucm.fdi.iw.repository.SwapRepository;
import es.ucm.fdi.iw.repository.UserRepository;
import jakarta.transaction.Transactional;

@Service
public class MessageService {
    
    private static final Logger log = LogManager.getLogger(MessageService.class);

    @Autowired
    private MessageRepository messageRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private SwapRepository swapRepository;

    public MessageService(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    public List<Message.Transfer> findMessagesForSwap(Long swapId) {
        return messageRepository.findBySwapId(swapId)
            .stream()
            .map(Message::toTransfer)
            .toList();
    }

    public Message.Transfer save(Message message) {
        return messageRepository.save(message).toTransfer();
    }

    @Transactional
    public Message saveNewMessage(String text, String senderUsername, Long swapId) {
        log.info("Attempting to save message. Text: '{}', Sender: '{}', SwapID: {}", text, senderUsername, swapId); // LOG 1

        if (text == null || text.isBlank()) {
            log.warn("Save failed: Message text is blank."); // LOG 2
            return null;
        }

        // 1. Buscar remitente
        User sender = userRepository.findByUsername(senderUsername);
        if (sender == null) {
            log.error("Save failed: Sender user '{}' not found.", senderUsername); // LOG 3
            return null;
        }
        log.info("Sender found: {}", sender.getUsername()); // LOG 4

        // 2. Buscar Swap
        Optional<Swap> swapOpt = swapRepository.findById(swapId);
        if (swapOpt.isEmpty()) {
            log.error("Save failed: Swap with ID {} not found.", swapId); // LOG 5
            return null;
        }
        Swap swap = swapOpt.get();
        log.info("Swap found: ID {}", swap.getId()); // LOG 6

        // 3. Determinar destinatario
        User recipient = null;
        User userA = swap.getUserA();
        User userB = swap.getUserB();

        if (userA == null || userB == null) {
             log.error("Save failed: Swap {} is missing one or both participants.", swapId); // LOG 7
             return null;
        }

        if (userA.getId() == sender.getId()) {
            recipient = userB;
        } else if (userB.getId() == sender.getId()) {
            recipient = userA;
        } else {
            log.error("Save failed: Sender {} is not a participant in Swap {}.", senderUsername, swapId); // LOG 8
            return null;
        }
        log.info("Recipient determined: {}", recipient.getUsername()); // LOG 9

        // 4. Crear entidad Message
        Message newMessage = new Message();
        newMessage.setText(text);
        newMessage.setSender(sender);
        newMessage.setRecipient(recipient);
        newMessage.setSwap(swap);
        newMessage.setDateSent(LocalDateTime.now());
        newMessage.setDateRead(null);
        newMessage.setReportFlag(false);
        log.info("Message entity created. Attempting save..."); // LOG 10

        // 5. Guardar (¡CON TRY-CATCH!)
        try {
            Message savedMessage = messageRepository.save(newMessage);
            log.info("Message saved successfully! ID: {}", savedMessage.getId()); // LOG 11
            return savedMessage; // ¡Éxito!
        } catch (Exception e) {
            // ***** ¡ESTE LOG ES CRUCIAL SI HAY UN ERROR AL GUARDAR! *****
            log.error("!!! DATABASE SAVE FAILED for Swap {} !!!", swapId, e); // LOG 12 - Imprime la excepción completa
            // e.printStackTrace(); // Descomenta si necesitas el stack trace completo en la consola
            return null; // Indica fallo al controlador
        }
    }
}
