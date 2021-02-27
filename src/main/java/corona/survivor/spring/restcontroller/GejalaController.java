package corona.survivor.spring.restcontroller;

import corona.survivor.spring.model.Gejala;
import corona.survivor.spring.model.Calendar;
import corona.survivor.spring.model.CreateGejalaDTO;
import corona.survivor.spring.service.GejalaService;
import corona.survivor.spring.service.CalendarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;

@RestController
public class GejalaController {

    @Autowired
    GejalaService gejalaService;

    @Autowired
    CalendarService calendarService;

    @PostMapping("/createGejala")
    public Map<String, Object> createGejala(@RequestBody CreateGejalaDTO listGejala)
            throws InterruptedException, ExecutionException {
        for (String namaGejala : listGejala.getListNamaGejala()) {
            Gejala gejalaModel = new Gejala();
            gejalaModel.setNamaGejala(namaGejala);
            gejalaModel.setEmailPengguna(listGejala.getEmailPengguna());
            gejalaModel.setNomorKalender(listGejala.getNomorKalender());
            gejalaModel.setTanggalDibuat(listGejala.getTanggalDibuat());
            gejalaService.saveGejala(gejalaModel);
        }
        Calendar calendarModel = calendarService.getCalendar(listGejala.getNomorKalender());
        calendarService.calculateSembuhAwal(calendarModel.getNomorKalender());
        Map<String, Object> response = new HashMap<String, Object>();
        Calendar targetCalendar = calendarService.getCalendar(calendarModel.getNomorKalender());
        response.put("startDate", targetCalendar.getStartRed());
        response.put("red", targetCalendar.getRed());
        response.put("yellow", targetCalendar.getYellow());
        response.put("lastDate", targetCalendar.getLastDate());
        return response;
    }
}
