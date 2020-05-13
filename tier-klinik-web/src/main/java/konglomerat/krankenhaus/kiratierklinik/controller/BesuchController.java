package konglomerat.krankenhaus.kiratierklinik.controller;


import konglomerat.krankenhaus.kiratierklinik.model.Besuch;
import konglomerat.krankenhaus.kiratierklinik.model.Tier;
import konglomerat.krankenhaus.kiratierklinik.service.BesuchService;
import konglomerat.krankenhaus.kiratierklinik.service.TierService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
public class BesuchController {
    private final BesuchService besuchService;
    private final TierService tierService;

    public BesuchController(BesuchService besuchService, TierService tierService) {
        this.besuchService = besuchService;
        this.tierService = tierService;
    }

    @InitBinder
    public void setAllowedField(WebDataBinder webDataBinder){
        webDataBinder.setDisallowedFields("id");
    }

    @ModelAttribute("besuch")
    public Besuch ladenTiereMitBesuch(@PathVariable("tierId") Long tierId,Model model){
        Tier tier  = tierService.findById(tierId);
        model.addAttribute("tier", tier);
        Besuch besuch = new Besuch();
        tier.getBesuch().add(besuch);
        besuch.setTier(tier);
        return besuch;
    }

    @GetMapping("/besitzer/*/tier/{tierId}/besuch/neu")
    public String initNeuBesuchFormular(@PathVariable Long tierId, Model model){
        return "besuchFormularErstellenOderAktualisieren";
    }

    @PostMapping("/besitzer/{besitzerId}/tier/{tierId}/besuch/neu")
    public String prozessInitNeuBesuchFormular(@PathVariable Long tierId, @Valid Besuch besuch, BindingResult bindingResult, Model model){
        if(bindingResult.hasErrors()){
            return "besuchFormularErstellenOderAktualisieren";
        }else {
            besuchService.save(besuch);
            return "redirect:/besitzer/{besitzerId}";
        }

    }

}
