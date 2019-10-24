package com.example.virtualapkmainapp.utils;

import android.app.Activity;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;

import com.example.virtualapkmainapp.bean.ApkItem;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * create Time ： 2019-10-17.
 * Author:lrx
 * dec:
 */
public class ApksUtils {
    private static ApksUtils instance;

    private ApksUtils() {
    }

    public static ApksUtils getInstance(){
        if(instance == null)
            instance = new ApksUtils();

        return instance;
    }

    public void getSDCardApks(final Activity act, final Handler handler){
        new Thread("ApkScanner") {
            @Override
            public void run() {
                List<ApkItem> result = new ArrayList<>();

                File file = Environment.getExternalStorageDirectory();

                List<File> apks = new ArrayList<File>(10);
                File[] files = file.listFiles();
                if (files != null) {
                    for (File apk : files) {
                        if (apk.exists() && apk.getPath().toLowerCase().endsWith(".apk")) {
                            apks.add(apk);
                        }
                    }
                }

                file = new File(Environment.getExternalStorageDirectory(), "360Download");
                if (file.exists() && file.isDirectory()) {
                    File[] files1 = file.listFiles();
                    if (files1 != null) {
                        for (File apk : files1) {
                            if (apk.exists() && apk.getPath().toLowerCase().endsWith(".apk")) {
                                apks.add(apk);
                            }
                        }
                    }
                }
                PackageManager pm = act.getPackageManager();
                for (final File apk : apks) {
                    try {
                        if (apk.exists() && apk.getPath().toLowerCase().endsWith(".apk")) {
                            final PackageInfo info = pm.getPackageArchiveInfo(apk.getPath(), 0);
                            ApkItem apkItem = new ApkItem(act,info,apk.getPath());
                            result.add(apkItem);
                        }
                    } catch (Exception e) {
                    }
                }

                if(handler != null){
                    Message msg = new Message();
                    msg.what = 1;
                    msg.obj = result;
                    handler.sendMessage(msg);
                }
            }
        }.start();

    }

}
