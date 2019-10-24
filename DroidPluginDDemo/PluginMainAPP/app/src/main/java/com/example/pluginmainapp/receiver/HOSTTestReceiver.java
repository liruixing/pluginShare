package com.example.pluginmainapp.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

/**
 * create Time ： 2019-10-16.
 * Author:lrx
 * dec:
 */
public class HOSTTestReceiver extends BroadcastReceiver {
    private String TAG = HOSTTestReceiver.class.getName();

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.e(TAG,"HOST TestReceiver  onReceive");
    }

}
