package es.ucm.fdi.iw.controller;

import java.util.ArrayList;
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

import es.ucm.fdi.iw.model.CurrentSkill;
import es.ucm.fdi.iw.model.DesiredSkill;
import es.ucm.fdi.iw.model.Skill;
import es.ucm.fdi.iw.model.User;
import es.ucm.fdi.iw.service.SkillService;
import es.ucm.fdi.iw.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import org.springframework.security.crypto.password.PasswordEncoder;


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
    private PasswordEncoder passwordEncoder;


    @ModelAttribute
    public void populateModel(HttpSession session, Model model) {
        for (String name : new String[] { "u", "url", "ws" }) {
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

    @GetMapping("/signupstep2")
    public String processSignupStep1(HttpServletRequest request, HttpSession session) {
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String email = request.getParameter("email");
        String emailRep = request.getParameter("emailRep");
        String password = request.getParameter("password");
        String passwordRep = request.getParameter("passwordRep");

        if (!email.equals(emailRep) || !password.equals(passwordRep)) {
            session.setAttribute("signupError", "Los correos o contraseñas no coinciden");
            //return "redirect:/signup";
        }

        RegistrationData regData = new RegistrationData(firstName, lastName, email, password);
        session.setAttribute("regData", regData);

        return "signupstep2";
    }

    /*
    @PostMapping("/signupstep2")
    public String showStep2() {
        // "signup-step2" es un fragmento Thymeleaf con el segundo formulario
        return "signupstep2"; // Vista con el formulario de habilidades
    }*/

    @PostMapping("/signupstep3")
    public String processSignupStep2(
        @RequestParam String firstName,
        @RequestParam String lastName,
        @RequestParam String email,
        @RequestParam String password,
        @RequestParam(name = "currentSkillNames[]", required = false) List<String> currentSkillNames,
        @RequestParam(name = "currentSkillDescriptions[]", required = false) List<String> currentSkillDescriptions,
        @RequestParam(name = "desiredSkillNames[]", required = false) List<String> desiredSkillNames,
        @RequestParam(name = "desiredSkillDescriptions[]", required = false) List<String> desiredSkillDescriptions,
        Model model) {

            User user = new User();
            user.setFirstName(firstName);
            user.setLastName(lastName);
            user.setEmail(email);
            user.setUsername(email);
            user.setPassword(passwordEncoder.encode(password));
            user.setDeleted(false);
            user.setRoles("USER");
            
            userService.registerUser(user);

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

    // Clase interna para almacenar temporalmente los datos del registro
    public static class RegistrationData {
        private String firstName;
        private String lastName;
        private String email;
        private String password;

        public RegistrationData(String firstName, String lastName, String email, String password) {
            this.firstName = firstName;
            this.lastName = lastName;
            this.email = email;
            this.password = password;
        }

        public String getFirstName() {
            return firstName;
        }

        public void setFirstName(String firstName) {
            this.firstName = firstName;
        }

        public String getLastName() {
            return lastName;
        }

        public void setLastName(String lastName) {
            this.lastName = lastName;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }
    }
}
