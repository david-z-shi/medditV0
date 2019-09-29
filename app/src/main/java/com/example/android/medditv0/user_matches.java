package com.example.android.medditv0;

import androidx.appcompat.app.AppCompatActivity;
//import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class user_matches extends AppCompatActivity {
    private ImageButton user_match1;
    private ImageButton user_match2;
    private ImageButton user_match3;
    private ImageButton user_match4;
    private ImageButton user_match5;
    private ImageButton home;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_matches);
        user_match1 = (ImageButton) findViewById(R.id.imageButton31);
        user_match2 = (ImageButton) findViewById(R.id.imageButton32);
        user_match3 = (ImageButton) findViewById(R.id.imageButton33);
        user_match4 = (ImageButton) findViewById(R.id.imageButton28);
        user_match5 = (ImageButton) findViewById(R.id.imageButton35);
        home = (ImageButton) findViewById(R.id.floatingActionButton5);
        user_match1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(user_matches.this, user_profile.class);
                startActivity(intent);
            }
        });

        user_match2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(user_matches.this, user_profile.class);
                startActivity(intent);
            }
        });
        user_match3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(user_matches.this, user_profile.class);
                startActivity(intent);
            }
        });
        user_match4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(user_matches.this, user_profile.class);
                startActivity(intent);
            }
        });
        user_match5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(user_matches.this, user_profile.class);
                startActivity(intent);
            }
        });
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(user_matches.this, main_menu.class);
                startActivity(intent);
            }
        });
    }
}
