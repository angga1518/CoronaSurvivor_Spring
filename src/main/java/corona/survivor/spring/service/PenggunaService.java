package corona.survivor.spring.service;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.firebase.FirebaseApp;
import com.google.firebase.cloud.FirestoreClient;
import org.springframework.stereotype.Service;

import corona.survivor.spring.model.Pengguna;

import java.util.concurrent.ExecutionException;

//CRUD operations
@Service
public class PenggunaService {

    public static final String COL_NAME = "pengguna";
    Firestore dbFirestore = FirestoreClient.getFirestore();

    public Pengguna createPengguna(Pengguna pengguna) throws InterruptedException, ExecutionException {
        dbFirestore.collection(COL_NAME).document(pengguna.getId()).set(pengguna);
        return pengguna;
    }

    public Pengguna getPengguna(String id) throws InterruptedException, ExecutionException{
        DocumentReference documentReference = dbFirestore.collection(COL_NAME).document(id);
        ApiFuture<DocumentSnapshot> future = documentReference.get();

        DocumentSnapshot document = future.get();

        Pengguna pengguna = null;

        if(document.exists()) {
            pengguna = document.toObject(Pengguna.class);
            return pengguna;
        }else {
            return null;
        }
    }

}