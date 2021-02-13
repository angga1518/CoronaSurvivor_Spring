package corona.survivor.spring.service;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import com.google.firebase.cloud.FirestoreClient;
import corona.survivor.spring.firebase.FirebaseInitialize;
import corona.survivor.spring.model.Artikel;
import corona.survivor.spring.model.Pengguna;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

@Service
public class ArtikelService {

    public static final String COL_NAME="Artikel";

    @Autowired
    FirebaseInitialize db;

    @Autowired
    PenggunaService penggunaService;

    public List<Artikel> getAllArtikel() throws InterruptedException, ExecutionException {
        List<Artikel> listArtikel = new ArrayList<Artikel>();
        CollectionReference artikel = db.getFirebase().collection(COL_NAME);
        ApiFuture<QuerySnapshot> querySnapshot = artikel.get();
        for (DocumentSnapshot doc : querySnapshot.get().getDocuments()) {
            Artikel artikelTemp = doc.toObject(Artikel.class);
            listArtikel.add(artikelTemp);
        }
        return listArtikel;
    }

    public List<Artikel> getSavedArtikel(String email) throws InterruptedException, ExecutionException {
        List<Artikel> listArtikel = new ArrayList<Artikel>();
        Pengguna pengguna = penggunaService.getPengguna(email);
        List<String> listIdArtikelTersimpan = pengguna.getListIdArtikelDisimpan();
        CollectionReference artikel = db.getFirebase().collection(COL_NAME);
        ApiFuture<QuerySnapshot> querySnapshot = artikel.get();
        for (DocumentSnapshot doc : querySnapshot.get().getDocuments()) {
            if (listIdArtikelTersimpan.contains(doc.getId())) {
                Artikel artikelTemp = doc.toObject(Artikel.class);
                listArtikel.add(artikelTemp);
            }
        }
        return listArtikel;
    }

    public Artikel getArtikelById(String idArtikel) throws InterruptedException,ExecutionException{
        Firestore dbFirestore = FirestoreClient.getFirestore();
        DocumentReference documentReference = dbFirestore.collection(COL_NAME).document(idArtikel);
        ApiFuture<DocumentSnapshot> future = documentReference.get();
        DocumentSnapshot document = future.get();

        Artikel artikel = null;

        if(document.exists()){
            artikel = document.toObject(Artikel.class);
            return artikel;
        }
        else {
            return null;
        }
    }
}
