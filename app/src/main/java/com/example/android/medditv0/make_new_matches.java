package com.example.android.medditv0;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class make_new_matches extends AppCompatActivity {

    private String matches;
    private final String USER_AGENT = "meddit_V0";
    private String keyword;
    private Button search;
    private EditText keywords_search;
    private String return_val;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.make_new_matches);
        search = (Button) findViewById(R.id.search);
        keywords_search = (EditText) findViewById(R.id.keywords_search);

        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String search_term = keywords_search.getText().toString();
                String return_val = getMatches(search_term);
            }
        });
    }



    public String getMatches(final String search_term) {
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

                    // Send post request
                    myConnection.setDoOutput(true);
                    OutputStream wr = myConnection.getOutputStream();
                    byte[] input = search_term.getBytes("utf-8");
                    wr.write(input, 0, input.length);
                    wr.flush();
                    wr.close();

                    int responseCode = myConnection.getResponseCode();
                    matches = myConnection.getResponseMessage();

                    System.out.println("\nSending 'GET' request to URL : " + webHost);
                    System.out.println("Response Code : " + responseCode);
                    System.out.println("Response Message: " + matches);
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        return matches;
    }
}
