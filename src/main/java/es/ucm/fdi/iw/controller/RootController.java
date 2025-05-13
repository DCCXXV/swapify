package es.ucm.fdi.iw.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Base64;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.JsonProcessingException;

import es.ucm.fdi.iw.LocalData;
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

    @Autowired
    private LocalData localData;

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
    public String index(Model model, HttpSession session, @RequestParam(defaultValue = "0") int page,
    @RequestParam(defaultValue = "9") int size) throws JsonProcessingException {
        model.addAttribute("actual", "inicio");

        User currentUser = (User) session.getAttribute("u");
        Page<User> pagedUsers = userService.findUsers(page, size, currentUser.getId());
                List<User.Transfer> userTransfers = pagedUsers.getContent()
                .stream().map(User::toTransfer).toList();
        
        model.addAttribute("users", userTransfers);
        model.addAttribute("hasMore", pagedUsers.hasNext());
        model.addAttribute("currentPage", page);

        List<Skill.Transfer> desiredSkills = skillService.getDesired();
        List<Skill.Transfer> commonSkills = skillService.getCommon();
        model.addAttribute("desiredSkills", desiredSkills);
        model.addAttribute("commonSkills", commonSkills);

        User me = userService.getUsersByID(((User) session.getAttribute("u")).getId());
        model.addAttribute("me", me.toTransfer());

        model.addAttribute("hasMore", pagedUsers.hasNext());
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

    @GetMapping("/checkEmail")
    @ResponseBody
    public ResponseEntity<?> checkEmail(@RequestParam String email) {
        boolean existe = userService.findEmail(email);
        return ResponseEntity.ok(existe ? "EXISTE" : "LIBRE");
    }

    @GetMapping("/checkFirstName")
    @ResponseBody
    public ResponseEntity<?> checkFirstName(@RequestParam String firstName) {
        boolean existe = userService.findFirstname(firstName);
        return ResponseEntity.ok(existe ? "EXISTE" : "LIBRE");
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
            @RequestParam(name = "photoBase64", required = false) String photoBase64,
            @RequestParam(name = "currentSkillNames[]", required = false) List<String> currentSkills,
            @RequestParam(name = "currentSkillDescriptions[]", required = false) List<String> currentSkillDescriptions,
            @RequestParam(name = "desiredSkillNames[]", required = false) List<String> desiredSkills,
            @RequestParam(name = "desiredSkillDescriptions[]", required = false) List<String> desiredSkillDescriptions)
            throws IOException {

        // usuario nuevo
        User newUser = new User();
        newUser.setFirstName(firstName);
        newUser.setLastName(lastName);
        newUser.setEmail(email);
        newUser.setPassword(passwordEncoder.encode(password));
        newUser.setDescription(description);
        newUser.setUsername(email.substring(0, email.indexOf("@")));
        userService.registerUser(newUser);
        
        if (photoBase64 != null && !photoBase64.isBlank()) {
            // Suele venir "data:image/png;base64,iVBORw0KGgoAAA..."
            // Quitamos la parte "data:image/...;base64,"
            String base64Data = photoBase64.substring(photoBase64.indexOf(",") + 1);
            byte[] fileBytes = Base64.getDecoder().decode(base64Data);

            // Prepara la carpeta y el nombre (p.ej. userId.jpg)
            File finalFile = localData.getFile("user", newUser.getId() + ".jpg");
            Files.write(finalFile.toPath(), fileBytes);

            // setPic si quieres que en la BD aparezca "42.jpg"
            newUser.setPic(newUser.getId() + ".jpg");

        }

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
    public String search(@RequestParam(name = "query", required = false) String keyword, Model model, HttpSession session) {
        User me = (User) session.getAttribute("u");
        model.addAttribute("query", keyword);
        model.addAttribute("skills", skillService.getSkillsByKeyword(keyword));

        if(me == null){        
            model.addAttribute("users", userService.getUsersByKeyword(keyword));
        }else{
            model.addAttribute("users", userService.getUsersByKeywordWithoutUser(keyword, me));
        }

        return "search";
    }

    @GetMapping("/loadMoreUsers")
    @ResponseBody
    public List<User.Transfer> loadMoreUsers(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "9") int size,
            HttpSession session) {
    
        User currentUser = (User) session.getAttribute("u");
    
        Page<User> pagedUsers = userService.findUsers(page, size, currentUser.getId());
        return pagedUsers.getContent()
                         .stream()
                         .map(User::toTransfer)
                         .toList();
    }
    
}
