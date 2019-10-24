package com.example.virtualapkmainapp.base;


import com.example.virtualapkmainapp.bean.ApkItem;

import java.util.List;

/**
 * create Time ： 2019-10-17.
 * Author:lrx
 * dec:
 */
public interface Iapk {

    void getApksCallBack(List<ApkItem> dataList);
}
