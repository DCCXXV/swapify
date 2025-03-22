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

    // Muestra el formulario de registro (datos generales)
    @GetMapping("/signup")
    public String showSignup() {
        return "signup";
    }

    // Procesa el formulario del paso 1
    @PostMapping("/signup")
    public String processSignupStep1(HttpServletRequest request, HttpSession session) {
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String email = request.getParameter("email");
        String emailRep = request.getParameter("emailRep");
        String password = request.getParameter("password");
        String passwordRep = request.getParameter("passwordRep");

        // Validar que los campos repetidos coincidan
        if (!email.equals(emailRep) || !password.equals(passwordRep)) {
            session.setAttribute("signupError", "Los correos o contraseñas no coinciden");
            return "signup";
        }

        // Crear un objeto para almacenar temporalmente los datos de registro
        RegistrationData regData = new RegistrationData(firstName, lastName, email, password);
        session.setAttribute("regData", regData);

        // Redirigir al paso 2 para recoger las habilidades
        return "redirect:/signupstep2";
    }

    @GetMapping("/signupstep2")
    public String showStep2() {
        // "signup-step2" es un fragmento Thymeleaf con el segundo formulario
        return "signupstep2"; // Vista con el formulario de habilidades
    }

    @PostMapping("/signupstep2")
    public String processSignupStep2(HttpServletRequest request, HttpSession session) {
        // Recuperar los datos generales del usuario almacenados en sesión
        RegistrationData regData = (RegistrationData) session.getAttribute("regData");
        if (regData == null) {
            return "redirect:/signup"; // Si no hay datos, se redirige al paso 1
        }

        // Recuperar las habilidades actuales (se envían como arrays)
        String[] currentSkills = request.getParameterValues("skillInputId");
        String[] currentDescriptions = request.getParameterValues("descriptionInputId");

        // Recuperar las habilidades deseadas (nombres de parámetros distintos)
        String[] desiredSkills = request.getParameterValues("desiredSkillInputId");
        String[] desiredDescriptions = request.getParameterValues("desiredDescriptionInputId");

        // Crear el objeto User y asignar los datos generales
        User user = new User();
        user.setFirstName(regData.getFirstName());
        user.setLastName(regData.getLastName());
        user.setEmail(regData.getEmail());
        user.setUsername(regData.getEmail()); // Usar el email como nombre de usuario
        user.setPassword(passwordEncoder.encode(regData.getPassword()));
        user.setDeleted(false);
        user.setRoles("USER");

        // Asignar las habilidades actuales
        List<CurrentSkill> currSkillList = new ArrayList<>();
        if (currentSkills != null) {
            for (int i = 0; i < currentSkills.length; i++) {
                String skillName = currentSkills[i];
                String desc = (currentDescriptions != null && currentDescriptions.length > i) ? currentDescriptions[i]
                        : "";
                // Obtener o crear la Skill usando el servicio
                Skill skill = skillService.getOrCreateSkill(skillName); // Método que debes implementar en SkillService

                // Crear la habilidad actual
                int rating = 0; // Default value
                int points = 0; // Default value
                CurrentSkill cs = new CurrentSkill(skill, desc, rating, points);
                cs.setUser(user);
                currSkillList.add(cs);
            }
        }
        user.setCurrentSkills(currSkillList);

        // Asignar las habilidades deseadas
        List<DesiredSkill> desiredSkillList = new ArrayList<>();
        if (desiredSkills != null) {
            for (int i = 0; i < desiredSkills.length; i++) {
                String skillName = desiredSkills[i];
                String desc = (desiredDescriptions != null && desiredDescriptions.length > i) ? desiredDescriptions[i]
                        : "";
                // Obtener o crear la Skill correspondiente
                Skill skill = skillService.getOrCreateSkill(skillName);

                DesiredSkill ds = new DesiredSkill();
                ds.setSkill(skill);
                ds.setDescription(desc);
                ds.setUser(user);
                desiredSkillList.add(ds);
            }
        }
        user.setDesiredSkills(desiredSkillList);

        // Registrar el usuario en la base de datos utilizando el servicio
        userService.registerUser(user);

        // Limpiar los datos de registro de la sesión
        session.removeAttribute("regData");

        // Redirigir a la página de login o a una página de confirmación
        return "redirect:/login?signupSuccess=true";
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
