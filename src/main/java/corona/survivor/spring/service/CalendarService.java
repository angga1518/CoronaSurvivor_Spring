package corona.survivor.spring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import corona.survivor.spring.model.Calendar;
import corona.survivor.spring.model.Gejala;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.ExecutionException;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.CollectionReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.QuerySnapshot;
import com.google.cloud.firestore.WriteResult;
import com.google.firebase.cloud.FirestoreClient;

import corona.survivor.spring.firebase.FirebaseInitialize;

@Service
public class CalendarService {

    public static final String COL_NAME="Calendar";

    @Autowired
    FirebaseInitialize db;

    @Autowired
    GejalaService gejalaService;

    public Calendar saveCalendar(Calendar calendarModel) throws InterruptedException, ExecutionException {
        final String uuid = UUID.randomUUID().toString().replace("-", "");
        calendarModel.setNomorKalender(uuid);
        Firestore dbFirestore = FirestoreClient.getFirestore();
        ApiFuture<WriteResult> collectionsApiFuture = dbFirestore.collection(COL_NAME).document(uuid).set(calendarModel);
        return calendarModel;
    }

    public Calendar getCalendar(String uuid) throws InterruptedException, ExecutionException{
        try{    
        CollectionReference allCalendar = db.getFirebase().collection("Calendar");
        ApiFuture<QuerySnapshot> querySnapshot = allCalendar.get();
        
        for (DocumentSnapshot doc : querySnapshot.get().getDocuments()){
            Calendar temp = doc.toObject(Calendar.class);
            String t1 = temp.getNomorKalender().strip();
            String t2 = uuid.strip();
            System.out.println("aaaa t1 " + t1);
            System.out.println("aaaa t2 " + t2);
            System.out.println("aaaa t1 " + t1.length());
            System.out.println("aaaa t2 " + t2.length());
            System.out.println("aaaa t1 == t2 " + t1.compareTo(t2));
            if (t1.equals(t2)){
                return temp;
            }
        }
        System.out.println("aaaa " + "ooopss");
        return null;
        }catch(Exception e){
            System.out.println("aaaa " + "ooopss2");
            return null;
        }
    }

    public List<Gejala> getAllGejala(String uuidCalendar) throws InterruptedException, ExecutionException{
        try{
            List<Gejala> listGejala = new ArrayList<Gejala>();
            Calendar targetCalendar = getCalendar(uuidCalendar);
            for(String nomorGejala : targetCalendar.getListGejala()){
                Gejala temp = gejalaService.getGejala(nomorGejala);
                if (temp != null){
                    listGejala.add(temp);
                }
            }
        return listGejala;
        }catch(Exception e){
            return null;
        }

    }

    public void calculateSembuhAwal(String uuidCalendar) throws InterruptedException, ExecutionException{
        try{
            System.out.println("aaaa" + "masukk");
            System.out.println("aaaa" + uuidCalendar);
            Calendar targetCalendar = getCalendar(uuidCalendar);
            
            if (targetCalendar.getListGejala().size() == 0){
                System.out.println("aaaa" + 1);
                targetCalendar.setStartRed(targetCalendar.getTanggalPositif());
                targetCalendar.setRed(10);
                targetCalendar.setYellow(0);
                targetCalendar.setStatus(1);
            }else{
                List<Gejala> listGejala = getAllGejala(uuidCalendar);
                for(Gejala gejala : listGejala){
                    if(gejala.getNamaGejala().contains("sesak") || gejala.getNamaGejala().contains("Sesak")){
                        targetCalendar.setStartRed(targetCalendar.getTanggalMunculGejala());
                        targetCalendar.setRed(20);
                        targetCalendar.setYellow(3);
                        targetCalendar.setStatus(3);
                        System.out.println("aaaa" + 3);
                        break;
                    }
                }
                targetCalendar.setStartRed(targetCalendar.getTanggalPositif());
                targetCalendar.setRed(10);
                targetCalendar.setYellow(3);
                targetCalendar.setStatus(2);
                System.out.println("aaaa" + 2);
            }
        
            Date calendarDate = targetCalendar.getStartRed();
            int dayIndex = calendarDate.getDay();
            targetCalendar.setNextIndex(dayIndex);

        Firestore dbFirestore = FirestoreClient.getFirestore();
        ApiFuture<WriteResult> collectionsApiFuture = dbFirestore.collection(COL_NAME).document(targetCalendar.getNomorKalender()).set(targetCalendar);
        }catch(Exception e){
            System.out.println("aaa " + e);
        }

    }

    public void calculateSembuhDelay(String nomorKalender, String nomorGejala) throws InterruptedException, ExecutionException{
        try{
            Calendar targetCalendar = getCalendar(nomorKalender);
            Gejala targetGejala = gejalaService.getGejala(nomorGejala);

            if(targetGejala.getNamaGejala().toLowerCase().contains("demam") || targetGejala.getNamaGejala().toLowerCase().contains("panas")){
                targetCalendar.setIsDelayed(true);
                targetCalendar.setRed(targetCalendar.getRed()+10);
                targetCalendar.setYellow(3);
                targetCalendar.setStatus(2);
            }

            if(targetGejala.getNamaGejala().toLowerCase().contains("sesak") || targetGejala.getNamaGejala().toLowerCase().contains("sesek")){
                targetCalendar.setIsDelayed(true);
                targetCalendar.setRed(targetCalendar.getRed()+20);
                targetCalendar.setYellow(3);
                targetCalendar.setStatus(3);
            }

            Firestore dbFirestore = FirestoreClient.getFirestore();
            ApiFuture<WriteResult> collectionsApiFuture = dbFirestore.collection(COL_NAME).document(targetCalendar.getNomorKalender()).set(targetCalendar);
        }catch(Exception e){

        }
    }

    public void addPuskesmas(String kodePuskesmas, String nomorKalender) throws InterruptedException, ExecutionException{
        try{
            Calendar targetCalendar = getCalendar(nomorKalender);
            targetCalendar.setKodePuskesmas(kodePuskesmas);
            Firestore dbFirestore = FirestoreClient.getFirestore();
            ApiFuture<WriteResult> collectionsApiFuture = dbFirestore.collection(COL_NAME).document(targetCalendar.getNomorKalender()).set(targetCalendar);
        }catch(Exception e){

        }

    }
}
