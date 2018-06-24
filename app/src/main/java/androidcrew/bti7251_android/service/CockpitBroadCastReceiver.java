package androidcrew.bti7251_android.service;

import android.app.Notification;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import androidcrew.bti7251_android.R;

public class CockpitBroadCastReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Intent service = new Intent(context, CockpitService.class);
        context.startForegroundService(service);
    }
}
