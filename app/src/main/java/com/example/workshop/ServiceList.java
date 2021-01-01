package com.example.workshop;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.gson.Gson;
import com.google.gson.JsonElement;

import java.io.Serializable;
import java.util.ArrayList;

public class ServiceList extends AppCompatActivity {

    DatabaseReference reference;
    RecyclerView categoryList;

    ArrayList<UserEntity> list;
    CategoryAdapter adapter;
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
                    list.add(p);
                }
                adapter = new CategoryAdapter(ServiceList.this,list);
                categoryList.setAdapter(adapter);

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
                intent.putExtra("details", (Serializable) list.get(position));
                startActivity(intent);
            }

            @Override
            public void onLongClick(View view, int position) {
                Toast.makeText(ServiceList.this, "Long press on position :"+position,
                        Toast.LENGTH_LONG).show();
            }
        }));

    }
}