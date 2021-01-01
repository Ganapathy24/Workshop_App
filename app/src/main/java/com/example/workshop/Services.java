package com.example.workshop;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.Toast;

public class Services extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_services);
        FrameLayout workshop = findViewById(R.id.workshoplist);
        FrameLayout batteryshop = findViewById(R.id.batteryshoplist);
        FrameLayout medicals = findViewById(R.id.medicallist);
        FrameLayout ambulance = findViewById(R.id.ambulancelist);
        FrameLayout petrolbunk = findViewById(R.id.petrolbunklist);

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
                Toast.makeText(Services.this, "Batteryshop is clicked", Toast.LENGTH_SHORT).show();
            }
        });
    }
}