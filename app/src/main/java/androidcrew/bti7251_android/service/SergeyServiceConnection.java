package androidcrew.bti7251_android.service;

import android.app.Service;
import android.content.ComponentName;
import android.os.IBinder;
import android.util.Log;

public class SergeyServiceConnection implements android.content.ServiceConnection {

    CockpitService cockpitService;
    @Override
    public void onServiceConnected(ComponentName name, IBinder service) {
        SergeyBinder binder = (SergeyBinder)service;
        cockpitService = binder.getCockpitService();
        Log.i("Cockpit", "Juhuuuuuuu");
    }

    @Override
    public void onServiceDisconnected(ComponentName name) {


    }

    public void logStuff(String message){
        cockpitService.logStuff(message);
    }

    public CockpitService getCockpitService(){
        return cockpitService;
    }


}
