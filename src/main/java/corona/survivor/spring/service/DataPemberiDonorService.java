package corona.survivor.spring.service;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import com.google.firebase.cloud.FirestoreClient;
import corona.survivor.spring.firebase.FirebaseInitialize;
import corona.survivor.spring.model.DataPemberiDonor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.ExecutionException;

@Service
public class DataPemberiDonorService {

    public static final String COL_NAME="DataPemberiDonor";

    @Autowired
    FirebaseInitialize db;

    public DataPemberiDonor saveDataPemberiDonor(DataPemberiDonor dataPemberiDonor) throws InterruptedException, ExecutionException {
        final String uuid = UUID.randomUUID().toString().replace("-", "");
        dataPemberiDonor.setIdDataPemberiDonor(uuid);
        Firestore dbFirestore = FirestoreClient.getFirestore();
        ApiFuture<WriteResult> collectionsApiFuture = dbFirestore.collection(COL_NAME).document(uuid).set(dataPemberiDonor);
        return dataPemberiDonor;
    }

    public List<DataPemberiDonor> getDataPemberiDonorByEmail(String email) throws InterruptedException, ExecutionException {
        List<DataPemberiDonor> listPemberiDonor = new ArrayList<DataPemberiDonor>();
        CollectionReference dataPemberiDOnor = db.getFirebase().collection(COL_NAME);
        ApiFuture<QuerySnapshot> querySnapshot = dataPemberiDOnor.get();
        for (DocumentSnapshot doc : querySnapshot.get().getDocuments()) {
            DataPemberiDonor dataPemberiDonorTemp = doc.toObject(DataPemberiDonor.class);
            if (dataPemberiDonorTemp.getEmailPendaftar().equals(email)) {
                listPemberiDonor.add(dataPemberiDonorTemp);
            }
        }
        return listPemberiDonor;
    }

    public List<DataPemberiDonor> getAllDataPemberiDonor() throws InterruptedException, ExecutionException {
        List<DataPemberiDonor> listPemberiDonor = new ArrayList<DataPemberiDonor>();
        CollectionReference dataPemberiDOnor = db.getFirebase().collection(COL_NAME);
        ApiFuture<QuerySnapshot> querySnapshot = dataPemberiDOnor.get();
        for (DocumentSnapshot doc : querySnapshot.get().getDocuments()) {
            DataPemberiDonor dataPemberiDonorTemp = doc.toObject(DataPemberiDonor.class);
            listPemberiDonor.add(dataPemberiDonorTemp);
        }
        return listPemberiDonor;
    }

    public DataPemberiDonor getDataPemberiDonorDetails(String idDataPemberiDonor) throws InterruptedException,ExecutionException{
        Firestore dbFirestore = FirestoreClient.getFirestore();
        DocumentReference documentReference = dbFirestore.collection(COL_NAME).document(idDataPemberiDonor);
        ApiFuture<DocumentSnapshot> future = documentReference.get();
        DocumentSnapshot document = future.get();

        DataPemberiDonor dataPemberiDonor = null;

        if(document.exists()){
            dataPemberiDonor = document.toObject(DataPemberiDonor.class);
            return dataPemberiDonor;
        }
        else {
            return null;
        }
    }

    public String deleteDataPemberiDonor(String idDataPemberiDonor) {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        ApiFuture<WriteResult> writeResult = dbFirestore.collection(COL_NAME).document(idDataPemberiDonor).delete();
        return "Document with DataPemberiDonor ID "+ idDataPemberiDonor +" has been deleted";
    }

}
