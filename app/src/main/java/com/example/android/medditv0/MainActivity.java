//package com.example.android.medditv0;
//
//import androidx.appcompat.app.AppCompatActivity;
//
//import android.content.Intent;
//import android.os.Bundle;
//import android.view.View;
//import android.widget.Button;
//import android.widget.TextView;
//
//public class MainActivity extends AppCompatActivity {
//    private Button login;
//    private Button create;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        login = (Button) findViewById(R.id.login);
//        create = (Button) findViewById(R.id.createButton);
//
//        login.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(MainActivity.this, login_page.class);
//                startActivity(intent);
//            }
//        });
//
//        create.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(MainActivity.this, create_new_user.class);
//                startActivity(intent);
//            }
//        });
//    }
//}
