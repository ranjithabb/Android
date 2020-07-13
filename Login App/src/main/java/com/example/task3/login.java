package com.example.task3;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class login extends AppCompatActivity {

    int count = 0;
    Button b1,b2;
    EditText et1, et2;
    @SuppressLint("WrongConstant")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        b1 = findViewById(R.id.ok);
        b2 = findViewById(R.id.cancel);

        et1 = findViewById(R.id.username);
        et2 = findViewById(R.id.password);

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                count++;
                if(count == 3) {
                    b1.setVisibility(View.INVISIBLE);
                }
                b1.postDelayed((new Runnable() {
                    @Override
                    public void run() {
                        b1.setVisibility(View.VISIBLE);
                    }
                }),8000);
                if (et1.getText().toString().equals("admin") && et2.getText().toString().equals("admin")) {
                    Toast.makeText(getApplicationContext(), "login success", Toast.LENGTH_LONG).show();
                    Intent i = new Intent(login.this, MainActivity.class);
                    startActivity(i);
                } else {
                    Toast.makeText(getApplicationContext(), "wrong username or password", Toast.LENGTH_LONG).show();
                }
            }
        });

        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}