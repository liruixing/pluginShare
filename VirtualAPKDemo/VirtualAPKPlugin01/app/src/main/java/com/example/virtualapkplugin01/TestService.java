package com.example.virtualapkplugin01;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

/**
 * create Time ： 2019-10-16.
 * Author:lrx
 * dec:
 */
public class TestService extends Service {
    private static String TAG = TestService.class.getName();

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.e(TAG,"service oncreate=======");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.e(TAG,"service onStartCommand=======");
        return super.onStartCommand(intent, flags, startId);
    }

}
