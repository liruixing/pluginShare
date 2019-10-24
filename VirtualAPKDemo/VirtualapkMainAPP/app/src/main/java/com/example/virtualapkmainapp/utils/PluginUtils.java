package com.example.virtualapkmainapp.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.RemoteException;
import android.widget.Toast;

import com.didi.virtualapk.PluginManager;
import com.example.virtualapkmainapp.bean.ApkItem;

import java.io.File;

/**
 * create Time ： 2019-10-16.
 * Author:lrx
 * dec:
 */
public class PluginUtils {

    public static boolean check(Context ctx, ApkItem apkItem) {
        if (PluginManager.getInstance(ctx).getLoadedPlugin(apkItem.packageInfo.packageName) == null) {
            return false;
        }else{
            return true;
        }
    }

    /**
     * 启动插件
     *
     * @param activity
     * @param packageName
     * @param className
     */
    public static void startActivity(Activity activity, String packageName, String className) {
        Intent intent = new Intent();
        intent.setClassName(packageName, className);
        activity.startActivity(intent);
    }

    /**
     * 应该在线程中安装，此方法仅供测试
     *
     * @param context
     * @param apkPathAndName
     */
    public static boolean installApk(Context context, String apkPathAndName) {
        try {
            PluginManager pluginManager = PluginManager.getInstance(context);
            File file = new File(apkPathAndName);
            if(file.exists()){
                pluginManager.loadPlugin(file);
                return true;
            }else{
                return false;
            }
        }catch (Exception e){
            return false;
        }
    }


    /**
     * 拿到Intent
     *
     * @return
     */
    private static Intent getLaunchehIntent(Context ctx, String pack) {
        PackageManager pm = ctx.getPackageManager();
        Intent intent = pm.getLaunchIntentForPackage(pack);
        return intent;
    }

}
