package corona.survivor.spring.controller;

import corona.survivor.spring.model.Puskesmas;
import corona.survivor.spring.model.Patient;
import corona.survivor.spring.model.Pengguna;
import corona.survivor.spring.model.Calendar;
import corona.survivor.spring.model.Recovery;
import corona.survivor.spring.model.Gejala;

import corona.survivor.spring.service.PuskesmasService;
import corona.survivor.spring.service.RecoveryService;
import corona.survivor.spring.service.CalendarService;
import corona.survivor.spring.service.PenggunaService;
import corona.survivor.spring.service.GejalaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
public class PuskemasController {

    @Autowired
    PuskesmasService puskesmasService;

    @Autowired
    PenggunaService penggunaService;

    @Autowired
    CalendarService calendarService;

    @Autowired
    RecoveryService recoveryService;

    @Autowired
    GejalaService gejalaService;

    // @GetMapping("/")
    // public String getAllPuskesmas(Model model){
    //     try{
    //         List<Puskesmas> puskesmasList = puskesmasService.getAllPuskesmas();
    //         model.addAttribute("listPuskesmas",puskesmasList);
    //         return "list-puskesmas";
    //     }catch (Exception e){
    //         return null;
    //     }
    // }

    @GetMapping("/")
    public String searchPuskesmas(Model model){
        return "search-puskesmas";
    }


    @PostMapping("/allCalendars")
    public String allPatients(@RequestParam String kodePuskesmas, Model model){
        try{
            List<Calendar> listCalendar = puskesmasService.getAllCalendar(kodePuskesmas);
            // System.out.println("AAA" + listCalendar.get(0).getEmailPengguna());
            model.addAttribute("listCalendar", listCalendar);
            return "all-calendars";
        }catch(Exception e){
            return null;
        }
    }
    
    @GetMapping("/viewCalendar/{email}")
    public String viewCalendar(@PathVariable String email, Model model){
        try{
            Pengguna pengguna = penggunaService.getPengguna(email.strip());
            Calendar calendar = calendarService.getCalendarByEmailPengguna(email.strip());
            List<String> listIdRecovery = calendar.getListRecovery();
            List<Recovery> listRecovery = new ArrayList<Recovery>();
            for(String id : listIdRecovery){
                try{
                    Recovery recovery = recoveryService.getRecovery(id.strip());
                    listRecovery.add(recovery);
                }catch(Exception e){
                    continue;
                }
            }


            // List<String> listIdGejala = calendar.getListGejala();
            // List<Gejala> listGejala = new ArrayList<Gejala>();
            // for(String id: listIdGejala){
            //     try{
            //         Gejala gejala = gejalaService.getGejala(id.strip());
            //         listGejala.add(gejala);
            //     }catch(Exception e){
            //         continue;
            //     }
            // }

            model.addAttribute("calendar", calendar);
            model.addAttribute("pengguna", pengguna);
            model.addAttribute("listGejala", calendar.getListNamaGejala());
            model.addAttribute("email", email);
            model.addAttribute("listRecovery", listRecovery);
            return "calendar";

        }catch(Exception e){
            return null;
        }
    }

    @GetMapping("/viewRecovery/{nomorRecovery}")
    public String viewRecovery(@PathVariable String nomorRecovery, Model model){
        try{
            Recovery recovery = recoveryService.getRecovery(nomorRecovery.strip());
            Calendar targetCalendar = calendarService.getCalendar(recovery.getNomorKalender());

            Date input1 = targetCalendar.getStartRed();
            LocalDate date1 = input1.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

            Date input2 = recovery.getTanggalKalender();
            LocalDate date2 = input2.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

            long diffInDays = ChronoUnit.DAYS.between(date1, date2);
            System.out.println("XXXX " + diffInDays);

            int targetIndex = (int) diffInDays;
            // int targetIndex = daysBetween(targetCalendar.getStartRed(), targetDate);
            if(targetIndex < 0) targetIndex = 0;
            
            List<String> listNamaGejala = new ArrayList<String>();
            List<String> listUpdateGejala = new ArrayList<String>();
            List<String> listGejalaString = targetCalendar.getListGejala();
            for(String uuidGejala : listGejalaString){
                Gejala targetGejala = gejalaService.getGejala(uuidGejala);
                listNamaGejala.add(targetGejala.getNamaGejala());
                listUpdateGejala.add(targetGejala.getSequenceUpdate().get(targetIndex));
            }

            System.out.println("XXXX " + listNamaGejala);
            System.out.println("XXXX " + listUpdateGejala);
            model.addAttribute("listNamaGejala", listNamaGejala);
            model.addAttribute("listUpdateGejala", listUpdateGejala);
            model.addAttribute("recovery", recovery);
            return "detail-recovery";
        }catch(Exception e){
            return null;
        }
    }

    @GetMapping("/addFeedback/{nomorRecovery}")
    public String addFeedback(@PathVariable String nomorRecovery, Model model){
        try{
            Recovery recoveryTarget = recoveryService.getRecovery(nomorRecovery.strip());
            model.addAttribute("recovery", recoveryTarget);
            return "add-feedback";
        }catch(Exception e){
            return null;
        }
    }

    @PostMapping("/addFeedback/{nomorRecovery}")
    public String addFeedback(@PathVariable String nomorRecovery, @RequestParam String feedback, Model model){
        try{
            System.out.println("XXXX " + feedback);
            Recovery recoveryTarget = recoveryService.getRecovery(nomorRecovery.strip());
            recoveryTarget.setFeedback(feedback);
            recoveryService.updateRecovery(recoveryTarget);
            model.addAttribute("recovery", recoveryTarget);
            return "search-puskesmas";
        }catch(Exception e){
            return null;
        }
    }






}
