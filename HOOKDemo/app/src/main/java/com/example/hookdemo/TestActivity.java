package com.example.hookdemo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

/**
 * create Time ： 2019-10-21.
 * Author:lrx
 * dec:
 */
public class TestActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TextView tv = new TextView(this);

        tv.setText("123546576");
        setContentView(tv);

        Intent intent = getIntent();

        String value = intent.getExtras().getString("hookTest");
        tv.setText(value);
    }
}
