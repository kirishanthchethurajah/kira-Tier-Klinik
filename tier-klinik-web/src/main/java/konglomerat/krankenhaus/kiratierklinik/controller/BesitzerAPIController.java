package konglomerat.krankenhaus.kiratierklinik.controller;

import konglomerat.krankenhaus.kiratierklinik.model.Besitzer;
import konglomerat.krankenhaus.kiratierklinik.service.BesitzerService;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;


@RestController
@RequestMapping("api/besitzer")
public class BesitzerAPIController {

    private BesitzerService besitzerService;

    public BesitzerAPIController(BesitzerService besitzerService) {
        this.besitzerService = besitzerService;
    }

    @PostMapping("/{besitzerId}/edit")
    public ResponseEntity<Void> prozessBearbeitenBesitzer(@Valid Besitzer besitzer, @PathVariable Long besitzerId, BindingResult bindingResult){
        if(bindingResult.hasErrors())
        {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } else{
            besitzer.setKId(besitzerId);
            Besitzer besitzer1= besitzerService.save(besitzer);
            HttpHeaders headers = new HttpHeaders();
            headers.add("id", String.valueOf(besitzerId));
            return new ResponseEntity<>(headers,HttpStatus.CREATED);

        }
    }

    @PostMapping("/neu")
    public ResponseEntity<Void> prozessErstellenBesitzer(@Valid Besitzer besitzer, BindingResult bindingResult){
        if(bindingResult.hasErrors() || besitzer.getVorName()!=null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } else{
            Besitzer besitzer1 = besitzerService.save(besitzer);
            HttpHeaders headers = new HttpHeaders();
            headers.add("id", String.valueOf(besitzer.getKId().toString()));
            return new ResponseEntity<>(headers,HttpStatus.CREATED);
        }

    }

    @GetMapping("/{besitzerId}/edit")
    public ResponseEntity<Besitzer> bearbeitenBesitzer(Model model, @PathVariable Long besitzerId) {
        Besitzer besitzer1 = besitzerService.findById(besitzerId);
        Link selfLink = linkTo(BesitzerAPIController.class).slash(besitzerId).withSelfRel();
        //besitzer1.add(selfLink);
    if (besitzer1 == null) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(besitzer1, HttpStatus.OK);

        }
    }
}
