package com.example.workshop;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;


public class splashscreen extends AppCompatActivity {
    private static  int sleepTimer=1500;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.splashscreen);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

Intent i=new Intent(splashscreen.this, MainActivity.class);
startActivity(i);

            }
        },sleepTimer);
    }
}
