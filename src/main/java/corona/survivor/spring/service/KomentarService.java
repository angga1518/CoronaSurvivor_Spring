package corona.survivor.spring.service;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import com.google.firebase.cloud.FirestoreClient;
import corona.survivor.spring.firebase.FirebaseInitialize;
import corona.survivor.spring.model.Artikel;
import corona.survivor.spring.model.Komentar;
import corona.survivor.spring.model.Pengguna;
import corona.survivor.spring.rest.KomentarPayload;
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
        if(parentKomentar.getListIdReply() == null){
            List<String> listIdReplies = new ArrayList<>();
            parentKomentar.setListIdReply(listIdReplies);
        }
        parentKomentar.getListIdReply().add(komentar.getIdKomentar());
        dbFirestore.collection(COL_NAME).document(parentKomentar.getIdKomentar()).set(parentKomentar);
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

    public List<Komentar> getAllKomentarByIdArtikel(String idArtikel) throws InterruptedException, ExecutionException{
        List<Komentar> listKomentar = new ArrayList<Komentar>();
        Artikel artikel = artikelService.getArtikelById(idArtikel);
        List<String> listIdKomentar = artikel.getListIdComment();
        if(listIdKomentar != null){
            CollectionReference komentar = db.getFirebase().collection(COL_NAME);
            ApiFuture<QuerySnapshot> querySnapshot = komentar.get();
            for (DocumentSnapshot doc : querySnapshot.get().getDocuments()) {
                if (listIdKomentar.contains(doc.getId())) {
                    Komentar komentarTemp = doc.toObject(Komentar.class);
                    listKomentar.add(komentarTemp);
                }
            }
            return listKomentar;
        }else {
            return listKomentar;
        }
    }

    public List<KomentarPayload> getAllKomentarWithReplies(List<Komentar> listKomentar) throws InterruptedException, ExecutionException{
        List<KomentarPayload> komentarPayloadList = new ArrayList<>();
        for(Komentar komentar : listKomentar){
            KomentarPayload komentarPayload = new KomentarPayload();
            komentarPayload.setIdKomentar(komentar.getIdKomentar());
            komentarPayload.setIsi(komentar.getIsi());
            komentarPayload.setJumlahLike(komentar.getJumlahLike());
            komentarPayload.setNamaLengkap(komentar.getNamaLengkap());
            komentarPayload.setTanggalPost(komentar.getTanggalPost());
            List<Komentar> initializeListPayload = new ArrayList<>();
            komentarPayload.setReplies(initializeListPayload);
            for (String idReply : komentar.getListIdReply()){
                Komentar reply = getKomentarById(idReply);
                komentarPayload.getReplies().add(reply);
            }
            komentarPayloadList.add(komentarPayload);
        }
        return komentarPayloadList;
    }

}
