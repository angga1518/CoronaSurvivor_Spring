package corona.survivor.spring.service;

import java.util.Date;
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
import corona.survivor.spring.model.*;
import corona.survivor.spring.model.Gejala;
import corona.survivor.spring.model.Recovery;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.WriteResult;
import com.google.firebase.cloud.FirestoreClient;

import corona.survivor.spring.firebase.FirebaseInitialize;

import corona.survivor.spring.service.*;

@Service
public class RecoveryService {
    public static final String COL_NAME="Recovery";

    @Autowired
    FirebaseInitialize db;

    @Autowired
    CalendarService calendarService;

    @Autowired
    PushNotificationService pnService;

    @Autowired 
    PenggunaService penggunaService;

    public Recovery saveRecovery(Recovery recoveryModel) throws InterruptedException, ExecutionException {
        final String uuid = UUID.randomUUID().toString().replace("-", "");
        recoveryModel.setNomorRecovery(uuid);
        Firestore dbFirestore = FirestoreClient.getFirestore();
        Calendar calendarModel = calendarService.getCalendar(recoveryModel.getNomorKalender());

        //Set statusnya duluu
        if (calendarModel.getKodePuskesmas() != null){
            recoveryModel.setStatus(2);
        }else{
            recoveryModel.setStatus(1);
        }

        ApiFuture<WriteResult> collectionsApiFuture = dbFirestore.collection(COL_NAME).document(uuid).set(recoveryModel);

        calendarModel.getListRecovery().add(recoveryModel.getNomorRecovery());
        calendarModel.setLastDate(recoveryModel.getTanggalKalender());

        ApiFuture<WriteResult> collectionsApiFuture2 = dbFirestore.collection("Calendar").document(calendarModel.getNomorKalender()).set(calendarModel);
        return recoveryModel;
    }

    public Recovery getRecovery(String uuid) throws InterruptedException, ExecutionException{
        try{    
            CollectionReference allRecovery = db.getFirebase().collection("Recovery");
            System.out.println("debug: " + "masuk");
            ApiFuture<QuerySnapshot> querySnapshot = allRecovery.get();
        for (DocumentSnapshot doc : querySnapshot.get().getDocuments()){
            Recovery temp = doc.toObject(Recovery.class);
            if (temp.getNomorRecovery().equals(uuid)){
                return temp;
            }
        }
        return null;
        }catch(Exception e){
            return null;
        }
    }

    public void updateRecovery(Recovery a){
        Firestore dbFirestore = FirestoreClient.getFirestore();
        ApiFuture<WriteResult> collectionsApiFuture = dbFirestore.collection(COL_NAME).document(a.getNomorRecovery()).set(a);
    }

    public Recovery updateRecovery(String uuid, String update) throws InterruptedException, ExecutionException{
        try{
            Recovery recoveryModel = getRecovery(uuid);
            recoveryModel.setFeedback(update);
            recoveryModel.setStatus(3);
            PushNotificationRequest req = new PushNotificationRequest("Perkembangan Anda telah diberi feedback oleh puskesmas!", 
            "Buka app untuk melihat feedback Anda", "MyCalendar");
            Pengguna pengguna = penggunaService.getPengguna(recoveryModel.getEmailPengguna().strip());

            req.setToken(pengguna.getToken());
            pnService.sendPushNotification(req);

            Firestore dbFirestore = FirestoreClient.getFirestore();
            ApiFuture<WriteResult> collectionsApiFuture = dbFirestore.collection(COL_NAME).document(uuid).set(recoveryModel);
            return recoveryModel;
        }catch(Exception e){
            return null;
        }
    }
}
