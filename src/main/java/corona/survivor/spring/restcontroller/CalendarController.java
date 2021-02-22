package corona.survivor.spring.restcontroller;

import corona.survivor.spring.model.Calendar;
import corona.survivor.spring.model.Recovery;
import corona.survivor.spring.model.Gejala;
import corona.survivor.spring.model.GejalaDTO;
import corona.survivor.spring.model.ListDTO;
import corona.survivor.spring.service.CalendarService;
import corona.survivor.spring.service.RecoveryService;
import corona.survivor.spring.service.GejalaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.unit.DataSize;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.CollectionReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.QuerySnapshot;
import com.google.cloud.firestore.WriteResult;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.WriteResult;
import com.google.firebase.cloud.FirestoreClient;

import corona.survivor.spring.firebase.FirebaseInitialize;


@RestController
public class CalendarController {

    @Autowired
    CalendarService calendarService;

    @Autowired
    RecoveryService recoveryService;

    @Autowired
    GejalaService gejalaService;

    @Autowired
    FirebaseInitialize db;
    
    @PostMapping("/createCalendar")
    public Calendar createDataCalendar(@RequestBody Calendar calendarModel) throws InterruptedException, ExecutionException {
        return calendarService.saveCalendar(calendarModel);
    }

    @GetMapping("/calculateSembuhAwal/{nomorCalendar}")
    public String calculateSembuhAwalCalendar(@PathVariable String nomorCalendar){
        try{
            calendarService.calculateSembuhAwal(nomorCalendar);
            return "Success";
        }catch(Exception e){
            return "Error";
        }
    }

    @GetMapping("/getColors/{nomorCalendar}")
    public Map<String, Object> getColors(@PathVariable String nomorCalendar){
        try{
            Map<String, Object> response = new HashMap<String, Object>();
            Calendar targetCalendar = calendarService.getCalendar(nomorCalendar);
            response.put("startDate", targetCalendar.getStartRed());
            response.put("red", targetCalendar.getRed());
            response.put("yellow", targetCalendar.getYellow());
            response.put("lastDate", targetCalendar.getLastDate());
            return response;
        }catch(Exception e){
            return null;
        }

    }
    
    @GetMapping("connect/{nomorCalendar}/{nomorPuskesmas}")
    public String connectCalendarPuskesmas (@PathVariable String nomorCalendar, @PathVariable String nomorPuskesmas){
        try{
            Calendar targetCalendar = calendarService.getCalendar(nomorCalendar);
            targetCalendar.setKodePuskesmas(nomorPuskesmas);
            Firestore dbFirestore = FirestoreClient.getFirestore();
            ApiFuture<WriteResult> collectionsApiFuture2 = dbFirestore.collection("Calendar").document(targetCalendar.getNomorKalender()).set(targetCalendar);

            return "Success";
        }catch(Exception e){
            return "Error";
        }
    }

    public int daysBetween(Date d1, Date d2){
        return (int)( (d2.getTime() - d1.getTime()) / (1000 * 60 * 60 * 24));
    }


    //Date nya bentuknya mm-dd-yyyy
    @GetMapping("getRecovery/{nomorCalendar}/{date}")
    public Map<String, Object> getRecovery(@PathVariable String nomorCalendar, @PathVariable String date){
        try{
            Map<String, Object> response = new HashMap<String, Object>();

            Calendar targetCalendar = calendarService.getCalendar(nomorCalendar);
            System.out.println("aaaa " + "masuk method getrecovery");
            Date targetDate = new SimpleDateFormat("MM-dd-yyyy").parse(date);
            System.out.println("aaaa " + "masuk method getrecovery 2 " + targetDate.toString());
            int targetIndex = daysBetween(targetCalendar.getStartRed(), targetDate);
            if(targetIndex < 0) targetIndex = 0;
            System.out.println("aaaa " + "ERROR " + targetIndex);
            String targetRecoveryId = targetCalendar.getListRecovery().get(targetIndex);
            Recovery targetRecovery = recoveryService.getRecovery(targetRecoveryId);

            List<String> listNamaGejala = new ArrayList<String>();
            List<String> listUpdateGejala = new ArrayList<String>();
            List<String> listGejalaString = targetCalendar.getListGejala();
            for(String uuidGejala : listGejalaString){
                Gejala targetGejala = gejalaService.getGejala(uuidGejala);
                listNamaGejala.add(targetGejala.getNamaGejala());
                listUpdateGejala.add(targetGejala.getSequenceUpdate().get(targetIndex));
            }
            response.put("recovery", targetRecovery);
            response.put("listNamaGejala", listNamaGejala);
            response.put("listUpdateGejala", listUpdateGejala);
            return response;

        }catch(Exception e){
            return null;
        }
    }

    @PostMapping("/updateGejalaFromRecovery/{nomorCalendar}")
    public String updateGejalaFromRecovery(@PathVariable String nomorCalendar, @RequestBody ListDTO listDTO){
        try{
            String status = gejalaService.updateGejalaFromRecovery(listDTO.getList());
            return status;
        }catch(Exception e){
            return "Error";
        }
    }

}
