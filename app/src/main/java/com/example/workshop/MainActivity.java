package com.example.workshop;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageButton;
import android.widget.ImageView;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity {

    ImageView register ;

    ImageView help,shine,shine1;

    public void onBackPressed(){
        Intent a = new Intent(Intent.ACTION_MAIN);
        a.addCategory(Intent.CATEGORY_HOME);
        a.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(a);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        register = findViewById(R.id.register);
        shine = (ImageView) findViewById(R.id.shine);

        ScheduledExecutorService scheduledExecutorService= Executors.newSingleThreadScheduledExecutor();
        scheduledExecutorService.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        ShineStart();
                    }
                    public void ShineStart() {

                        Animation animation = new TranslateAnimation(
                                0, register.getWidth() + shine.getHeight(), 0, 0
                        );
                        animation.setDuration(1500);
                        animation.setFillAfter(false);
                        animation.setInterpolator(new AccelerateDecelerateInterpolator());
                        shine.startAnimation(animation);
                    }
                });

            }
        },1,2, TimeUnit.SECONDS);


        shine1 = (ImageView) findViewById(R.id.shine1);
        help = findViewById(R.id.help);

        scheduledExecutorService.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        ShineStart();
                    }
                    public void ShineStart() {

                        Animation animation = new TranslateAnimation(
                                0, help.getWidth() + shine1.getHeight(), 0, 0
                        );
                        animation.setDuration(1700);
                        animation.setFillAfter(false);
                        animation.setInterpolator(new AccelerateDecelerateInterpolator());
                        shine1.startAnimation(animation);
                    }
                });

            }
        },1,2, TimeUnit.SECONDS);



        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent register = new Intent(MainActivity.this, Registration.class);
                startActivity(register);
            }
        });

        help.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent services = new Intent(MainActivity.this, Services.class);
                startActivity(services);
            }
        });



    }
}