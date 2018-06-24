package androidcrew.bti7251_android.service;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.Service;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Build;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

import java.util.UUID;

import androidcrew.bti7251_android.R;

public class CockpitService extends Service{
    public static final String ANDROID_CHANNEL_ID = "GEILIID69";
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }



    @Override
    public void onCreate() {
        super.onCreate();
        createNotificationChannel();
        Log.i("Cockpit", "Cockpit Service started");

        Notification noti = new Notification.Builder(this)
                .setContentTitle("Sergeye Service")
                .setContentText("Subject")
                .setSmallIcon(R.drawable.ic_launcher_background)
                .setChannelId(ANDROID_CHANNEL_ID)
                .build();
        startForeground(1, noti);
        doStuff();
    }

    private void doStuff(){
        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {
                while(true){
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    Log.i("Cockpit", "I'm still running");
                }
            }
        });
    }
    private void createNotificationChannel() {
        // Create the NotificationChannel, but only on API 26+ because
        // the NotificationChannel class is new and not in the support library
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = "Sergey";
            String description = "Sergey Channel";
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel("GEILIID69", name, importance);
            channel.setDescription(description);
            // Register the channel with the system; you can't change the importance
            // or other notification behaviors after this
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }
}
