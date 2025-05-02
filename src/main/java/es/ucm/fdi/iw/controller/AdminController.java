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
import es.ucm.fdi.iw.model.Swap;
import jakarta.servlet.http.HttpSession;
import jakarta.transaction.Transactional;

import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;

import org.springframework.http.ResponseEntity;



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

            swapService.deleteSwapById(id);

            return "{\"status\":\"success\"}";
        } catch(Exception e) {
            log.error("Error cancelando swap " + id, e);
            return "{\"status\":\"error\",\"message\":\"" + e.getMessage() + "\"}";
        }
    }

}
