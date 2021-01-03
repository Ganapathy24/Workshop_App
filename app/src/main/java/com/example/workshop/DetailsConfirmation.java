package com.example.workshop;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.JsonReader;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.util.JsonUtils;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.util.JsonMapper;
import com.google.gson.Gson;

import org.json.JSONObject;
import org.json.JSONTokener;
import org.w3c.dom.Text;

import java.io.IOException;

public class DetailsConfirmation extends AppCompatActivity {

    static int id = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_confirmation);
        String name = getIntent().getStringExtra("name");
        String shopname = getIntent().getStringExtra("shopname");
        String category = getIntent().getStringExtra("category");
        String phoneNumber = getIntent().getStringExtra("phonenumber");
        String latitude = getIntent().getStringExtra("latitude");
        String longitude = getIntent().getStringExtra("longitude");

        Log.d("Data ", phoneNumber);

        TextView ownerName = findViewById(R.id.oName);
        TextView sName = findViewById(R.id.sName);
        TextView categoryText = findViewById(R.id.categoryId);
        TextView phone = findViewById(R.id.pNumber);
        TextView location = findViewById(R.id.location);
        Button submit_btn = findViewById(R.id.submit_btn);
        String combinedLoc = "Latitude" + latitude + "\n" + "Longitude" + longitude;

        ownerName.setText(name);
        sName.setText(shopname);
        categoryText.setText(category);
        phone.setText(phoneNumber);
        location.setText(combinedLoc);


        Gson gson = new Gson();


        submit_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String json = null;
                FirebaseDatabase database = FirebaseDatabase.getInstance();
                DatabaseReference myRef = database.getReference(category);
                UserEntity ue = new UserEntity(name, shopname, category ,phoneNumber, latitude, longitude );
                json = gson.toJson(ue);
                myRef.child(id+"").setValue(json);
                Toast.makeText(DetailsConfirmation.this, "Stored Successfully", Toast.LENGTH_SHORT).show();
                id++;
            }
        });


    }
}