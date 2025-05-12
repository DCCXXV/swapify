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

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PathVariable;




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
    
}
