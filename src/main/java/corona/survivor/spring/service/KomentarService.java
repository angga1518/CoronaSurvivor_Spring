package corona.survivor.spring.service;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.WriteResult;
import com.google.firebase.cloud.FirestoreClient;
import corona.survivor.spring.firebase.FirebaseInitialize;
import corona.survivor.spring.model.Artikel;
import corona.survivor.spring.model.Komentar;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.ExecutionException;

@Service
public class KomentarService {

    public static final String COL_NAME="Komentar";

    @Autowired
    FirebaseInitialize db;

    @Autowired
    ArtikelService artikelService;

    public Komentar createKomentar(Komentar komentar, String idArtikel) throws InterruptedException,ExecutionException{
        final String uuid = UUID.randomUUID().toString().replace("-", "");
        komentar.setIdKomentar(uuid);
        Firestore dbFirestore = FirestoreClient.getFirestore();
        Artikel artikel = artikelService.getArtikelById(idArtikel);
        if(artikel.getListIdComment() == null){
            List<String> listIdComment = new ArrayList<>();
            artikel.setListIdComment(listIdComment);
        }
        artikel.getListIdComment().add(komentar.getIdKomentar());
        dbFirestore.collection("Artikel").document(artikel.getIdArtikel()).set(artikel);
        ApiFuture<WriteResult> collectionsApiFuture = dbFirestore.collection(COL_NAME).document(uuid).set(komentar);
        return komentar;
    }

    public Komentar createReplies(Komentar komentar, String idParentKomentar) throws InterruptedException,ExecutionException{
        final String uuid = UUID.randomUUID().toString().replace("-", "");
        komentar.setIdKomentar(uuid);
        Firestore dbFirestore = FirestoreClient.getFirestore();
        Komentar parentKomentar = getKomentarById(idParentKomentar);
        parentKomentar.getListIdReply().add(uuid);
        dbFirestore.collection(COL_NAME).document(parentKomentar.getIdKomentar()).set(komentar);
        ApiFuture<WriteResult> collectionsApiFuture = dbFirestore.collection(COL_NAME).document(uuid).set(komentar);
        return komentar;
    }

    public Komentar getKomentarById(String idKomentar)throws InterruptedException,ExecutionException{
        Firestore dbFirestore = FirestoreClient.getFirestore();
        DocumentReference documentReference = dbFirestore.collection(COL_NAME).document(idKomentar);
        ApiFuture<DocumentSnapshot> future = documentReference.get();
        DocumentSnapshot document = future.get();

        Komentar komentar = null;

        if(document.exists()){
            komentar = document.toObject(Komentar.class);
            return komentar;
        }
        else {
            return null;
        }
    }

}
