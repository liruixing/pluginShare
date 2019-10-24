package com.example.pluginmainapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import com.example.pluginmainapp.adapter.APKAdapter;
import com.example.pluginmainapp.base.Iapk;
import com.example.pluginmainapp.bean.ApkItem;
import com.example.pluginmainapp.utils.ApksUtils;
import com.example.pluginmainapp.utils.PluginUtils;

import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private ListView mListView;
    private APKAdapter mAPKAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mListView = findViewById(R.id.lv_apkList);

        initData();

    }

    private void initData() {

        ApksUtils.getInstance().getSDCardApks(this,mHandler);
    }

    final Handler mHandler = new Handler(){
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case 1:
                    List<ApkItem> result = (List<ApkItem>) msg.obj;
                    mAPKAdapter = new APKAdapter(MainActivity.this,result,MainActivity.this);
                    mListView.setAdapter(mAPKAdapter);
                    break;
            }
        }
    };


    @Override
    public void onClick(View v) {
        ApkItem apkItem;
        switch (v.getId()){
            case R.id.btn_install:
                apkItem = (ApkItem) v.getTag();
                //先检查
                if(PluginUtils.check(this,apkItem)){
                    Toast.makeText(this,"插件已安装",Toast.LENGTH_LONG).show();
                }else{
                    //安装插件
                    boolean b = PluginUtils.installApk(this,apkItem.apkfile,apkItem.packageInfo.packageName);
                    if(b){
                        Toast.makeText(this,"插件已安装成功",Toast.LENGTH_LONG).show();
                    }else{
                        Toast.makeText(this,"插件安装失败",Toast.LENGTH_LONG).show();
                    }
                }

                break;
            case R.id.btn_uninstall:
                apkItem = (ApkItem) v.getTag();
                //先检查
                if(!PluginUtils.check(this,apkItem)){
                    Toast.makeText(this,"插件未安装",Toast.LENGTH_LONG).show();
                }else{
                    PluginUtils.unInstallApk(this,apkItem.packageInfo.packageName);
                }

                break;
            case R.id.btn_open:
                apkItem = (ApkItem) v.getTag();
                //先检查
                if(!PluginUtils.check(this,apkItem)){
                    Toast.makeText(this,"插件未安装",Toast.LENGTH_LONG).show();
                }else{
                    PluginUtils.startActivity(this,apkItem.packageInfo.packageName);
                }
                break;
        }
    }
}
