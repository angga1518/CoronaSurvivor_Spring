package corona.survivor.spring.controller;


import corona.survivor.spring.model.Puskesmas;
import corona.survivor.spring.service.PuskesmasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class PuskemasController {

    @Autowired
    PuskesmasService puskesmasService;

    @GetMapping("/")
    public String getAllPuskesmas(Model model){
        try{
            List<Puskesmas> puskesmasList = puskesmasService.getAllPuskesmas();
            model.addAttribute("listPuskesmas",puskesmasList);
            return "list-puskesmas";
        }catch (Exception e){
            return null;
        }
    }


}
