package com.example.android.medditv0;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class update_user_preferences extends AppCompatActivity {

    private final String USER_AGENT = "meddit_V0";
    private EditText sex;
    private EditText conditions;
    private EditText preferences;
    private EditText city;
    private EditText state;
    private EditText age;
    private EditText profile;
    private Button save;

    private String responseMessage;

    @Override
    protected  void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle bundle = getIntent().getExtras();
        final String firstName = bundle.getString("firstName");
        final String lastName = bundle.getString("lastName");
        final String email = bundle.getString("email");
        final String username = bundle.getString("username");
        final String password = bundle.getString("password");
        final int phone = bundle.getInt("phone");

        city = (EditText) findViewById(R.id.city);
        sex = (EditText) findViewById(R.id.sex);
        conditions = (EditText) findViewById(R.id.conditions);
        preferences = (EditText) findViewById(R.id.preferences);
        age = (EditText) findViewById(R.id.age);
        state = (EditText) findViewById(R.id.state);
        profile = (EditText) findViewById(R.id.profile);

        final String s = sex.toString();
        final String cond = conditions.toString();
        final String pref = preferences.toString();
        final int a = Integer.parseInt(age.toString());
        final String prof = profile.toString();
        final String st = state.toString();
        final String ct = city.toString();

        save = (Button) findViewById(R.id.save);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                User new_user = new User(firstName, lastName, a, s, cond, pref, phone,
                        email, st, ct, username, password, prof);
                String id = createUser(new_user);
            }
        });
    }

    public String createUser(final User new_user) {
        MyAsyncTask aTask = new MyAsyncTask();
        aTask.setListener(new MyAsyncTask.MyAsyncTaskListener() {
            @Override
            public void onPreExecuteConcluded() {
                // gui stuff
            }

            @Override
            public void onPostExecuteConcluded(String result) {
                // gui stuff
            }
        });
        aTask.execute(new Runnable() {
            @Override
            public void run() {
                URL webHost = null;
                try {
                    webHost = new URL("http://35.194.75.168:8030/user/create");
                    HttpURLConnection myConnection = (HttpURLConnection) webHost.openConnection();
                    //add request header
                    myConnection.setRequestMethod("POST");
                    myConnection.setRequestProperty("User-Agent", USER_AGENT);
                    myConnection.setRequestProperty("Accept", "application/json");
                    myConnection.setRequestProperty("Accept-Language", "en-US,en;q=0.5");

                    String urlParameters = new_user.toString();

                    // Send post request
                    myConnection.setDoOutput(true);
                    OutputStream wr = myConnection.getOutputStream();
                    byte[] input = urlParameters.getBytes("utf-8");
                    wr.write(input, 0, input.length);
                    wr.flush();
                    wr.close();

                    int responseCode = myConnection.getResponseCode();
                    responseMessage = myConnection.getResponseMessage();
                    System.out.println("\nSending 'GET' request to URL : " + webHost);
                    System.out.println("Response Code : " + responseCode);
                    System.out.println("Response Message: " + responseMessage);
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        return responseMessage;
    }
}
