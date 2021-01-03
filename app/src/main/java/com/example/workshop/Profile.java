package com.example.workshop;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class Profile extends AppCompatActivity {

    ImageView img1,img2;
    private static final int MY_PERMISSIONS_REQUEST_SEND_SMS =0 ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        String username = getIntent().getStringExtra("username");
        String sname = getIntent().getStringExtra("shopname");
        String phone =getIntent().getStringExtra("phone");
        String category = getIntent().getStringExtra("category");
        String latitute = getIntent().getStringExtra("latitute");
        String longitute = getIntent().getStringExtra("longitute");

        img1 = (ImageView) findViewById(R.id.img1);
        TextView name = findViewById(R.id.ownername);
        TextView shopname = findViewById(R.id.shopname);
        TextView locationText = findViewById(R.id.location);

        name.setText(username);
        shopname.setText(sname);
        locationText.setText("latitute " + latitute + "\n" + "longitute " + longitute);

        img1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
                Intent callIntent = new Intent(Intent.ACTION_CALL);
                callIntent.setData(Uri.parse("tel:"+ phone));
                if (ActivityCompat.checkSelfPermission(Profile.this,
                        Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(Profile.this, new String[]{Manifest.permission.CALL_PHONE}, 1);
                    Toast.makeText(Profile.this, "404 Error ", Toast.LENGTH_SHORT).show();
                    return;
                }
                startActivity(callIntent);
            }
        });
        img2 = (ImageView) findViewById(R.id.img2);


        img2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = "https://maps.google.com/?q=<"+latitute+">,<"+longitute+">";
                sendSMSMessage();
                //Toast.makeText(Profile.this, "LOCATION SENT ", Toast.LENGTH_SHORT).show();
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                startActivity(i);
//                Intent i = new Intent(Profile.this, GPS.class);
//                startActivity(i);
            }
        });
    }
    protected void sendSMSMessage() {

        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.SEND_SMS)
                != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.SEND_SMS)) {
            } else {
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.SEND_SMS},
                        MY_PERMISSIONS_REQUEST_SEND_SMS);
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,String permissions[], int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_SEND_SMS: {
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    SmsManager smsManager = SmsManager.getDefault();
                    smsManager.sendTextMessage("7639210637", null, "Hello", null, null);
                    Toast.makeText(Profile.this, "SMS sent.",
                            Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(Profile.this,
                            "SMS faild, please try again.", Toast.LENGTH_LONG).show();
                    return;
                }
            }
        }

    }
}