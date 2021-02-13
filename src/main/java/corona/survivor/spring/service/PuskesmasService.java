package corona.survivor.spring.service;

import com.google.api.core.ApiFunction;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.CollectionReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.QuerySnapshot;
import corona.survivor.spring.firebase.FirebaseInitialize;
import corona.survivor.spring.model.Puskesmas;
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

}
