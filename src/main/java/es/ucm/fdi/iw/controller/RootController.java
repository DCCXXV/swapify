package es.ucm.fdi.iw.controller;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import es.ucm.fdi.iw.model.User;
import es.ucm.fdi.iw.service.SkillService;
import es.ucm.fdi.iw.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

/**
 *  Non-authenticated requests only.
 */
@Controller
public class RootController {

    private static final Logger log = LogManager.getLogger(RootController.class);

    @Autowired
    private UserService userService;

    @Autowired
	private SkillService skillService;

    @ModelAttribute
    public void populateModel(HttpSession session, Model model) {        
        for (String name : new String[] {"u", "url", "ws"}) {
            model.addAttribute(name, session.getAttribute(name));
        }
    }

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("actual", "inicio");
        
        List<User.Transfer> otherusers = userService.getAllUsers();

        List<String> desiredSkills = SkillService.getRequestedSkills();
        List<String> commonSkills = SkillService.getCommonSkills();

        model.addAttribute("desiredSkills", desiredSkills);
        model.addAttribute("commonSkills", commonSkills);
        
		model.addAttribute("otherusers", otherusers);
        
        return "index";
    }

	@GetMapping("/login")
    public String login(Model model, HttpServletRequest request) {
        boolean error = request.getQueryString() != null && request.getQueryString().indexOf("error") != -1;
        model.addAttribute("loginError", error);
        return "login";
    }
    

    @GetMapping("/signup")
    public String showSignup() {
        return "signup";
    }

    @PostMapping("/signup")
    public String signup(Model model, HttpServletRequest request) {
        return "/signupstep2";
    } 

    @GetMapping("/signupstep2")
    public String showStep2() {
        // "signup-step2" es un fragmento Thymeleaf con el segundo formulario
        return "signupstep2";
    }

    @PostMapping("/signupstep2")
    public String signupstep2(Model model, HttpServletRequest request) {
        return "signupstep2";
    }
    

    @GetMapping("/rewards")
    public String rewards(Model model) {
        return "rewards";
    }

   @GetMapping("/search")
    public String search(@RequestParam(name = "query", required = false) String keyword, Model model) {
        model.addAttribute("query", keyword);
        model.addAttribute("users", userService.getUsersByKeyword(keyword));
        model.addAttribute("skills", skillService.getSkillsByKeyword(keyword));
        return "search";
    }
}
