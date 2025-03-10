package corona.survivor.spring.firebase;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.FirestoreOptions;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;

import com.google.firebase.cloud.FirestoreClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.core.io.ClassPathResource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.PostConstruct;

@Service
public class FirebaseInitialize {

    @Value("${app.firebase-configuration-file}")
    private String firebaseConfigPath;

    Logger logger = LoggerFactory.getLogger(FirebaseInitialize.class);

    @PostConstruct
    public void initialize() {
        try {
            FirebaseOptions options = new FirebaseOptions.Builder()
                    .setFirestoreOptions(FirestoreOptions.newBuilder().setTimestampsInSnapshotsEnabled(true)
                            .setCredentials(GoogleCredentials
                                    .fromStream(new ClassPathResource(firebaseConfigPath).getInputStream()))
                            .build())
                    .setCredentials(
                            GoogleCredentials.fromStream(new ClassPathResource(firebaseConfigPath).getInputStream()))
                    .build();
            if (FirebaseApp.getApps().isEmpty()) {
                FirebaseApp.initializeApp(options);
                logger.info("Firebase application has been initialized");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    public Firestore getFirebase(){
        return FirestoreClient.getFirestore();
    }
}