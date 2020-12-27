package com.example.workshop;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

public class DetailsConfirmation extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_confirmation);
        String name = getIntent().getStringExtra("name");
        String shopname = getIntent().getStringExtra("shopname");
        String category = getIntent().getStringExtra("category");
        String phoneNumber = getIntent().getStringExtra("phonenumber");

        Log.d("Data ", phoneNumber.toString());

        TextView ownerName = findViewById(R.id.oName);
        TextView sName = findViewById(R.id.sName);
        TextView categortyText = findViewById(R.id.categoryId);
        TextView phone = findViewById(R.id.pNumber);

        ownerName.setText(name);
        sName.setText(shopname);
        categortyText.setText(category);
        phone.setText(phoneNumber);



        Toast.makeText(DetailsConfirmation.this,
                "Category - " + category,
                Toast.LENGTH_SHORT).show();

    }
}