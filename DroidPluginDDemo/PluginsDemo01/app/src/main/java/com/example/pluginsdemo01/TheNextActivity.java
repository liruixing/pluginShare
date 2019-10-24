package com.example.pluginsdemo01;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

/**
 * create Time ： 2019-10-16.
 * Author:lrx
 * dec:
 */
public class TheNextActivity extends Activity implements View.OnClickListener {
    private static String TAG = "TheNextActivity";
    private Button btnGoBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_next);
        btnGoBack = findViewById(R.id.btn_back);

        btnGoBack.setOnClickListener(this);

        initData();
    }

    private void initData() {
        Intent intent = getIntent();
        String data = intent.getStringExtra("key");
        Log.e(TAG,"我是参数.....啦啦啦："+data);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_back:
                finish();
                break;
        }
    }

}
