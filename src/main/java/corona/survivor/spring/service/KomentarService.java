package corona.survivor.spring.service;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import com.google.firebase.cloud.FirestoreClient;
import corona.survivor.spring.firebase.FirebaseInitialize;
import corona.survivor.spring.model.Artikel;
import corona.survivor.spring.model.Komentar;
import corona.survivor.spring.model.Pengguna;
import corona.survivor.spring.rest.KomentarPayload;
import corona.survivor.spring.rest.ListKomentarPayload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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

    @Autowired
    PenggunaService penggunaService;

    public Komentar createKomentar(Komentar komentar, String idArtikel) throws InterruptedException,ExecutionException{
        final String uuid = UUID.randomUUID().toString().replace("-", "");
        komentar.setIdKomentar(uuid);
        Firestore dbFirestore = FirestoreClient.getFirestore();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/YYYY hh:mm a");
        String dateString = sdf.format(new Date());
        komentar.setTanggalPost(dateString);
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
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/YYYY hh:mm a");
        String dateString = sdf.format(new Date());
        komentar.setTanggalPost(dateString);
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
        if(!(listIdKomentar.isEmpty())){
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

    public List<KomentarPayload> getAllKomentarWithReplies(List<Komentar> listKomentar, String email) throws InterruptedException, ExecutionException{
        List<KomentarPayload> komentarPayloadList = new ArrayList<>();
        Pengguna pengguna = penggunaService.getPengguna(email);
        for(Komentar komentar : listKomentar){
            KomentarPayload komentarPayload = new KomentarPayload();
            komentarPayload.setIdKomentar(komentar.getIdKomentar());
            komentarPayload.setIsi(komentar.getIsi());
            komentarPayload.setJumlahLike(komentar.getJumlahLike());
            komentarPayload.setNamaLengkap(komentar.getNamaLengkap());
            komentarPayload.setTanggalPost(komentar.getTanggalPost());
            List<Komentar> initializeListPayload = new ArrayList<>();
            komentarPayload.setReplies(initializeListPayload);
            if(!(pengguna.getListIdLikedKomentar().isEmpty())){
                List<String> listLikedKomentar = pengguna.getListIdLikedKomentar();
                if(listLikedKomentar.contains(komentar.getIdKomentar())){
                    komentarPayload.setLiked(true);
                }
            }
            if(komentar.getListIdReply() != null){
                for (String idReply : komentar.getListIdReply()){
                    Komentar reply = getKomentarById(idReply);
                    komentarPayload.getReplies().add(reply);
                }
            }
            komentarPayloadList.add(komentarPayload);
        }
        return komentarPayloadList;
    }

    public String handleLikedKomentar(ListKomentarPayload listKomentarPayload, String email) throws InterruptedException,ExecutionException{
        for(KomentarPayload komentarPayload : listKomentarPayload.getListKomentarPayload()){
            Komentar komentar = getKomentarById(komentarPayload.getIdKomentar());
            Pengguna pengguna = penggunaService.getPengguna(email);
            Firestore dbFirestore = FirestoreClient.getFirestore();
            if(pengguna.getListIdLikedKomentar() == null){
                List<String> iniatializeList = new ArrayList<>();
                pengguna.setListIdLikedKomentar(iniatializeList);
            }
            if(komentarPayload.isLiked()){
                pengguna.getListIdLikedKomentar().add(komentarPayload.getIdKomentar());
                komentar.setJumlahLike(komentar.getJumlahLike() + 1);
                dbFirestore.collection("Komentar").document(komentar.getIdKomentar()).set(komentar);
                dbFirestore.collection("Pengguna").document(pengguna.getEmail()).set(pengguna);
            }else {
                pengguna.getListIdLikedKomentar().remove(komentarPayload.getIdKomentar());
                komentar.setJumlahLike(komentar.getJumlahLike() - 1);
                dbFirestore.collection("Komentar").document(komentar.getIdKomentar()).set(komentar);
                dbFirestore.collection("Pengguna").document(pengguna.getEmail()).set(pengguna);
            }
        }
        return "Berhasil di handle";
    }
}
