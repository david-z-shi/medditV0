package com.example.android.medditv0;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class create_new_user extends AppCompatActivity {
    private EditText firstName;
    private EditText lastName;
    private EditText phone;
    private EditText email;
    private Button createAccount;
    private EditText username;
    private EditText password;
    private Button restart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_new_user);

        firstName = (EditText) findViewById(R.id.firstName);
        lastName = (EditText) findViewById(R.id.lastName);
        phone = (EditText) findViewById(R.id.phoneNumber);
        email = (EditText) findViewById(R.id.email);

        username = (EditText) findViewById(R.id.username);
        password = (EditText) findViewById(R.id.password);
        restart = (Button) findViewById(R.id.floatingActionButton4);
        createAccount = (Button) findViewById(R.id.createAccount);

        createAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(create_new_user.this, update_user_preferences.class);
                intent.putExtra("firstName", firstName.toString());
                intent.putExtra("lastName", lastName.toString());
                intent.putExtra("phone", Integer.parseInt(phone.toString()));
                intent.putExtra("email", email.toString());
                intent.putExtra("username", username.toString());
                intent.putExtra("password", password.toString());

                startActivity(intent);
            }
        });
        restart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(create_new_user.this, opening_page.class);
                startActivity(intent);
            }
        });
    }
}
