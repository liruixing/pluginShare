package com.example.hookdemo;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.example.hookdemo.hook.HookUtil;

/**
 * create Time ： 2019-10-21.
 * Author:lrx
 * dec:
 */
public class MyAPP extends Application {
    private final String TAG = "STAR";
    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        try {
            HookUtil.hookAMS();
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(TAG,"HOOK FAILED");
        }
    }
}
