package corona.survivor.spring.controller;


import corona.survivor.spring.model.Puskesmas;
import corona.survivor.spring.model.Patient;
import corona.survivor.spring.model.Calendar;

import corona.survivor.spring.service.PuskesmasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

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

    @GetMapping("/searchPuskesmas")
    public String searchPuskesmas(Model model){
        return "search-puskesmas";
    }


    @PostMapping("/allCalendars")
    public String allPatients(@RequestParam String kodePuskesmas, Model model){
        try{
            System.out.println("AAA" + kodePuskesmas);
            List<Calendar> listCalendar = puskesmasService.getAllCalendar(kodePuskesmas);
            System.out.println("AAA" + listCalendar.get(0).getEmailPengguna());
            model.addAttribute("listCalendar, listCalendar");
            return "all-calendars";
        }catch(Exception e){
            return null;
        }
    }


}
