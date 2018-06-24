package androidcrew.bti7251_android.service;

import android.os.Binder;

public class SergeyBinder extends Binder {
    CockpitService cockpitService;

    public SergeyBinder(CockpitService cockpitService) {
        this.cockpitService = cockpitService;
    }

    public CockpitService getCockpitService() {
        return cockpitService;
    }
}
