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

public class FirebaseMessages extends FirebaseMessagingService {

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        JSONObject jo = null;
        try {
            jo = new JSONObject(remoteMessage.getNotification().getBody());
        } catch (JSONException e) {
            e.printStackTrace();
        }

        Intent intent = new Intent(getBaseContext(), MainActivity.class);
        PendingIntent pIntent = PendingIntent.getActivity(getBaseContext(), 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        NotificationCompat.Builder b = new NotificationCompat.Builder(getBaseContext(), "foobar");
        try {
            b.setAutoCancel(true)
                    .setDefaults(Notification.DEFAULT_ALL)
                    .setWhen(System.currentTimeMillis())
                    .setTicker("Ticker")
                    .setContentTitle(jo.getString("title"))
                    .setContentText(jo.getString("text"))
                    .setDefaults(Notification.DEFAULT_LIGHTS | Notification.DEFAULT_SOUND)
                    .setContentIntent(pIntent)
                    .setContentInfo("Info");
        } catch (JSONException e) {
            e.printStackTrace();
        }

        Random r = new Random();
        int val = r.nextInt(1000000001);

        NotificationManager manager = (NotificationManager)getBaseContext().getSystemService(Context.NOTIFICATION_SERVICE);
        manager.notify(val, b.build());

        super.onMessageReceived(remoteMessage);
    }
}
