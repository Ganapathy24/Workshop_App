package com.example.workshop;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class Profile extends AppCompatActivity {

    ImageView img1,img2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        String username = getIntent().getStringExtra("username");
        String sname = getIntent().getStringExtra("shopname");
        String phone =getIntent().getStringExtra("phone");
        String category = getIntent().getStringExtra("category");

        img1 = (ImageView) findViewById(R.id.img1);
        TextView name = findViewById(R.id.ownername);
        TextView shopname = findViewById(R.id.shopname);

        name.setText(username);
        shopname.setText(sname);

        img1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
                Intent callIntent = new Intent(Intent.ACTION_CALL);
                callIntent.setData(Uri.parse("tel:"+ phone));
                if (ActivityCompat.checkSelfPermission(Profile.this,
                        Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    return;
                }
                startActivity(callIntent);
            }
        });
        img2 = (ImageView) findViewById(R.id.img2);


        img2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(Profile.this, "LOCATION SENT ", Toast.LENGTH_SHORT).show();
//                Intent i = new Intent(Profile.this, GPS.class);
//                startActivity(i);
            }
        });
    }
}