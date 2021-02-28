package corona.survivor.spring.service;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import com.google.firebase.cloud.FirestoreClient;
import corona.survivor.spring.firebase.FirebaseInitialize;
import corona.survivor.spring.model.DataPemberiDonor;
import corona.survivor.spring.model.DataPenerimaDonor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.ExecutionException;

@Service
public class DataPenerimaDonorService {

    public static final String COL_NAME="DataPenerimaDonor";

    @Autowired
    FirebaseInitialize db;

    public DataPenerimaDonor saveDataPendonor(DataPenerimaDonor dataPenerimaDonor) throws InterruptedException, ExecutionException {
        final String uuid = UUID.randomUUID().toString().replace("-", "");
        dataPenerimaDonor.setIdDataPenerimaDonor(uuid);
        Firestore dbFirestore = FirestoreClient.getFirestore();
        ApiFuture<WriteResult> collectionsApiFuture = dbFirestore.collection(COL_NAME).document(uuid).set(dataPenerimaDonor);
        return dataPenerimaDonor;
    }

    public List<DataPenerimaDonor> getDataPenerimaDonorByEmail(String email) throws InterruptedException, ExecutionException {
        List<DataPenerimaDonor> listPenerimaDonor = new ArrayList<DataPenerimaDonor>();
        CollectionReference dataPenerimaDonor = db.getFirebase().collection(COL_NAME);
        ApiFuture<QuerySnapshot> querySnapshot = dataPenerimaDonor.get();
        for (DocumentSnapshot doc : querySnapshot.get().getDocuments()) {
            DataPenerimaDonor dataPenerimaDonorTemp = doc.toObject(DataPenerimaDonor.class);
            if (dataPenerimaDonorTemp.getEmailPendaftar().equals(email)) {
                listPenerimaDonor.add(dataPenerimaDonorTemp);
            }
        }
        return listPenerimaDonor;
    }


    public List<DataPenerimaDonor> getAllDataPenerimaDonor() throws InterruptedException, ExecutionException {
        List<DataPenerimaDonor> listPenerimaDonor = new ArrayList<DataPenerimaDonor>();
        CollectionReference dataPenerimaDonor = db.getFirebase().collection(COL_NAME);
        ApiFuture<QuerySnapshot> querySnapshot = dataPenerimaDonor.get();
        for (DocumentSnapshot doc : querySnapshot.get().getDocuments()) {
            DataPenerimaDonor dataPenerimaDonorTemp = doc.toObject(DataPenerimaDonor.class);
            listPenerimaDonor.add(dataPenerimaDonorTemp);

        }
        return listPenerimaDonor;
    }

    public DataPenerimaDonor getDataPenerimaDonorDetails(String idDataPenerimaDonor) throws InterruptedException,ExecutionException{
        Firestore dbFirestore = FirestoreClient.getFirestore();
        DocumentReference documentReference = dbFirestore.collection(COL_NAME).document(idDataPenerimaDonor);
        ApiFuture<DocumentSnapshot> future = documentReference.get();
        DocumentSnapshot document = future.get();

        DataPenerimaDonor dataPenerimaDonor = null;

        if(document.exists()){
            dataPenerimaDonor = document.toObject(DataPenerimaDonor.class);
            return dataPenerimaDonor;
        }
        else {
            return null;
        }
    }

    public String deleteDataPemberiDonor(String idDataPenerimaDonor) {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        ApiFuture<WriteResult> writeResult = dbFirestore.collection(COL_NAME).document(idDataPenerimaDonor).delete();
        return "Document with DataPemberiDonor ID "+ idDataPenerimaDonor +" has been deleted";
    }
}
