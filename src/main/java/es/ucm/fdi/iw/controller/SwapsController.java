package es.ucm.fdi.iw.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import jakarta.servlet.http.HttpSession;
import jakarta.transaction.Transactional;
import java.util.List;

import es.ucm.fdi.iw.model.Swap;
import es.ucm.fdi.iw.model.User;
import es.ucm.fdi.iw.service.SwapService;
import es.ucm.fdi.iw.service.UserService;
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

    private final SwapService swapService;
    private final UserService userService;


    public SwapsController(SwapService swapService, UserService userService) {
        this.swapService = swapService;
        this.userService = userService;
    }

    private static final Logger log = LogManager.getLogger(SwapsController.class);

    @GetMapping
    public String index(Model model) {
        log.debug("Loading initial swaps page");
        model.addAttribute("actual", "swaps"); 

        List<Swap.Transfer> allSwaps = swapService.getAll();
        model.addAttribute("swaps", allSwaps);

        Swap.Transfer selectedSwap = null;
        if (!allSwaps.isEmpty()) {
            selectedSwap = swapService.getById(allSwaps.get(0).getId());
        }
        
        model.addAttribute("selectedSwap", selectedSwap);

        return "swaps";
    }

    @GetMapping("/{id}")
    public String swapChat(@PathVariable Long id, Model model, HttpSession session) {
        log.debug("intentando sacar información del chat con id: {}", id);
        
        Swap.Transfer swap = swapService.getById(id);
        User.Transfer me = userService.getUsersByID(((User)session.getAttribute("u")).getId()).toTransfer();

        model.addAttribute("selectedSwap", swap);
        model.addAttribute("currentUser", me);
        return "swaps :: chatFragment";
    }

    @PostMapping("/create")
    @Transactional
    @ResponseBody
    public String createSwap(@RequestBody SwapRequest swapRequest) {
        Swap swap = new Swap();
        // TODO: usar service no el em
        /*
        swap.setUserA(entityManager.find(User.class, swapRequest.getUserA()));
        swap.setUserB(entityManager.find(User.class, swapRequest.getUserB()));
        swap.setSkillA(entityManager.find(Skill.class, swapRequest.getSkillA()));
        swap.setSkillB(entityManager.find(Skill.class, swapRequest.getSkillB()));
        swap.setSchedule(List.of(java.sql.Date.valueOf(swapRequest.getSwapDate())));
        entityManager.persist(swap);
        return "{\"status\":\"Swap creado con éxito\"}";
        */
        return "swap/";
    }

    @GetMapping("/info")
    public String info(Model model) {
        return "swapinfo";
    }

    @GetMapping("/panel")
    public String panel(Model model) {
        return "swapspanel";
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
