package com.example.workshop;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.app.Service;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Parcelable;
import android.provider.Settings;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.gson.Gson;
import com.google.gson.JsonElement;

import java.io.Serializable;
import java.security.Provider;
import java.util.ArrayList;
import java.util.List;

public class ServiceList extends AppCompatActivity {

    DatabaseReference reference;
    RecyclerView categoryList;

    ArrayList<UserEntity> list;
    CategoryAdapter adapter;
    private static final int REQUEST_LOCATION = 1;
    LocationManager locationManager;
    double latitude, longitude;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_list);

        String category = getIntent().getStringExtra("category");

        categoryList = (RecyclerView) findViewById(R.id.myRecycler);
        categoryList.setLayoutManager( new LinearLayoutManager(this));
        Gson gson = new Gson();


        reference = FirebaseDatabase.getInstance().getReference().child(category);
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                list = new ArrayList<UserEntity>();
                for(DataSnapshot dataSnapshot1: dataSnapshot.getChildren())
                {
                    UserEntity p = gson.fromJson(dataSnapshot1.getValue().toString(), UserEntity.class);
                    if (distance(latitude, longitude, p.latitute, p.longitute) > 5){
                        list.add(p);
                    }
                }
                if (list.size() > 0) {
                    adapter = new CategoryAdapter(ServiceList.this, list);
                    categoryList.setAdapter(adapter);
                }
                Toast.makeText(ServiceList.this, "Empty List", Toast.LENGTH_SHORT).show();

            }



            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(ServiceList.this, "Opsss.... Something is wrong", Toast.LENGTH_SHORT).show();
            }
        });
        categoryList.addOnItemTouchListener(new RecyclerTouchListener(this,
                categoryList, new ClickListener() {
            @Override
            public void onClick(View view, final int position) {
                //Values are passing to activity & to fragment as well
                Intent intent = new Intent(ServiceList.this, Profile.class);
                UserEntity ue = list.get(position);
                String name = ue.getName();
                String shopname = ue.getShopName();
                String phone = ue.getPhoneNumber();
                String category = ue.getCategory();
                double latitute = ue.getLatitute();
                double longitute = ue.getLongitute();
                intent.putExtra("username", name);
                intent.putExtra("shopname", shopname);
                intent.putExtra("phone", phone);
                intent.putExtra("category", category);
                intent.putExtra("latitute", latitute);
                intent.putExtra("longitute", longitute);
                startActivity(intent);
            }

            @Override
            public void onLongClick(View view, int position) {
                Toast.makeText(ServiceList.this, "Long press on position :"+position,
                        Toast.LENGTH_LONG).show();
            }
        }));

    }
    private double distance(double lat1, double lon1, double lat2, double lon2) {
        double theta = lon1 - lon2;
        double dist = Math.sin(deg2rad(lat1))
                * Math.sin(deg2rad(lat2))
                + Math.cos(deg2rad(lat1))
                * Math.cos(deg2rad(lat2))
                * Math.cos(deg2rad(theta));
        dist = Math.acos(dist);
        dist = rad2deg(dist);
        dist = dist * 60 * 1.1515;
        return (dist);


    }
    private double deg2rad(double deg) {
        return (deg * Math.PI / 180.0);
    }

    private double rad2deg(double rad) {
        return (rad * 180.0 / Math.PI);
    }
    private void OnGPS() {
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Enable GPS").setCancelable(false).setPositiveButton("Yes", new  DialogInterface.OnClickListener() {
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
                ServiceList.this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                ServiceList.this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(ServiceList.this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_LOCATION);
        } else {
            Location locationGPS = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
            if (locationGPS != null) {
                double lat = locationGPS.getLatitude();
                double longi = locationGPS.getLongitude();
                latitude = lat;
                longitude = longi;
                Toast.makeText(ServiceList.this, "Your Location: " + "\n" + "Latitude: " + latitude + "\n" + "Longitude: " + longitude, Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Unable to find location.", Toast.LENGTH_SHORT).show();
            }
        }
    }




}