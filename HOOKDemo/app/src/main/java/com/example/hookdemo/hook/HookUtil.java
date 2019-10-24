package com.example.hookdemo.hook;

import android.app.Instrumentation;
import android.content.Context;
import android.content.pm.PackageManager;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * create Time ： 2019-10-21.
 * Author:lrx
 * dec:
 */
public class HookUtil {

    public static void hookAMS() {
        try {
            Class activityManagerNativeClass = Class.forName("android.app.ActivityManager");
            Field getDefault = activityManagerNativeClass.getDeclaredField("IActivityManagerSingleton");
            getDefault.setAccessible(true);
            Object IActivityManagerSingleton = getDefault.get(null);
            Class<?> singleton = Class.forName("android.util.Singleton");
            Field mInstanceField = singleton.getDeclaredField("mInstance");
            mInstanceField.setAccessible(true);
            Object rawIActivityManager = mInstanceField.get(IActivityManagerSingleton);//gDefault单例对象里面的mInstance值

            //由于IActivityManager.class是隐藏interface所以使用Class.forName("");
            Class IActivityManagerClass = Class.forName("android.app.IActivityManager");
            Object hookOActivityManager = Proxy.newProxyInstance(IActivityManagerClass.getClassLoader(),
                    new Class[]{IActivityManagerClass}, new HookHandler(rawIActivityManager));
            mInstanceField.set(IActivityManagerSingleton,hookOActivityManager);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void hookPMS(Context ctx) {
        try {
            // 获取全局的ActivityThread对象
            Class<?> activityThreadClass = Class.forName("android.app.ActivityThread");
            Method currentActivityThreadMethod = activityThreadClass.getDeclaredMethod("currentActivityThread");
            Object currentActivityThread = currentActivityThreadMethod.invoke(null);//得到ActivityThread对象

            // 获取ActivityThread里面原始的 sPackageManager
            Field sPackageManagerField = activityThreadClass.getDeclaredField("sPackageManager");

            sPackageManagerField.setAccessible(true);
            Object sPackageManager = sPackageManagerField.get(currentActivityThread);

            // 准备好代理对象, 用来替换原始的对象
            Class<?> iPackageManagerInterface = Class.forName("android.content.pm.IPackageManager");
            Object proxy = Proxy.newProxyInstance(iPackageManagerInterface.getClassLoader(),
                    new Class<?>[]{iPackageManagerInterface},
                    new HookHandler(sPackageManager));

            // 1. 替换掉ActivityThread里面的 sPackageManager 字段
            sPackageManagerField.set(currentActivityThread, proxy);

            // 2. 替换 ApplicationPackageManager里面的 mPM对象
            PackageManager pm = ctx.getPackageManager();
            Field mPmField = pm.getClass().getDeclaredField("mPM");
            mPmField.setAccessible(true);
            mPmField.set(pm, proxy);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
