package konglomerat.krankenhaus.kiratierklinik.controller;

import konglomerat.krankenhaus.kiratierklinik.service.TierArztService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
@RequestMapping("/tierarzt")
@Controller
public class TierArztController {

    public TierArztController(TierArztService tierArztService) {
        this.tierArztService = tierArztService;
    }

    private TierArztService tierArztService;

    @RequestMapping({"/index","/","","/index.html","/finden"})
    public String listTierArzt(Model model){
        model.addAttribute("tierArzt",tierArztService.findAll());
        return "tierarzt/index";
    }
}
