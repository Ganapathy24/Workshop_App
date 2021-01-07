package com.example.workshop;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Services extends AppCompatActivity {

    ImageView workshop,shine,shine1,shine2,shine3,battery,ambulance,petrolbunk;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_services);


        workshop = (ImageView) findViewById(R.id.workshop);
        shine = (ImageView) findViewById(R.id.shine);

        ScheduledExecutorService scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();
        scheduledExecutorService.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        ShineStart();
                    }
                    public void ShineStart(){

                        Animation animation=new TranslateAnimation(
                                0,workshop.getWidth()+shine.getHeight(),0,0
                        );
                        animation.setDuration(1500);
                        animation.setFillAfter(false);
                        animation.setInterpolator(new AccelerateDecelerateInterpolator());
                        shine.startAnimation(animation);
                    }

                });

            }}, 1, 2, TimeUnit.SECONDS);

        battery = (ImageView) findViewById(R.id.battery);
        shine1 = (ImageView) findViewById(R.id.shine1);

        scheduledExecutorService.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        ShineStart();
                    }
                    public void ShineStart(){

                        Animation animation=new TranslateAnimation(
                                0,battery.getWidth()+shine1.getHeight(),0,0
                        );
                        animation.setDuration(1500);
                        animation.setFillAfter(false);
                        animation.setInterpolator(new AccelerateDecelerateInterpolator());
                        shine1.startAnimation(animation);
                    }

                });

            }}, 1, 2, TimeUnit.SECONDS);

        ambulance = (ImageView) findViewById(R.id.ambulance);
        shine2 = (ImageView) findViewById(R.id.shine2);

        scheduledExecutorService.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        ShineStart();
                    }
                    public void ShineStart(){

                        Animation animation=new TranslateAnimation(
                                0,ambulance.getWidth()+shine2.getHeight(),0,0
                        );
                        animation.setDuration(1500);
                        animation.setFillAfter(false);
                        animation.setInterpolator(new AccelerateDecelerateInterpolator());
                        shine2.startAnimation(animation);
                    }

                });

            }}, 1, 2, TimeUnit.SECONDS);
        petrolbunk = (ImageView) findViewById(R.id.petrolbunk);
        shine3 = (ImageView) findViewById(R.id.shine3);

        scheduledExecutorService.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        ShineStart();
                    }
                    public void ShineStart(){

                        Animation animation=new TranslateAnimation(
                                0,petrolbunk.getWidth()+shine3.getHeight(),0,0
                        );
                        animation.setDuration(1500);
                        animation.setFillAfter(false);
                        animation.setInterpolator(new AccelerateDecelerateInterpolator());
                        shine3.startAnimation(animation);
                    }

                });

            }}, 1, 2, TimeUnit.SECONDS);


        LinearLayout workshop = findViewById(R.id.workshoplist);
        LinearLayout batteryshop = findViewById(R.id.batteryshoplist);
        LinearLayout ambulance = findViewById(R.id.ambulancelist);
        LinearLayout petrolbunk = findViewById(R.id.petrolbunklist);

        workshop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Services.this, ServiceList.class);
                intent.putExtra("category", "WorkShop");
                startActivity(intent);
                Toast.makeText(Services.this, "Workshop is clicked", Toast.LENGTH_SHORT).show();
            }
        });
        batteryshop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Services.this, ServiceList.class);
                intent.putExtra("category", "BatteryShop");
                startActivity(intent);
                Toast.makeText(Services.this, "Batteryshop is clicked", Toast.LENGTH_SHORT).show();
            }
        });
        petrolbunk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Services.this, ServiceList.class);
                intent.putExtra("category", "PetrolBunk");
                startActivity(intent);
                Toast.makeText(Services.this, "petrolpump is clicked", Toast.LENGTH_SHORT).show();
            }
        });
        ambulance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Services.this, ServiceList.class);
                intent.putExtra("category", "Ambulance");
                startActivity(intent);
                Toast.makeText(Services.this, "Ambulance is clicked", Toast.LENGTH_SHORT).show();
            }
        });
    }
}