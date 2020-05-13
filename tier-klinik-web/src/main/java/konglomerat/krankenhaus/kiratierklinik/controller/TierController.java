package konglomerat.krankenhaus.kiratierklinik.controller;
import konglomerat.krankenhaus.kiratierklinik.model.Besitzer;
import konglomerat.krankenhaus.kiratierklinik.model.Tier;
import konglomerat.krankenhaus.kiratierklinik.model.TierTyp;
import konglomerat.krankenhaus.kiratierklinik.service.BesitzerService;
import konglomerat.krankenhaus.kiratierklinik.service.TierService;
import konglomerat.krankenhaus.kiratierklinik.service.TierTypService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collection;

@Controller
@RequestMapping("/besitzer/{besitzerId}")
public class TierController {

    private final TierService tierService;
    private final BesitzerService besitzerService;
    private final TierTypService tierTypService;

    public TierController(TierService tierService, BesitzerService besitzerService, TierTypService tierTypService) {
        this.tierService = tierService;
        this.besitzerService = besitzerService;
        this.tierTypService = tierTypService;
    }

    @ModelAttribute("typ")
    public Collection<TierTyp> fullenTierTypen(){
        return tierTypService.findAll();

    }

    @ModelAttribute("besitzer")
    public Besitzer findenBesitzer(@PathVariable long besitzerId){
        return besitzerService.findById(besitzerId);
    }

    @InitBinder("besitzer")
    public void initBesitzerBinder(WebDataBinder webDataBinder){
        webDataBinder.setDisallowedFields("id");
    }

    @GetMapping("/tier/neu")
    public String erstellenFormular(Besitzer besitzer, Model model){
        Tier tier= new Tier();
        besitzer.getTiere().add(tier);
        tier.setBesitzer(besitzer);
        model.addAttribute("tier",tier);
        return "tierFormularErstellenOderAktualisieren";

    }

    @PostMapping("/tier/neu")
    public String prozessErstellenFormular(Besitzer besitzer, @Valid Tier tier, BindingResult bindingResult, Model model) {
        if(StringUtils.hasLength(tier.getName()) && tier.isNew() && besitzer.getTier(tier.getName(),true)!=null){
            bindingResult.rejectValue("name","Duplikat","ist bereits vorhanden");
        }
        besitzer.getTiere().add(tier);
        tier.setBesitzer(besitzer);
        if(bindingResult.hasErrors()) {
            model.addAttribute("tier",tier);
            return "tierFormularErstellenOderAktualisieren";
        } else {
            tierService.save(tier);
            return "redirect:/besitzer/"+besitzer.getId();
        }

    }

    @GetMapping("/tier/{tierId}/edit")
    public String aktualisierenFormular(Model model, @PathVariable Long tierId){
        model.addAttribute("tier",tierService.findById(tierId));
        return "tierFormularErstellenOderAktualisieren";

    }

    @PostMapping("/tier/{tierId}/edit")
    public String prozessAktualisierenFormular(Besitzer besitzer, @Valid Tier tier, BindingResult bindingResult, Model model, @PathVariable Long tierId) {
        if(bindingResult.hasErrors()) {
            tier.setBesitzer(besitzer);
            model.addAttribute("tier",tier);
            return "tierFormularErstellenOderAktualisieren";
        } else {
            besitzer.getTiere().add(tier);
            tierService.save(tier);
            return "redirect:/besitzer/"+besitzer.getId();
        }

    }

}


