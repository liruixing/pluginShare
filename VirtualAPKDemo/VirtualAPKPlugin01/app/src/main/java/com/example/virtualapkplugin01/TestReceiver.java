package com.example.virtualapkplugin01;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

/**
 * create Time ： 2019-10-16.
 * Author:lrx
 * dec:
 */
public class TestReceiver extends BroadcastReceiver {
    private String TAG = TestReceiver.class.getName();

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.e(TAG,"Plugins TestReceiver  onReceive");
    }

}
