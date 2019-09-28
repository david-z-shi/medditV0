package com.example.android.medditv0;
import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.speech.tts.TextToSpeech;
import android.speech.tts.TextToSpeech.OnInitListener;
import android.util.JsonReader;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Locale;

import javax.net.ssl.HttpsURLConnection;

public class text_to_speech extends Activity implements View.OnClickListener, OnInitListener {

    //TTS object
    private TextToSpeech myTTS;
    //status check code
    private int MY_DATA_CHECK_CODE = 0;

    private final String welcome = "Welcome to meddit";
    private final String firstQuestion = "Do you want to enter voice entry mode?";
    private final String answer = "undefined";

    //Speech to text stuff
    private TextView txtSpeechInput;
    private ImageButton btnSpeak;
    private final int REQ_CODE_SPEECH_INPUT = 100;

    //create the Activity
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_text_to_speech);

        //check for TTS data
        Intent checkTTSIntent = new Intent();
        checkTTSIntent.setAction(TextToSpeech.Engine.ACTION_CHECK_TTS_DATA);
        startActivityForResult(checkTTSIntent, MY_DATA_CHECK_CODE);

        //Speech to text stuff
        txtSpeechInput = (TextView) findViewById(R.id.txtSpeechInput);
        btnSpeak = (ImageButton) findViewById(R.id.btnSpeak);

        // hide the action bar
        btnSpeak.setOnClickListener(this);
    }

    public void onClick(View v) {
        promptSpeechInput();
    }

    /**
     * Showing google speech input dialog
     * */
    private void promptSpeechInput() {
        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());
        intent.putExtra(RecognizerIntent.EXTRA_PROMPT,
                getString(R.string.speech_prompt));
        try {
            startActivityForResult(intent, REQ_CODE_SPEECH_INPUT);
        } catch (ActivityNotFoundException a) {
            Toast.makeText(getApplicationContext(),
                    getString(R.string.speech_not_supported),
                    Toast.LENGTH_SHORT).show();
        }
        //speakWords(firstQuestion);
    }

    //speak the user text
    private void speakWords(String speech) {
        //speak straight away
        myTTS.speak(speech, TextToSpeech.QUEUE_FLUSH, null);
    }

    //act on result of TTS data check
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == MY_DATA_CHECK_CODE) {
            if (resultCode == TextToSpeech.Engine.CHECK_VOICE_DATA_PASS) {
                //the user has the necessary data - create the TTS
                myTTS = new TextToSpeech(this, this);
            }
            else {
                //no data - install it now
                Intent installTTSIntent = new Intent();
                installTTSIntent.setAction(TextToSpeech.Engine.ACTION_INSTALL_TTS_DATA);
                startActivity(installTTSIntent);
            }
        }
        switch (requestCode) {
            case REQ_CODE_SPEECH_INPUT: {
                if (resultCode == RESULT_OK && null != data) {

                    ArrayList<String> result = data
                            .getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                    txtSpeechInput.setText(result.get(0));
                }
                break;
            }

        }
    }

    //setup TTS
    public void onInit(int initStatus) {
        //check for successful instantiation
        if (initStatus == TextToSpeech.SUCCESS) {
            if(myTTS.isLanguageAvailable(Locale.US)==TextToSpeech.LANG_AVAILABLE)
                myTTS.setLanguage(Locale.US);
        }
        else if (initStatus == TextToSpeech.ERROR) {
            Toast.makeText(this, "Sorry! Text To Speech failed...", Toast.LENGTH_LONG).show();
        }
    }


    public void createUser() {
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
                    webHost = new URL("35.194.75.168");
                    HttpsURLConnection myConnection = (HttpsURLConnection) webHost.openConnection();
                    if (myConnection.getResponseCode() == 200) {
                        // Success
                        // Further processing here
                        getUser(myConnection);
                    } else {
                        // Error handling code goes here
                    }
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public void getUser(HttpsURLConnection myConnection) throws IOException {
        InputStream responseBody = myConnection.getInputStream();
        InputStreamReader responseBodyReader =
                new InputStreamReader(responseBody, "UTF-8");
        JsonReader jsonReader = new JsonReader(responseBodyReader);
        jsonReader.beginObject(); // Start processing the JSON object
        while (jsonReader.hasNext()) { // Loop through all keys
            String key = jsonReader.nextName(); // Fetch the next key
            if (key.equals("test")) { // Check if desired key
                // Fetch the value as a String
                String value = jsonReader.nextString();
                System.out.println(value);
                break; // Break out of the loop
            } else {
                jsonReader.skipValue(); // Skip values of other keys
            }
        }
    }
}