package corona.survivor.spring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import corona.survivor.spring.model.Calendar;
import java.util.UUID;
import java.util.concurrent.ExecutionException;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.WriteResult;
import com.google.firebase.cloud.FirestoreClient;

import corona.survivor.spring.firebase.FirebaseInitialize;

@Service
public class CalendarService {

    public static final String COL_NAME="Calendar";

    @Autowired
    FirebaseInitialize db;

    public Calendar saveCalendar(Calendar calendarModel) throws InterruptedException, ExecutionException {
        final String uuid = UUID.randomUUID().toString().replace("-", "");
        calendarModel.setNomorKalender(uuid);
        Firestore dbFirestore = FirestoreClient.getFirestore();
        ApiFuture<WriteResult> collectionsApiFuture = dbFirestore.collection(COL_NAME).document(uuid).set(calendarModel);
        return calendarModel;
    }
}
