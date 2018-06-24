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

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import androidcrew.bti7251_android.R;
import androidcrew.bti7251_android.lists.DataProvider;
import androidcrew.bti7251_android.lists.Person;

public class CockpitService extends Service{
    public static final String ANDROID_CHANNEL_ID = "GEILIID69";
    List<Person> people = new ArrayList<>();
    DatabaseReference personReference;
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return new SergeyBinder(this);
    }



    @Override
    public void onCreate() {
        super.onCreate();
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        personReference = database.getReference("Person");
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
                    addPerson();
                    Log.i("Cockpit", "I'm still running");
                }
            }
        });
    }

    public void logStuff(String message){
        Log.i("Cockpit", message);
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

    public void addPerson(){
        DataProvider dataProvider = new DataProvider(0);
        personReference.setValue(dataProvider.createRandomPerson(69));
    }
}
