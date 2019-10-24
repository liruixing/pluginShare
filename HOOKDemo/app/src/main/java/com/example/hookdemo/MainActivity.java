package com.example.hookdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.hookdemo.hook.HookUtil;

public class MainActivity extends AppCompatActivity {
    private final String TAG = MainActivity.class.getName();
    private Button btnHook;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        HookUtil.hookAMS();
        btnHook = findViewById(R.id.btn_hook);
        btnHook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,TestActivity.class));
            }
        });
    }
}
