package com.meizi.haokan.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

public class AdviceService extends Service {
    public AdviceService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
