package com.example.hookdemo.proxyAbout;

/**
 * create Time ： 2019-10-22.
 * Author:lrx
 * dec:
 */
public class CookManager implements ICook {
    @Override
    public void dealWithFood() {
        System.out.println("food had been dealed with");
    }

    @Override
    public void cook() {
        System.out.println("cook food");

    }
}
