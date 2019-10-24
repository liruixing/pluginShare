package com.example.pluginsdemo01;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private String TAG = MainActivity.class.getName();

    private Button mBtnGoToNext;
    private Button mBtnStartService;
    private Button mBtnStartReceiver;
    private Button mBtnStartHostReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mBtnGoToNext = findViewById(R.id.btn_gotonext);
        mBtnStartService = findViewById(R.id.btn_startService);
        mBtnStartReceiver = findViewById(R.id.btn_startReceiver);
        mBtnStartHostReceiver = findViewById(R.id.btn_startHostReceiver);

        mBtnStartReceiver.setOnClickListener(this);
        mBtnStartService.setOnClickListener(this);
        mBtnGoToNext.setOnClickListener(this);
        mBtnStartHostReceiver.setOnClickListener(this);
        initData();
    }


    private void initData() {
        Intent intent = getIntent();
        if(intent == null)return;

        String data = intent.getStringExtra("Host");
        Log.e(TAG,"接收宿主进程的参数："+data);
    }
    @Override
    public void onClick(View v) {
        Intent intent;
        switch (v.getId()){
            case R.id.btn_gotonext:
                intent = new Intent(this,TheNextActivity.class);
                intent.putExtra("key","我是参数");
                startActivity(intent);
                break;
            case R.id.btn_startService:
                intent = new Intent(this,TestService.class);
                startService(intent);
                break;
            case R.id.btn_startReceiver:
                intent = new Intent();
                intent.setAction("com.test.testReceiver");
                sendBroadcast(intent);
                break;
            case R.id.btn_startHostReceiver:
                intent = new Intent();
                intent.setAction("com.example.pluginmainapp.HostReceiver");
                sendBroadcast(intent);
                break;
        }
    }


}
