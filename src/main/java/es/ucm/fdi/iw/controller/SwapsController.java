package es.ucm.fdi.iw.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
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
import org.springframework.web.server.ResponseStatusException;

import jakarta.servlet.http.HttpSession;
import jakarta.transaction.Transactional;

import java.util.ArrayList;
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

        List<Long> swapIds = swapService.getAllByUsername(me.getUsername()).stream().map(Swap.Transfer::getId).toList();
        if (swapIds == null) swapIds = new ArrayList<>();
        model.addAttribute("allMySwapIds", swapIds);

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

        if (!swap.getUserA().equals(me) &&
            !swap.getUserB().equals(me)) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "No autorizado");
        }

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

    @PostMapping("/{id}/sendMessage")
    @ResponseBody
    public ResponseEntity<Map<String, Object>> sendMessage(
            @PathVariable Long id,
            @RequestBody TChatMessage tChatMessage,
            Authentication auth) {
        String username = auth.getName();
        Swap swap = swapService.getSwapByID(id);

        if (!swap.getUserA().getUsername().equals(username) &&
            !swap.getUserB().getUsername().equals(username)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN)
                    .body(Map.of("status", "error", "message", "No autorizado"));
        }

        Message savedMessage = messageService.saveNewMessage(tChatMessage.getText(), username, id);

        if (savedMessage != null) {
            messagingTemplate.convertAndSend("/topic/swap/" + id, savedMessage.toTransfer());
            return ResponseEntity.ok(Map.of("status", "success"));
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of("status", "error", "message", "No se pudo guardar el mensaje"));
        }
    }

    @PostMapping("/create")
    @Transactional
    @ResponseBody
    public ResponseEntity<Map<String, Object>> createSwap(@RequestBody SwapRequest swapRequest) {
        Map<String, Object> response = new HashMap<>();
        try {
            if (swapRequest.getUserA() == null || swapRequest.getUserB() == null ||
                swapRequest.getUserA() <= 0 || swapRequest.getUserB() <= 0 ||
                swapRequest.getSkillA() == null || swapRequest.getSkillB() == null) {
                response.put("status", "error");
                response.put("message", "IDs inválidos proporcionados");
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
            }

            Swap swap = new Swap();

            User userA = userService.getUsersByID(swapRequest.getUserA());
            User userB = userService.getUsersByID(swapRequest.getUserB());

            if (userA == null || userB == null) {
                log.error("Fallo al crear swap: usuario no encontrado");
                response.put("status", "error");
                response.put("message", "Usuario no encontrado");
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
            }

            swap.setUserA(userA);
            swap.setUserB(userB);

            Skill skillA = swapService.getSkillByName(swapRequest.getSkillA());
            Skill skillB = swapService.getSkillByName(swapRequest.getSkillB());

            if (skillA == null || skillB == null) {
                log.error("Fallo al crear swap: skill no encontrada");
                response.put("status", "error");
                response.put("message", "Habilidad no encontrada");
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
            }

            swap.setSkillA(skillA);
            swap.setSkillB(skillB);

            Swap.Transfer savedSwap = swapService.saveSwap(swap);
            log.info("Creado el swap con ID: {}", savedSwap.getId());

            response.put("status", "success");
            response.put("message", "Swap creado con éxito");
            response.put("id", savedSwap.getId());
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            log.error("Error al crear swap", e);
            response.put("status", "error");
            response.put("message", "Error al crear el swap: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    @PostMapping("/{id}/submitReview")
    @Transactional
    @ResponseBody
    public ResponseEntity<Map<String, Object>> submitReview(
            @PathVariable Long id,
            @RequestBody ReviewRequest reviewRequest,
            HttpSession session) {
        Map<String, Object> response = new HashMap<>();
        try {
            User currentUser = (User)session.getAttribute("u");
            if (currentUser == null) {
                response.put("status", "error");
                response.put("message", "Usuario no autenticado");
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
            }

            Swap swap = swapService.getSwapByID(id);

            if (swap.getSwapStatus() != Swap.Status.FINISHED) {
                response.put("status", "error");
                response.put("message", "No se puede escribir una reseña para un swap que no está finalizado");
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
            }

            boolean isUserA = swap.getUserA().getId() == currentUser.getId();
            boolean isUserB = swap.getUserB().getId() == currentUser.getId();

            if (!isUserA && !isUserB) {
                response.put("status", "error");
                response.put("message", "No puedes escribir una reseña para un swap en el que no participaste");
                return ResponseEntity.status(HttpStatus.FORBIDDEN).body(response);
            }

            if ((isUserA && swap.getReviewA() != null) || (isUserB && swap.getReviewB() != null)) {
                response.put("status", "error");
                response.put("message", "Ya has enviado una reseña para este swap");
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
            }

            Review review = new Review();
            review.setText(reviewRequest.getText());
            review.setRating(reviewRequest.getRating());
            review.setSwapId(id);

            if (isUserA) {
                review.setUserA(currentUser);  // quien escribe la reseña
                review.setUserB(swap.getUserB());  // quien recibe la reseña
                review.setSkillA(swap.getSkillA());  // habilidad del que evalúa
                review.setSkillB(swap.getSkillB());  // habilidad evaluada
                swap.setReviewA(review);
            } else {
                // El usuario actual es userB en el swap, por lo que:
                // Está evaluando a userA
                review.setUserA(currentUser);  // quien escribe la reseña
                review.setUserB(swap.getUserA());  // quien recibe la reseña
                review.setSkillA(swap.getSkillB());  // habilidad del que evalúa (B usa skillB)
                review.setSkillB(swap.getSkillA());  // habilidad evaluada (la de A)
                swap.setReviewB(review);
            }

            reviewService.saveReview(review);
            swapService.saveSwap(swap);

            response.put("status", "success");
            response.put("message", "Reseña enviada con éxito");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response.put("status", "error");
            response.put("message", "Error al enviar la reseña: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    @PostMapping("/{id}/finishSwap")
    public String finishSwap(@PathVariable long id) {
        swapService.updateStatus(id, Swap.Status.FINISHED);

        messagingTemplate.convertAndSend("/topic/swap/" + id, Map.of(
            "type", "swapFinished",
            "swapId", id
        ));

        return "redirect:/swaps";
    }

    @PostMapping("/{id}/acceptSwap")
    public String acceptSwap(@PathVariable long id) {
        swapService.updateStatus(id, Swap.Status.ACTIVE);
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
