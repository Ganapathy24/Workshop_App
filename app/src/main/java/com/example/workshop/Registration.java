package com.example.workshop;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.basgeekball.awesomevalidation.AwesomeValidation;
import com.basgeekball.awesomevalidation.ValidationStyle;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Registration extends AppCompatActivity {

    private static final int REQUEST_LOCATION = 1;
    LocationManager locationManager;
    String latitude, longitude;
    private AwesomeValidation awesomeValidation;
    ImageView img, shine;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        awesomeValidation = new AwesomeValidation(ValidationStyle.BASIC);

        img = (ImageView) findViewById(R.id.img);
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
                                0,img.getWidth()+shine.getHeight(),0,0
                        );
                        animation.setDuration(1500);
                        animation.setFillAfter(false);
                        animation.setInterpolator(new AccelerateDecelerateInterpolator());
                        shine.startAnimation(animation);
                    }

                });

            }}, 1, 2, TimeUnit.SECONDS);



        EditText name = findViewById(R.id.ownername);
        EditText shopname = findViewById(R.id.shopname);
        Spinner category = findViewById(R.id.categoryname);
        EditText phone_number = findViewById(R.id.phonenumber);
        Button submit = findViewById(R.id.submit_btn);

        ActivityCompat.requestPermissions(this,
                new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_LOCATION);


        awesomeValidation.addValidation(this, R.id.ownername, "^[A-Za-z\\s]{1,}[\\.]{0,1}[A-Za-z\\s]{0,}$", R.string.nameerror);
        awesomeValidation.addValidation(this, R.id.phonenumber, "^[2-9]{2}[0-9]{8}$", R.string.mobileerror);
        awesomeValidation.addValidation(this, R.id.shopname, "^[A-Za-z\\s]{1,}[\\.]{0,1}[A-Za-z\\s]{0,}$", R.string.shoperror);


        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (v == submit) {
                    submitForm();
                }
            }


            private void submitForm() {
                //first validate the form then move ahead
                //if this becomes true that means validation is successfull
                if (awesomeValidation.validate()) {
                    Toast.makeText(Registration.this, "Validation Successfull", Toast.LENGTH_LONG).show();

                    //process the data further
                    locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
                    if (!locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
                        OnGPS();
                    } else {
                        getLocation();
                    }

                    Intent detailConfirmation = new Intent(Registration.this, DetailsConfirmation.class);
                    detailConfirmation.putExtra("name", name.getText().toString());
                    detailConfirmation.putExtra("shopname", shopname.getText().toString());
                    detailConfirmation.putExtra("category", String.valueOf(category.getSelectedItem()));
                    detailConfirmation.putExtra("phonenumber", phone_number.getText().toString());
                    detailConfirmation.putExtra("latitude", latitude);
                    detailConfirmation.putExtra("longitude", longitude);

                    startActivity(detailConfirmation);
                }
            }

            private void OnGPS() {
                final AlertDialog.Builder builder = new AlertDialog.Builder(Registration.this);
                builder.setMessage("Enable GPS").setCancelable(false).setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        startActivity(new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS));
                    }
                }).setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                final AlertDialog alertDialog = builder.create();
                alertDialog.show();
            }

            private void getLocation() {
                if (ActivityCompat.checkSelfPermission(
                        Registration.this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                        Registration.this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(Registration.this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_LOCATION);
                } else {
                    Location locationGPS = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
                    if (locationGPS != null) {
                        double lat = locationGPS.getLatitude();
                        double longi = locationGPS.getLongitude();
                        latitude = String.valueOf(lat);
                        longitude = String.valueOf(longi);
                        Toast.makeText(Registration.this, "Your Location: " + "\n" + "Latitude: " + latitude + "\n" + "Longitude: " + longitude, Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(Registration.this, "Unable to find location.", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
}