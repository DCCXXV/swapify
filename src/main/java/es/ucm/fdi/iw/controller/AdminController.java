package es.ucm.fdi.iw.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import es.ucm.fdi.iw.model.User;
import es.ucm.fdi.iw.service.UserService;
import es.ucm.fdi.iw.service.SwapService;
import es.ucm.fdi.iw.service.MessageService;
import es.ucm.fdi.iw.service.ReviewService;
import es.ucm.fdi.iw.model.Swap;
import es.ucm.fdi.iw.model.Review;
import jakarta.servlet.http.HttpSession;
import jakarta.transaction.Transactional;

import java.util.Map;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;




/**
 *  Site administration.
 *
 *  Access to this end-point is authenticated - see SecurityConfig
 */
@Controller
@RequestMapping("admin")
public class AdminController {

    @Autowired
    private UserService userService;

    @Autowired
    private SwapService swapService;

    @Autowired
    private ReviewService reviewService;
    
    @Autowired
    private MessageService messageService;

    @ModelAttribute
    public void populateModel(HttpSession session, Model model) {        
        for (String name : new String[] {"u", "url", "ws"}) {
            model.addAttribute(name, session.getAttribute(name));
        }
    }

    private static final Logger log = LogManager.getLogger(AdminController.class);

	@GetMapping("/")
    public String index(Model model) {
        log.info("Admin acaba de entrar");
        model.addAttribute("users", userService.getAllUsers());
        model.addAttribute("totalUser", userService.getAllUsers().size());
        model.addAttribute("totalSwapsActivated", swapService.countActiveSwaps());
        model.addAttribute("swaps", swapService.getAllSwaps());
        return "admin";
    }
    
    @PostMapping("/toggle/{id}")
    @Transactional
    @ResponseBody
    public String toggleUser(@PathVariable long id, Model model) {
        log.info("Admin cambia estado de " + id);
        User target = userService.getUsersByID(id);
        target.setDeleted(!target.isDeleted());
        return "{\"deleted\":" + target.isDeleted() + "}";
    }
    
    @PostMapping("/cancelSwap/{id}")
    @Transactional
    @ResponseBody
    public String cancelSwap(@PathVariable long id) {
        try {
            log.info("Admin cancela swap con id: " + id);
            Swap.Transfer swap = swapService.getById(id);
            long userAId = swap.getUserA().getId();
            long userBId = swap.getUserB().getId();

            Swap swapEntity = swapService.getSwapByID(id);
            
            try {
                messageService.deleteAllMessagesForSwap(id);

                if (swapEntity.getReviewA() != null) {
                    Review reviewA = swap.getReviewA();
                    // Poner a null para romper la relación con el swap
                    swapEntity.setReviewA(null);
                    reviewService.removeSwapFromProfile(reviewA);
                }
                if (swapEntity.getReviewB() != null) {
                    Review reviewB = swap.getReviewB();
                    swapEntity.setReviewB(null);
                    reviewService.removeSwapFromProfile(reviewB);
                }

            } catch (Exception e) {
                log.error("Error al eliminar mensajes o reviews del swap " + id, e);
            }
            
            swapService.deleteSwapById(id);
            
            reviewService.updateRating(userAId);
            reviewService.updateRating(userBId);

            return "{\"status\":\"success\"}";
        } catch(Exception e) {
            log.error("Error cancelando swap " + id, e);
            return "{\"status\":\"error\",\"message\":\"" + e.getMessage() + "\"}";
        }
    }
    
    @PostMapping("/editarUsuario")
    @Transactional
    @ResponseBody
    public Map<String, Object> editarUsuario(@RequestBody Map<String, Object> info,
                            Model model,
                            HttpSession session) {
        long id = Long.parseLong(info.get("id").toString());
        String firstName = info.get("firstName").toString();
        String lastName = info.get("lastName").toString();
        String username = info.get("username").toString();
        String email = info.get("email").toString();
        User user = userService.getUsersByID(id);
        if (user != null) {
            user.setFirstName(firstName);
            user.setLastName(lastName);
            user.setUsername(username);
            user.setEmail(email);
            return Map.of("status", "success");
        } else {
            return Map.of("status", "error", "message", "Usuario no encontrado");
        }
    }
    

    @GetMapping("/searchUser/{id}")
    @ResponseBody
    public ResponseEntity<Map<String, Object>> searchUserById(@PathVariable long id) {
        User user = userService.getUsersByID(id);
        if(user == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(Map.of("status", "error", "message", "Usuario no encontrado"));
        }
        Map<String, Object> userDto = Map.of(
             "id", user.getId(),
             "firstName", user.getFirstName(),
             "lastName", user.getLastName(),
             "username", user.getUsername(),
             "email", user.getEmail()
        );
        return ResponseEntity.ok(Map.of("status", "success", "user", userDto));
    }

    @GetMapping("/searchSwap/{id}")
    @ResponseBody
    public ResponseEntity<Map<String, Object>> searchSwapById(@PathVariable long id) {
        try {
            Swap swapEntity = swapService.getSwapByID(id);
            Map<String, Object> skillA = Map.of("name", swapEntity.getSkillA().getName());
            Map<String, Object> skillB = Map.of("name", swapEntity.getSkillB().getName());
            Map<String, Object> userA = Map.of("username", swapEntity.getUserA().getUsername());
            Map<String, Object> userB = Map.of("username", swapEntity.getUserB().getUsername());
            
            Map<String, Object> swapDto = Map.of(
                "id", swapEntity.getId(),
                "skillA", skillA,
                "skillB", skillB,
                "userA", userA,
                "userB", userB,
                "swapStatus", swapEntity.getSwapStatus()
            );
            return ResponseEntity.ok(Map.of("status", "success", "swap", swapDto));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(Map.of("status", "error", "message", "Swap no encontrado con id: " + id));
        }
    }
    
}
