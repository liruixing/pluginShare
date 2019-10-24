package com.example.virtualapkmainapp;

import android.app.Application;
import android.content.Context;

import com.didi.virtualapk.PluginManager;

/**
 * create Time ： 2019-10-19.
 * Author:lrx
 * dec:
 */
public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        PluginManager pluginManager = PluginManager.getInstance(this);
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        PluginManager.getInstance(base).init();

    }
}
