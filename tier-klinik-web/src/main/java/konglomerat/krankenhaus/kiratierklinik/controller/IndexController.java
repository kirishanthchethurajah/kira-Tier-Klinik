package konglomerat.krankenhaus.kiratierklinik.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {
    @RequestMapping({"/index","/","","/index.html","home","/startseite"})
    public String index(){
        return "index";
    }
}
