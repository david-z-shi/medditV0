package com.example.android.medditv0;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class main_menu extends AppCompatActivity {
    private Button UpdateProfile;
    private Button SavedMatches;
    private Button NewMatches;
    private Button Settings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_menu);

        UpdateProfile = (Button) findViewById(R.id.UpdateProfile);
        SavedMatches = (Button) findViewById(R.id.SavedMatches);
        NewMatches = (Button) findViewById(R.id.NewMatches);
        Settings = (Button) findViewById(R.id.Settings);

        UpdateProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(main_menu.this, update_user_preferences.class);
                startActivity(intent);
            }
        });

        SavedMatches.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(main_menu.this, update_user_preferences.class);
                startActivity(intent);
            }
        });

        NewMatches.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(main_menu.this, update_user_preferences.class);
                startActivity(intent);
            }
        });

        Settings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(main_menu.this, update_user_preferences.class);
                startActivity(intent);
            }
        });
    }
}
