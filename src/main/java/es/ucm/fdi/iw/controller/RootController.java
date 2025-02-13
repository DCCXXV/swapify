package es.ucm.fdi.iw.controller;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import es.ucm.fdi.iw.model.User;
import es.ucm.fdi.iw.service.SkillService;
import es.ucm.fdi.iw.service.UserService;

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
        List<User> recusers = UserService.getRecommendedUsers();
        List<User> otherusers = UserService.getPopularUsers();
        List<String> desiredSkills = SkillService.getRequestedSkills();
        List<String> commonSkills = SkillService.getCommonSkills();

        model.addAttribute("recusers", recusers);
        model.addAttribute("otherusers", otherusers);
        model.addAttribute("desiredSkills", desiredSkills);
        model.addAttribute("commonSkills", commonSkills);

        return "index";
    }

	@GetMapping("/swaps")
    public String swaps(Model model) {
        return "swaps";
    }
    
    @GetMapping("/swaps/info")
    public String getNewContent() {
        return "swapinfo";
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
