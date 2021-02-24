package corona.survivor.spring.service;

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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import corona.survivor.spring.firebase.FirebaseInitialize;
import corona.survivor.spring.model.Calendar;
import corona.survivor.spring.model.Gejala;
import corona.survivor.spring.model.GejalaDTO;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.WriteResult;
import com.google.firebase.cloud.FirestoreClient;

import corona.survivor.spring.firebase.FirebaseInitialize;

import corona.survivor.spring.service.CalendarService;

@Service
public class GejalaService {
    public static final String COL_NAME="Gejala";

    @Autowired
    FirebaseInitialize db;

    @Autowired
    CalendarService calendarService;

    public Gejala saveGejala(Gejala gejalaModel) throws InterruptedException, ExecutionException {
        final String uuid = UUID.randomUUID().toString().replace("-", "");
        gejalaModel.setNomorGejala(uuid);
        Firestore dbFirestore = FirestoreClient.getFirestore();
        ApiFuture<WriteResult> collectionsApiFuture;
        collectionsApiFuture = dbFirestore.collection(COL_NAME).document(uuid).set(gejalaModel);

        Calendar calendarModel = calendarService.getCalendar(gejalaModel.getNomorKalender());
        boolean checkGejala = checkGejala(gejalaModel.getNamaGejala(), calendarModel);

        if(checkGejala == true){
            calendarModel.getListGejala().add(gejalaModel.getNomorGejala());

            if(calendarModel.getAllGejalaAwalAdded() == false){
                // calendarService.calculateSembuhAwal(calendarModel.getNomorKalender());
            }else{
                calendarService.calculateSembuhDelay(calendarModel.getNomorKalender(), uuid);
            }
    
            collectionsApiFuture = dbFirestore.collection(COL_NAME).document(gejalaModel.getNomorGejala()).set(gejalaModel); 
            collectionsApiFuture = dbFirestore.collection("Calendar").document(gejalaModel.getNomorKalender()).set(calendarModel); 
            System.out.println("aaaa ditambahkan: " + gejalaModel.getNomorGejala());
            
            return gejalaModel;
        }else{
            return null;
        }

    }

    public boolean checkGejala(String newGejala, Calendar targetCalendar){
        try{
            List<String> targetListGejala = targetCalendar.getListGejala();
            for(String nomorGejala : targetListGejala){
                String namaGejala = getGejala(nomorGejala).getNamaGejala();
                if(namaGejala.equals(newGejala)) return false;
            }
            return true;

        }catch(Exception e){
            return false;
        }
    }

    public Gejala getGejala(String uuid) throws InterruptedException, ExecutionException{
        try{    
        CollectionReference allGejala = db.getFirebase().collection("Gejala");
        ApiFuture<QuerySnapshot> querySnapshot = allGejala.get();
        for (DocumentSnapshot doc : querySnapshot.get().getDocuments()){
            Gejala temp = doc.toObject(Gejala.class);
            if (temp.getNomorGejala().equals(uuid)){
                return temp;
            }
        }
        return null;
        }catch(Exception e){
            return null;
        }
    }

    public String updateGejalaFromRecovery(List<GejalaDTO> listUpdate) throws InterruptedException, ExecutionException{
        try{
            Firestore dbFirestore = FirestoreClient.getFirestore();
            ApiFuture<WriteResult> collectionsApiFuture;
            for(GejalaDTO temp : listUpdate){
                Gejala targetGejala = getGejala(temp.getUuid());
                targetGejala.getSequenceDate().add(temp.getDate());
                targetGejala.getSequenceUpdate().add(temp.getUpdate());
                collectionsApiFuture = dbFirestore.collection(COL_NAME).document(temp.getUuid()).set(targetGejala);
            }
            return "Berhasil";
        }catch(Exception e){
            return "Error";
        }
    }
}
