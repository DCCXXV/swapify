package es.ucm.fdi.iw.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *  Non-authenticated requests only.
 */
@Controller
public class RootController {

	private static final Logger log = LogManager.getLogger(RootController.class);

	@GetMapping("/login")
    public String login(Model model) {
        return "login";
    }

	@GetMapping("/")
    public String index(Model model) {
        return "index";
    }

    @GetMapping("/messages")
    public String messages(Model model) {
        return "messages";
    }

    @GetMapping("/rewards")
    public String rewards(Model model) {
        return "rewards";
    }

   @GetMapping("/search")
    public String search(@RequestParam(name = "username", required = false) String username, Model model) {
        model.addAttribute("username", username);
        return "search";
    }
}
