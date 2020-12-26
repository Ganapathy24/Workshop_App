package com.example.workshop;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class Registration extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        EditText name = findViewById(R.id.ownername);
        EditText shopname = findViewById(R.id.shopname);
        Spinner category = findViewById(R.id.categoryname);
        EditText phone_number = findViewById(R.id.phonenumber);

        Button submit = findViewById(R.id.submit_btn);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Registration.this,
                        "OnClickListener : " +
                                "\nCategory Spinner : "+ String.valueOf(category.getSelectedItem()),
                        Toast.LENGTH_SHORT).show();
            }
        });
    }
}