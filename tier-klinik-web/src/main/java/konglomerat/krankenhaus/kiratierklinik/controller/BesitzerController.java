package konglomerat.krankenhaus.kiratierklinik.controller;

import konglomerat.krankenhaus.kiratierklinik.service.BesitzerService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/besitzer")
@Controller
public class BesitzerController {

    private BesitzerService besitzerService;

    public BesitzerController(BesitzerService besitzerService) {
        this.besitzerService = besitzerService;
    }

    @RequestMapping({"/index","/","","/index.html","/finden"})
    public String listBesitzer(Model model){
        model.addAttribute("besitzer",besitzerService.findAll());
        return "besitzer/index";
    }
}
