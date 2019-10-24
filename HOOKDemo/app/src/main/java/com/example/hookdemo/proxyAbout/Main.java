package com.example.hookdemo.proxyAbout;

import java.lang.reflect.Proxy;

/**
 * create Time ： 2019-10-22.
 * Author:lrx
 * dec:
 */
public class Main {

    public static void main(String[] args){
        //通过这里可以清楚明了的看出动态代理的调用时序
        CookManager cookManager = new CookManager();
        DynamicProxyHandler dynamicProxyHandler = new DynamicProxyHandler(cookManager);
        ICook iCook =(ICook) Proxy.newProxyInstance(dynamicProxyHandler.
                getClass().getClassLoader(),cookManager.getClass().getInterfaces(),
                dynamicProxyHandler);
        //打印一下代理类的类名
        System.out.println(iCook.getClass().getName());
        iCook.dealWithFood();
        iCook.cook();
    }
}
