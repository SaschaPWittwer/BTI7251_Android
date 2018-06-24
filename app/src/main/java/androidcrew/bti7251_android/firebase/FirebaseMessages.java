package androidcrew.bti7251_android.firebase;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.widget.TextView;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Random;

import androidcrew.bti7251_android.MainActivity;
import androidcrew.bti7251_android.R;

public class FirebaseMessages extends FirebaseMessagingService {

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        JSONObject jo = null;
        try {
            jo = new JSONObject(remoteMessage.getNotification().getBody());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        Notification noti = new Notification.Builder(this)
                .setContentTitle("Message Received")
                .setContentText("Subject")
                .setSmallIcon(R.drawable.ic_launcher_background)
                .setChannelId("GEILIID69")
                .build();
        noti.notify();
        super.onMessageReceived(remoteMessage);
    }
}
