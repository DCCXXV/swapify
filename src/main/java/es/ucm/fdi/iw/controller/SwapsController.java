package es.ucm.fdi.iw.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import jakarta.servlet.http.HttpSession;
import jakarta.transaction.Transactional;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.ArrayList;
import java.util.List;
import es.ucm.fdi.iw.model.Swap;
import es.ucm.fdi.iw.model.User;
import es.ucm.fdi.iw.model.Skill;
import lombok.Getter;
import lombok.Setter;

/**
 *  Site administration.
 *
 *  Access to this end-point is authenticated - see SecurityConfig
 */
@Controller
@RequestMapping("swaps")
public class SwapsController {

    @ModelAttribute
    public void populateModel(HttpSession session, Model model) {        
        for (String name : new String[] {"u", "url", "ws"}) {
            model.addAttribute(name, session.getAttribute(name));
        }
    }

    private static final Logger log = LogManager.getLogger(SwapsController.class);

    @Autowired
    private EntityManager entityManager;

	@GetMapping("/")
    public String index(Model model) {
        model.addAttribute("actual", "swaps");
        return "swaps";
    }

    @GetMapping("/info")
    public String info(Model model) {
        return "swapinfo";
    }

    @GetMapping("/panel")
    public String panel(Model model) {
        return "swapspanel";
    }

    @PostMapping("/create")
    @Transactional
    @ResponseBody
    public String createSwap(@RequestBody SwapRequest swapRequest) {
        Swap swap = new Swap();
        swap.setUserA(entityManager.find(User.class, swapRequest.getUserA()));
        swap.setUserB(entityManager.find(User.class, swapRequest.getUserB()));
        swap.setSkillA(entityManager.find(Skill.class, swapRequest.getSkillA()));
        swap.setSkillB(entityManager.find(Skill.class, swapRequest.getSkillB()));
        swap.setSchedule(List.of(java.sql.Date.valueOf(swapRequest.getSwapDate())));
        entityManager.persist(swap);
        return "{\"status\":\"Swap creado con éxito\"}";
    }

    @Getter @Setter
    public static class SwapRequest {
        private long userA;
        private long userB;
        private long skillA;
        private long skillB;
        private String swapDate;
    }
}
