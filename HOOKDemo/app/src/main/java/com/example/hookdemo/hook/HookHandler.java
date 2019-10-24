package com.example.hookdemo.hook;

import android.content.Intent;
import android.util.Log;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * create Time ： 2019-10-21.
 * Author:lrx
 * dec:
 */
class HookHandler implements InvocationHandler {

    private static final String TAG = "HookHandler";

    private Object mBase;

    public HookHandler(Object base) {
        mBase = base;
    }
    /**
     * @param proxy     代理本身（由于返回this并不是代理本身，所以需要传入proxy）
     * @param method    使用代理执行方法的Method对象
     * @param args      使用代理执行方法传入的参数
     * @return
     */
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Log.e(TAG, "进来这里了method:" + method.getName() + " 其他参数是:" + Arrays.toString(args));

        if("startActivity".equals(method.getName())){//跳转
            // 第三个参数是intent
            Intent intent = (Intent)args[2];
            intent.putExtra("hookTest","testValue");
            args[2] = intent;
        }

        return method.invoke(mBase, args);
    }
}
