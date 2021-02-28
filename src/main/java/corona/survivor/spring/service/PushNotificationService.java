package corona.survivor.spring.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import corona.survivor.spring.firebase.FCMService;
import corona.survivor.spring.model.PushNotificationRequest;

@Service
public class PushNotificationService {

    private Logger logger = LoggerFactory.getLogger(PushNotificationService.class);
    private FCMService fcmService;

    public PushNotificationService(FCMService fcmService) {
        this.fcmService = fcmService;
    }


    public void sendPushNotification(PushNotificationRequest request) {
        try {
            fcmService.sendMessage(request.getData(), request);
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
    }

}