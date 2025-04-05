package es.ucm.fdi.iw.controller;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.fasterxml.jackson.core.JsonProcessingException;

import es.ucm.fdi.iw.model.CurrentSkill;
import es.ucm.fdi.iw.model.DesiredSkill;
import es.ucm.fdi.iw.model.Skill;
import es.ucm.fdi.iw.model.User;
import es.ucm.fdi.iw.service.CurrentSkillService;
import es.ucm.fdi.iw.service.DesiredSkillService;
import es.ucm.fdi.iw.service.SkillService;
import es.ucm.fdi.iw.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

/**
 * Non-authenticated requests only.
 */
@Controller
public class RootController {

    private static final Logger log = LogManager.getLogger(RootController.class);

    @Autowired
    private UserService userService;

    @Autowired
    private SkillService skillService;

    @Autowired
    private CurrentSkillService currentSkillService;
    
    @Autowired
    private DesiredSkillService desiredSkillService;


    @Autowired
    private PasswordEncoder passwordEncoder;

    RootController(CurrentSkillService currentSkillService) {
        this.currentSkillService = currentSkillService;
    }

    @ModelAttribute
    public void populateModel(HttpSession session, Model model) {
        for (String name : new String[] { "u", "url", "ws" }) {
            model.addAttribute(name, session.getAttribute(name));
        }
    }

    @GetMapping("/")
    public String index(Model model, HttpSession session) throws JsonProcessingException {
        model.addAttribute("actual", "inicio");

        List<User.Transfer> otherusers = userService.getAllUsers();

        List<String> desiredSkills = SkillService.getRequestedSkills();
        List<String> commonSkills = SkillService.getCommonSkills();

        model.addAttribute("desiredSkills", desiredSkills);
        model.addAttribute("commonSkills", commonSkills);

		User me = userService.getUsersByID(((User)session.getAttribute("u")).getId());
		model.addAttribute("me", me.toTransfer());
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
    public String processSignupStep1() {
        return "signup";
    }

    @GetMapping("/signupstep2")
    public String processSignupStep2(HttpServletRequest request, HttpSession session) {
        String email = request.getParameter("email");
        String emailRep = request.getParameter("emailRep");
        String password = request.getParameter("password");
        String passwordRep = request.getParameter("passwordRep");

        if (!email.equals(emailRep) || !password.equals(passwordRep)) {
            session.setAttribute("signupError", "Los correos o contraseñas no coinciden");
            return "redirect:/error";
        }

        return "signupstep2";
    }

    @GetMapping("/signupstep3")
    public String processSignupStep3() {
        return "signupstep3";
    }

    @PostMapping("/finalizarRegistro")
    public String finalizarRegistro(
            @RequestParam String firstName,
            @RequestParam String lastName,
            @RequestParam String email,
            @RequestParam String password,
            @RequestParam String description,
            @RequestParam String photo,
            @RequestParam(name = "currentSkillNames[]", required = false) List<String> currentSkills,
            @RequestParam(name = "currentSkillDescriptions[]", required = false) List<String> currentSkillDescriptions,
            @RequestParam(name = "desiredSkillNames[]", required = false) List<String> desiredSkills,
            @RequestParam(name = "desiredSkillDescriptions[]", required = false) List<String> desiredSkillDescriptions) {

        // usuario nuevo
        User newUser = new User();
        newUser.setFirstName(firstName);
        newUser.setLastName(lastName);
        newUser.setEmail(email);
        newUser.setPassword(passwordEncoder.encode(password));
        newUser.setDescription(description);
        newUser.setUsername(email.substring(0, email.indexOf("@")));
        newUser.setPic(photo);

        userService.registerUser(newUser);

        // habilidades actuales
        if (currentSkills != null) {
            for (int i = 0; i < currentSkills.size(); i++) {
                String skillName = currentSkills.get(i);
                String skillDescription = (currentSkillDescriptions != null && currentSkillDescriptions.size() > i)
                        ? currentSkillDescriptions.get(i)
                        : "";
                Skill skill = skillService.getOrCreateSkill(skillName);

                CurrentSkill cs = new CurrentSkill();
                cs.setUser(newUser);
                cs.setSkill(skill);
                cs.setDescription(skillDescription);
                currentSkillService.saveCurrentSkill(cs);
            }
        }

        // habilidades deseadas
        if (desiredSkills != null) {
            for (int i = 0; i < desiredSkills.size(); i++) {
                String skillName = desiredSkills.get(i);
                String skillDescription = (desiredSkillDescriptions != null && desiredSkillDescriptions.size() > i)
                        ? desiredSkillDescriptions.get(i)
                        : "";
                Skill skill = skillService.getOrCreateSkill(skillName);
                DesiredSkill ds = new DesiredSkill();
                ds.setSkill(skill);
                ds.setDescription(skillDescription);
                ds.setUser(newUser);
                desiredSkillService.saveDesiredSkill(ds);
            }
        } 

        return "redirect:/login";
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
