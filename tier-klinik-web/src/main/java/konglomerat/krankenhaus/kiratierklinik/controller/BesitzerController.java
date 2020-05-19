package konglomerat.krankenhaus.kiratierklinik.controller;

import konglomerat.krankenhaus.kiratierklinik.model.Besitzer;
import konglomerat.krankenhaus.kiratierklinik.service.BesitzerService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.Set;

@RequestMapping("/besitzer")
@Controller
public class BesitzerController {

    private BesitzerService besitzerService;

    public BesitzerController(BesitzerService besitzerService) {
        this.besitzerService = besitzerService;
    }

    @InitBinder
    public void setErlaubtesFeld(WebDataBinder dataBinder){
        dataBinder.setDisallowedFields("id");

    }

    @GetMapping("/{besitzerId}")
    public ModelAndView anzeigeBesitzer(@PathVariable("besitzerId") Long besitzerId){
        ModelAndView mav = new ModelAndView("besitzer/besitzerAngaben");
        mav.addObject(besitzerService.findById(besitzerId));
        return mav;
    }

    @GetMapping
    public String prozessFormFinden(Besitzer besitzer, BindingResult bindingResult,Model model){
        //Finden alle besitzer
        if (besitzer.getNachName() == null) {
            besitzer.setNachName("");
        }
        Set<Besitzer> besitzerList=  besitzerService.findAllByNachNameLike("%"+besitzer.getNachName()+"%");


        if(besitzerList.isEmpty()){
         bindingResult.reject("Nach Name","nicht finden");
         return  "besitzer/besitzerFinden";
        }else if(besitzerList.size() ==1){

            return "redirect:/besitzer/" +besitzerList.stream().findFirst().get().getKId();
        }
        model.addAttribute("besitzer",besitzerList);
        return "besitzer/besitzerList";
    }


    @RequestMapping("/finden")
    public String findenBesitzer( Model model){

        model.addAttribute("besitzer", Besitzer.builder().build());
        return "besitzer/besitzerFinden";
    }


    @GetMapping("/neu")
    public String erstellenBesitzer(Model model){
        model.addAttribute("besitzer", Besitzer.builder().build());
        return "besitzer/besitzerFormularErstellenOderAktualisieren";
    }

    @PostMapping("/neu")
    public String prozessErstellenBesitzer(@Valid Besitzer besitzer, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return "besitzer/besitzerFormularErstellenOderAktualisieren";
        } else{
            Besitzer besitzer1 = besitzerService.save(besitzer);
            return "redirect:/besitzer/"+ besitzer1.getKId();
        }

    }

    @GetMapping("/{besitzerId}/edit")
    public String bearbeitenBesitzer(Model model, @PathVariable Long besitzerId){
        model.addAttribute("besitzer",besitzerService.findById(besitzerId) );
        return "besitzer/besitzerFormularErstellenOderAktualisieren";
    }

    @PostMapping("/{besitzerId}/edit")
    public String prozessBearbeitenBesitzer(@Valid Besitzer besitzer,@PathVariable Long besitzerId, BindingResult bindingResult){
     if(bindingResult.hasErrors())
     {
         return "besitzer/besitzerFormularErstellenOderAktualisieren";
     } else{
         besitzer.setKId(besitzerId);
         Besitzer besitzer1= besitzerService.save(besitzer);
         return "redirect:/besitzer/"+ besitzer1.getKId();

     }
    }
}
