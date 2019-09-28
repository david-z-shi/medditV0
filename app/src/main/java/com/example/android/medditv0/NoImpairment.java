package com.example.android.medditv0;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.JsonReader;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

public class NoImpairment extends AppCompatActivity {
    private EditText firstName;
    private EditText lastName;
    private EditText age;
    private EditText sex;
    private EditText conditions;
    private EditText preferences;
    private EditText phone;
    private EditText email;
    private EditText city;
    private EditText state;
    private Button match;

    private final String USER_AGENT = "meddit_V0";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_no_impairment);

        firstName = (EditText) findViewById(R.id.firstName);
        lastName = (EditText) findViewById(R.id.lastName);
        age = (EditText) findViewById(R.id.age);
        sex = (EditText) findViewById(R.id.sex);
        conditions = (EditText) findViewById(R.id.conditions);
        preferences = (EditText) findViewById(R.id.preferences);
        phone = (EditText) findViewById(R.id.phoneNumber);
        email = (EditText) findViewById(R.id.email);
        city = (EditText) findViewById(R.id.city);
        state = (EditText) findViewById(R.id.state);
        match = (Button) findViewById(R.id.match);

        match.setOnClickListener(new View.OnClickListener() {
            public void onClick (View v){
                String fn = firstName.getText().toString();
                String ln = lastName.getText().toString();
                int a = Integer.parseInt(age.getText().toString());
                String s = sex.getText().toString();
                int pn = Integer.parseInt(phone.getText().toString());
                String em = email.getText().toString();
                String st = state.getText().toString();
                String ct = city.getText().toString();

                String pref = lastName.getText().toString();
                String cond = lastName.getText().toString();

                User new_user = new User(fn, ln, a, s, cond, pref, pn, em, st, ct);
                System.out.println("got here");
                createUser(new_user);
            }
        });
    }

    public void createUser(final User new_user) {
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
                    System.out.println("\nSending 'GET' request to URL : " + webHost);
                    System.out.println("Response Code : " + responseCode);
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }


    public void getUser(HttpsURLConnection myConnection) throws IOException {

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
                    InputStream responseBody = myConnection.getInputStream();
                    InputStreamReader responseBodyReader =
                            new InputStreamReader(responseBody, "UTF-8");
                    JsonReader jsonReader = new JsonReader(responseBodyReader);
                    jsonReader.beginObject(); // Start processing the JSON object
                    User new_user = new User();
                    while (jsonReader.hasNext()) { // Loop through all keys
                        String key = jsonReader.nextName(); // Fetch the next key
                        if (key.equals("test")) { // Check if desired key
                            // Fetch the value as a String
                            String value = jsonReader.nextString();
                            System.out.println(value);
                            //new_user.set(key)
                            break; // Break out of the loop
                        } else {
                            jsonReader.skipValue(); // Skip values of other keys
                        }
                    }
                    int responseCode = myConnection.getResponseCode();
                    System.out.println("\nSending 'GET' request to URL : " + webHost);
                    System.out.println("Response Code : " + responseCode);
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
