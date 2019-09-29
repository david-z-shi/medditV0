package com.example.android.medditv0;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class opening_page extends AppCompatActivity {

    private Button login;
    private Button createButton;
    private ImageButton speech;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.opening_page);

        login = (Button) findViewById(R.id.loginpage);
        createButton = (Button) findViewById(R.id.createButton);
        speech = (ImageButton) findViewById(R.id.btnSpeak6);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(opening_page.this, login_page.class);
                startActivity(intent);
            }
        });

        createButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(opening_page.this, create_new_user.class);
                startActivity(intent);
            }
        });

        speech.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(opening_page.this, text_to_speech.class);
                startActivity(intent);
            }
        });
    }
}
