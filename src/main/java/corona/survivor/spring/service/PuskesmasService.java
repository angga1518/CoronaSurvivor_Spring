package corona.survivor.spring.service;

import com.google.api.core.ApiFunction;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.CollectionReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.QuerySnapshot;
import corona.survivor.spring.firebase.FirebaseInitialize;
import corona.survivor.spring.model.Puskesmas;
import corona.survivor.spring.model.Calendar;
import corona.survivor.spring.model.Patient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

@Service
public class PuskesmasService {

    @Autowired
    FirebaseInitialize db;

    public List<Puskesmas> getAllPuskesmas() throws InterruptedException, ExecutionException{
        List<Puskesmas> puskesmasList = new ArrayList<Puskesmas>();
        CollectionReference puskesmas = db.getFirebase().collection("Puskesmas");
        ApiFuture<QuerySnapshot> querySnapshot= puskesmas.get();
        for (DocumentSnapshot doc : querySnapshot.get().getDocuments()){
            Puskesmas puskesmasTemp = doc.toObject(Puskesmas.class);
            puskesmasList.add(puskesmasTemp);
        }
        return puskesmasList;
    }

    public List<Calendar> getAllCalendar(String kodePuskesmas) throws InterruptedException, ExecutionException{
        System.out.println("debug: " + "masuk");
        List<Calendar> calendarList = new ArrayList<Calendar>();
        CollectionReference allCalendar = db.getFirebase().collection("Calendar");
        System.out.println("debug: " + "masuk2");
        ApiFuture<QuerySnapshot> querySnapshot = allCalendar.get();
        System.out.println("debug: " + "masuk3");
        for (DocumentSnapshot doc : querySnapshot.get().getDocuments()){
            System.out.println("debug: " + "masuk4");
            try{
                Calendar temp = doc.toObject(Calendar.class);
                if (temp.getKodePuskesmas().equals(kodePuskesmas)){
                    calendarList.add(temp);
                }
            }catch(Exception e){
                continue;
            }
        }
        return calendarList;
    }

    public Boolean validateKodePuskesmas(String kodePuskesmas) throws InterruptedException, ExecutionException{
        CollectionReference allPuskesmas = db.getFirebase().collection("Puskesmas");
        ApiFuture<QuerySnapshot> querySnapshot = allPuskesmas.get();
        for (DocumentSnapshot doc : querySnapshot.get().getDocuments()){
            Puskesmas temp = doc.toObject(Puskesmas.class);
            if (temp.getKodePuskesmas().equals(kodePuskesmas)){
                return true;
            }
        }
        return false;
    }

}
