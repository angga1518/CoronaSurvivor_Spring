package corona.survivor.spring.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PuskemasController {

    @GetMapping("/")
    public String index(){
        return "index";
    }

}
