package corona.survivor.spring.restcontroller;
import corona.survivor.spring.model.Gejala;
import corona.survivor.spring.model.Calendar;
import corona.survivor.spring.model.CreateGejalaDTO;
import corona.survivor.spring.service.GejalaService;
import corona.survivor.spring.service.CalendarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

@RestController
public class GejalaController {

    @Autowired
    GejalaService gejalaService;

    @Autowired
    CalendarService calendarService;

    @PostMapping("/createGejala")
    public List<Gejala> createGejala(@RequestBody CreateGejalaDTO listGejala) throws InterruptedException, ExecutionException {
        List<Gejala> listGejalaCreated = new ArrayList<Gejala>();
        for(String namaGejala : listGejala.getListNamaGejala()){
            Gejala gejalaModel = new Gejala();
            gejalaModel.setNamaGejala(namaGejala);
            gejalaModel.setEmailPengguna(listGejala.getEmailPengguna());
            gejalaModel.setNomorKalender(listGejala.getNomorKalender());
            gejalaModel.setTanggalDibuat(listGejala.getTanggalDibuat());
            listGejalaCreated.add(gejalaService.saveGejala(gejalaModel));
        }
        Calendar calendarModel = calendarService.getCalendar(listGejala.getNomorKalender());
        calendarService.calculateSembuhAwal(calendarModel.getNomorKalender());
        return listGejalaCreated;
    }
}
