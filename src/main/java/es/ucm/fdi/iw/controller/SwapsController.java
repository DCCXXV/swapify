package es.ucm.fdi.iw.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import jakarta.servlet.http.HttpSession;
import jakarta.transaction.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import es.ucm.fdi.iw.model.Message;
import es.ucm.fdi.iw.model.Review;
import es.ucm.fdi.iw.model.Skill;
import es.ucm.fdi.iw.model.Swap;
import es.ucm.fdi.iw.model.User;
import es.ucm.fdi.iw.service.MessageService;
import es.ucm.fdi.iw.service.SwapService;
import es.ucm.fdi.iw.service.UserService;
import es.ucm.fdi.iw.service.ReviewService;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 *  Site administration.
 *
 *  Access to this end-point is authenticated - see SecurityConfig
 */
@Controller
@RequestMapping("swaps")
public class SwapsController {

    @ModelAttribute
    public void populateModel(HttpSession session, Model model) {        
        for (String name : new String[] {"u", "url", "ws"}) {
            model.addAttribute(name, session.getAttribute(name));
        }
    }

    @Data
    @NoArgsConstructor
    public static class TChatMessage {
        private String text;
    }

    private final SwapService swapService;
    private final UserService userService;
    private final MessageService messageService;
    private final ReviewService reviewService;
    private final SimpMessagingTemplate messagingTemplate;

    public SwapsController(SwapService swapService, UserService userService, MessageService messageService, 
                          ReviewService reviewService, SimpMessagingTemplate messagingTemplate) {
        this.swapService = swapService;
        this.userService = userService;
        this.messageService = messageService;
        this.reviewService = reviewService;
        this.messagingTemplate = messagingTemplate;
    }

    private static final Logger log = LogManager.getLogger(SwapsController.class);

    @GetMapping
    public String index(Model model, HttpSession session) {
        log.debug("Loading initial swaps page");
        model.addAttribute("actual", "swaps"); 

        User.Transfer me = userService.getUsersByID(((User)session.getAttribute("u")).getId()).toTransfer();
        model.addAttribute("currentUser", me);

        List<Swap.Transfer> activeSwaps = swapService.getActiveByUsername(me.getUsername());
        model.addAttribute("activeSwaps", activeSwaps);

        List<Swap.Transfer> finishedSwaps = swapService.getFinishedByUsername(me.getUsername());
        model.addAttribute("finishedSwaps", finishedSwaps);

        List<Swap.Transfer> pendingSwaps = swapService.getPendingByUsername(me.getUsername());
        model.addAttribute("pendingSwaps", pendingSwaps);

        return "swaps";
    }

    @Value("${app.websocket.endpoint:/ws}")
    private String webSocketEndpointUrl;
    @GetMapping("/{id}")
    public String swapChat(@PathVariable Long id, Model model, HttpSession session) {
        log.debug("intentando sacar información del chat con id: {}", id);

        Swap.Transfer swap = swapService.getById(id);
        User me = userService.getUsersByID(((User)session.getAttribute("u")).getId());
        List<Message.Transfer> messages = messageService.findMessagesForSwap(id);

        boolean reviewSubmitted = swapService.isReviewSubmitted(id, me.getId());
        model.addAttribute("reviewSubmitted", reviewSubmitted);

        model.addAttribute("selectedSwap", swap);
        model.addAttribute("currentUser", me);
        model.addAttribute("messages", messages);
        model.addAttribute("ws", webSocketEndpointUrl);

        return "swaps :: chatFragment";
    }

    @GetMapping("/{id}/terminate")
    @ResponseBody
    public Map<String, Object> terminateSwap(@PathVariable Long id, Model model, HttpSession session) {
        log.debug("intentando terminar el chat con id: {}", id);

        Swap.Transfer swap = swapService.getById(id);
        User me = userService.getUsersByID(((User)session.getAttribute("u")).getId());
        List<Message.Transfer> messages = messageService.findMessagesForSwap(id);

        Map<String, Object> response = new HashMap<>();
        response.put("selectedSwap", swap);
        response.put("currentUser", me);
        response.put("messages", messages);
        response.put("ws", webSocketEndpointUrl);
        log.debug("Añadiendo al modelo - archived: {}", response.get("archived"));
        model.addAttribute("archived", true);

        return response;
    }


    @MessageMapping("/swap/{swapId}/sendMessage")
    public void handleSendMessage(@DestinationVariable String swapId,
                                  @Payload TChatMessage tChatMessage,
                                  Authentication authentication) {

        try {
            long swapIdLong = Long.parseLong(swapId);
            String username = authentication.getName();

            Message savedMessage = messageService.saveNewMessage(
                tChatMessage.getText(), username, swapIdLong);

            if (savedMessage != null) {
                messagingTemplate.convertAndSend("/topic/swap/" + swapId, savedMessage.toTransfer());
                System.out.println("Mensaje guardado y enviado a swap " + swapId + " por " + username);
            } else {
                 System.err.println("Error al guardar mensaje para swap " + swapId);
            }
        } catch (NumberFormatException e) {
             System.err.println("Swap ID inválido recibido en WebSocket: " + swapId);
        } catch (Exception e) {
             System.err.println("Error procesando mensaje WebSocket para swap " + swapId + ": " + e.getMessage());
             e.printStackTrace();
        }
    }

