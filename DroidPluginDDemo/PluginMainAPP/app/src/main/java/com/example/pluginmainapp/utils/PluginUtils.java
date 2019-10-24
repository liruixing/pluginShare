package com.example.pluginmainapp.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.RemoteException;
import android.util.Log;
import android.widget.Toast;

import com.example.pluginmainapp.bean.ApkItem;
import com.morgoo.droidplugin.pm.PluginManager;
import com.morgoo.helper.compat.PackageManagerCompat;

import java.util.List;

/**
 * create Time ： 2019-10-16.
 * Author:lrx
 * dec:
 */
public class PluginUtils {

    public static boolean check(Context ctx, ApkItem apkItem) {
        Intent intent = getLaunchehIntent(ctx, apkItem.packageInfo.packageName);
        //通过这种方法来判断插件是不是安装不准确,毕竟如果插件是安装在手机里面的话也不为空
        //精准的方法是通过下面PluginManager里面的方法,然后再判断
        if (null != intent) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 启动插件
     *
     * @param activity
     * @param packageName
     */
    public static void startActivity(Activity activity, String packageName) {
        PackageManager pm = activity.getPackageManager();
        Intent intent = getLaunchehIntent(activity, packageName);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.putExtra("Host","主进程的参数.......");
        activity.startActivity(intent);
    }

    /**
     * 删除apk
     *
     * @param activity
     * @param packageName
     */
    public static void unInstallApk(Activity activity, String packageName) {
        if (!PluginManager.getInstance().isConnected()) {
            Toast.makeText(activity, "服务未连接", Toast.LENGTH_SHORT).show();
        } else {
            try {
                PluginManager.getInstance().deletePackage(packageName, 0);
                Toast.makeText(activity, "删除完成", Toast.LENGTH_SHORT).show();
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 应该在线程中安装，此方法仅供测试
     *
     * @param context
     * @param apkPathAndName
     * @param packageName
     */
    public static boolean installApk(Context context, String apkPathAndName, String packageName) {
        if (!PluginManager.getInstance().isConnected()) {
            //installTips(context,"插件服务正在初始化，请稍后再试。。。");
            return false;
        }
        try {
            //如果需要更新插件，则flag 设置为 PackageManagerCompat.INSTALL_REPLACE_EXISTING
            int returnCode = PluginManager.getInstance().installPackage(apkPathAndName, PackageManagerCompat.INSTALL_REPLACE_EXISTING);

            if (returnCode == PackageManagerCompat.INSTALL_SUCCEEDED) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
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
