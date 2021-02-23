package corona.survivor.spring.service;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import com.google.firebase.cloud.FirestoreClient;
import corona.survivor.spring.firebase.FirebaseInitialize;
import corona.survivor.spring.model.Artikel;
import corona.survivor.spring.model.Pengguna;
import corona.survivor.spring.rest.ArtikelPayload;
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

    public List<ArtikelPayload> getAllArtikel(String email) throws InterruptedException, ExecutionException {
        List<ArtikelPayload> listArtikel = new ArrayList<>();
        CollectionReference artikel = db.getFirebase().collection(COL_NAME);
        Pengguna pengguna = penggunaService.getPengguna(email);
        ApiFuture<QuerySnapshot> querySnapshot = artikel.get();
        for (DocumentSnapshot doc : querySnapshot.get().getDocuments()) {
            Artikel artikelTemp = doc.toObject(Artikel.class);
            ArtikelPayload artikelPayload = addLikedProperty(artikelTemp,pengguna);
            listArtikel.add(artikelPayload);
        }
        return listArtikel;
    }

    public List<ArtikelPayload> getSavedArtikel(String email) throws InterruptedException, ExecutionException {
        List<ArtikelPayload> listArtikel = new ArrayList<>();
        Pengguna pengguna = penggunaService.getPengguna(email);
        List<String> listIdArtikelTersimpan = pengguna.getListIdArtikelDisimpan();
        CollectionReference artikel = db.getFirebase().collection(COL_NAME);
        ApiFuture<QuerySnapshot> querySnapshot = artikel.get();
        for (DocumentSnapshot doc : querySnapshot.get().getDocuments()) {
            if (listIdArtikelTersimpan.contains(doc.getId())) {
                Artikel artikelTemp = doc.toObject(Artikel.class);
                ArtikelPayload artikelPayload = addLikedProperty(artikelTemp,pengguna);
                listArtikel.add(artikelPayload);
            }
        }
        return listArtikel;
    }

    public ArtikelPayload getArtikelByIdForFrontEnd(String idArtikel,String email) throws InterruptedException,ExecutionException{
        Firestore dbFirestore = FirestoreClient.getFirestore();
        DocumentReference documentReference = dbFirestore.collection(COL_NAME).document(idArtikel);
        Pengguna pengguna = penggunaService.getPengguna(email);
        ApiFuture<DocumentSnapshot> future = documentReference.get();
        DocumentSnapshot document = future.get();

        Artikel artikel = null;

        if(document.exists()){
            artikel = document.toObject(Artikel.class);
            ArtikelPayload artikelPayload = addLikedProperty(artikel,pengguna);
            return artikelPayload;
        }
        else {
            return null;
        }
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

    public ArtikelPayload addLikedProperty(Artikel artikel, Pengguna pengguna){
        ArtikelPayload artikelPayload = new ArtikelPayload();
        if(pengguna.getListIdLikedArtikel() != null){
            List<String> listLikedArtikel = pengguna.getListIdLikedArtikel();
            if(pengguna.getListIdLikedArtikel().contains(artikel.getIdArtikel())){
                artikelPayload.setLiked(true);
            }
        }
        artikelPayload.setAuthor(artikel.getAuthor());
        artikelPayload.setDeskripsi(artikel.getDeskripsi());
        artikelPayload.setIdArtikel(artikel.getIdArtikel());
        artikelPayload.setImageUrl(artikel.getImageUrl());
        artikelPayload.setInstitusi(artikel.getInstitusi());
        artikelPayload.setJudul(artikel.getJudul());
        artikelPayload.setJumlahLike(artikel.getJumlahLike());
        artikelPayload.setJumlahView(artikel.getJumlahView());
        artikelPayload.setListIdComment(artikel.getListIdComment());
        artikelPayload.setTanggalPost(artikel.getTanggalPost());
        return artikelPayload;
    }

    public String handleLikedArtikel(ArtikelPayload artikel, String email) throws InterruptedException,ExecutionException{
        Artikel artikel1 = getArtikelById(artikel.getIdArtikel());
        Pengguna pengguna = penggunaService.getPengguna(email);
        Firestore dbFirestore = FirestoreClient.getFirestore();
        if(pengguna.getListIdLikedArtikel() == null){
            List<String> iniatializeList = new ArrayList<>();
            pengguna.setListIdLikedArtikel(iniatializeList);
        }
        if(artikel.isLiked()){
            pengguna.getListIdLikedArtikel().add(artikel.getIdArtikel());
            artikel1.setJumlahLike(artikel1.getJumlahLike() + 1);
            dbFirestore.collection("Artikel").document(artikel1.getIdArtikel()).set(artikel1);
            dbFirestore.collection("Pengguna").document(pengguna.getEmail()).set(pengguna);
            return "Artikel berhasil dilike";
        }else {
            pengguna.getListIdLikedArtikel().remove(artikel.getIdArtikel());
            artikel1.setJumlahLike(artikel1.getJumlahLike() - 1);
            dbFirestore.collection("Artikel").document(artikel1.getIdArtikel()).set(artikel1);
            dbFirestore.collection("Pengguna").document(pengguna.getEmail()).set(pengguna);
            return "Artikel berhasil diunlike";
        }
    }

    public String handleSavedArtikel(ArtikelPayload artikel,String email) throws InterruptedException,ExecutionException{
        Pengguna pengguna = penggunaService.getPengguna(email);
        if(pengguna.getListIdLikedArtikel() == null){
            List<String> iniatializeList = new ArrayList<>();
            pengguna.setListIdArtikelDisimpan(iniatializeList);
        }
        pengguna.getListIdArtikelDisimpan().add(artikel.getIdArtikel());
        Firestore dbFirestore = FirestoreClient.getFirestore();
        dbFirestore.collection("Pengguna").document(pengguna.getEmail()).set(pengguna);
        return "Artikel berhasil disimpan";
    }
}