    @PostMapping("/create")
    @Transactional
    @ResponseBody
    public String createSwap(@RequestBody SwapRequest swapRequest) {
        log.info("Creando swap entre users {} y {}", swapRequest.getUserA(), swapRequest.getUserB());
        
        try {
            if (swapRequest.getUserA() <= 0 || swapRequest.getUserB() <= 0 ||
                swapRequest.getSkillA() == null || swapRequest.getSkillB() == null) {
                return "{\"status\":\"error\",\"message\":\"IDs inválidos proporcionados\"}";
            }

            Swap swap = new Swap();
            
            User userA = userService.getUsersByID(swapRequest.getUserA());
            User userB = userService.getUsersByID(swapRequest.getUserB());
            
            if (userA == null || userB == null) {
                log.error("Fallo al crear swap: usuario no encontrado");
                return "{\"status\":\"error\",\"message\":\"Usuario no encontrado\"}";
            }
            
            swap.setUserA(userA);
            swap.setUserB(userB);
            
            Skill skillA = swapService.getSkillByName(swapRequest.getSkillA());
            Skill skillB = swapService.getSkillByName(swapRequest.getSkillB());
            
            if (skillA == null || skillB == null) {
                log.error("Fallo al crear swap: skill no encontrada");
                return "{\"status\":\"error\",\"message\":\"Habilidad no encontrada\"}";
            }
            
            swap.setSkillA(skillA);
            swap.setSkillB(skillB);
            
            Swap.Transfer savedSwap = swapService.saveSwap(swap);
            log.info("Creado el swap con ID: {}", savedSwap.getId());
            
            return "{\"status\":\"success\",\"message\":\"Swap creado con éxito\",\"id\":" + savedSwap.getId() + "}";
        } catch (Exception e) {
            log.error("Error al crear swap", e);
            return "{\"status\":\"error\",\"message\":\"Error al crear el swap: " + e.getMessage() + "\"}";
        }
    }

    @PostMapping("/{id}/submitReview")
    @Transactional
    @ResponseBody
    public String submitReview(@PathVariable Long id, @RequestBody ReviewRequest reviewRequest, HttpSession session) {
        try {
            User currentUser = (User)session.getAttribute("u");
            if (currentUser == null) {
                return "{\"status\":\"error\",\"message\":\"Usuario no autenticado\"}";
            }

            Swap.Transfer transfer = swapService.getById(id);
            Swap swap = new Swap();
            swap.setId(transfer.getId());
            swap.setUserA(transfer.getUserA());
            swap.setUserB(transfer.getUserB());
            swap.setSkillA(transfer.getSkillA());
            swap.setSkillB(transfer.getSkillB());
            swap.setSwapStatus(Swap.Status.valueOf(transfer.getSwapStatus()));

            if (swap == null || swap.getSwapStatus() != Swap.Status.FINISHED) {
                return "{\"status\":\"error\",\"message\":\"No se puede escribir una reseña para un swap que no está finalizado\"}";
            }

            boolean isUserA = swap.getUserA().getId() == currentUser.getId();
            boolean isUserB = swap.getUserB().getId() == currentUser.getId();

            if (!isUserA && !isUserB) {
                return "{\"status\":\"error\",\"message\":\"No puedes escribir una reseña para un swap en el que no participaste\"}";
            }

            if ((isUserA && swap.getReviewA() != null) || (isUserB && swap.getReviewB() != null)) {
                return "{\"status\":\"error\",\"message\":\"Ya has enviado una reseña para este swap\"}";
            }

            Review review = new Review();
            review.setText(reviewRequest.getText());
            review.setRating(reviewRequest.getRating());
            review.setSwapId(id);
            review.setUserA(transfer.getUserA());
            review.setUserB(transfer.getUserB());
            review.setSkillA(transfer.getSkillA());
            review.setSkillB(transfer.getSkillB());

            reviewService.saveReview(review);

            if (isUserA) {
                swap.setReviewA(review);
            } else {
                swap.setReviewB(review);
            }

            swapService.saveSwap(swap);

            return "{\"status\":\"success\",\"message\":\"Reseña enviada con éxito\"}";
        } catch (Exception e) {
            return "{\"status\":\"error\",\"message\":\"Error al enviar la reseña: " + e.getMessage() + "\"}";
        }
    }

    @PostMapping("/{id}/finishSwap")
    public String finishSwap(@PathVariable long id) {
        Swap.Transfer swap = swapService.updateStatus(id, Swap.Status.FINISHED);
        return "redirect:/swaps";
    }

        @PostMapping("/{id}/acceptSwap")
    public String acceptSwap(@PathVariable long id) {
        Swap.Transfer swap = swapService.updateStatus(id, Swap.Status.ACTIVE);
        return "redirect:/swaps";
    }

    @GetMapping("/info")
    public String info(Model model) {
        return "swapinfo";
    }

    @GetMapping("/panel")
    public String panel(Model model) {
        return "swapspanel";
    }

    @Getter @Setter
    public static class SwapRequest {
        private Long userA;
        private Long userB;
        private String skillA;
        private String skillB;
        private String swapDate;
    }

    @Getter @Setter
    public static class ReviewRequest {
        private String text;
        private double rating;
    }
}
