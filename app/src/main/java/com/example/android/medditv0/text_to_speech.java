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
import android.widget.Button;
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

    private int count = 1;

    private final String welcome = "Welcome to meddit";
    private final String firstQuestion = "Do you want to enter voice entry mode?";
    private final String answer = "undefined";

    //Speech to text stuff
    private TextView txtSpeechInput;
    private ImageButton btnSpeak;
    private final int REQ_CODE_SPEECH_INPUT = 100;

    private Button normal_user;

    String firstName;
    String lastName;
    String email;
    String username;
    String password;
    int phone;
    String s;
    String cond;
    String pref;
    int a;
    String prof;
    String st;
    String ct;

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
        normal_user = (Button) findViewById(R.id.normal_user);
        normalClick();

        btnSpeak.setOnClickListener(this);
    }

    public void onClick(View v) {
        tenQuestions();
    }

    public void normalClick() {
        normal_user.setOnClickListener(new View.OnClickListener() {
            public void onClick (View v){
                Intent intent = new Intent(text_to_speech.this, NoImpairment.class);
                startActivity(intent);
            }
        });
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
        int speechStatus = myTTS.speak(speech, TextToSpeech.QUEUE_FLUSH, null, null);
    }

    private boolean confirmNext() {
        promptSpeechInput();
        if (txtSpeechInput.getText().toString().equals("confirmed")) {
            myTTS.speak("confirmed, next", TextToSpeech.QUEUE_FLUSH, null, null);
            return true;
        }
        return false;
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

    public void tenQuestions() {
        switch (count) {
            case 1:
                myTTS.speak("What is your first name?", TextToSpeech.QUEUE_FLUSH, null, null);
                promptSpeechInput();
                firstName = txtSpeechInput.getText().toString();
                confirmNext();
                if (!confirmNext()) {
                    break;
                }
            case 2:
                myTTS.speak("What is your last name?", TextToSpeech.QUEUE_FLUSH, null, null);
                promptSpeechInput();
                lastName = txtSpeechInput.getText().toString();
                if (!confirmNext()) {
                    break;
                }
            case 3:
                myTTS.speak("What is your phone number?", TextToSpeech.QUEUE_FLUSH, null, null);
                promptSpeechInput();
                phone = Integer.parseInt(txtSpeechInput.getText().toString());

                if (!confirmNext()) {
                    break;
                }
            case 4:
                myTTS.speak("What is your email?", TextToSpeech.QUEUE_FLUSH, null, null);
                promptSpeechInput();
                email = txtSpeechInput.getText().toString();
                if (!confirmNext()) {
                    break;
                }
            case 5:
                myTTS.speak("What is your city?", TextToSpeech.QUEUE_FLUSH, null, null);
                promptSpeechInput();
                ct = txtSpeechInput.getText().toString();
                if (!confirmNext()) {
                    break;
                }
            case 6:
                myTTS.speak("What is your state?", TextToSpeech.QUEUE_FLUSH, null, null);
                promptSpeechInput();
                st = txtSpeechInput.getText().toString();
                if (!confirmNext()) {
                    break;
                }
            case 7:
                myTTS.speak("What is your condition?", TextToSpeech.QUEUE_FLUSH, null, null);
                promptSpeechInput();
                cond = txtSpeechInput.getText().toString();
                if (!confirmNext()) {
                    break;
                }
            case 8:
                myTTS.speak("What is your preference?", TextToSpeech.QUEUE_FLUSH, null, null);
                promptSpeechInput();
                pref = txtSpeechInput.getText().toString();
                if (!confirmNext()) {
                    break;
                }
            case 9:
                myTTS.speak("What is your sex?", TextToSpeech.QUEUE_FLUSH, null, null);
                promptSpeechInput();
                s = txtSpeechInput.getText().toString();
                if (!confirmNext()) {
                    break;
                }
            case 10:
                myTTS.speak("What is your age?", TextToSpeech.QUEUE_FLUSH, null, null);
                promptSpeechInput();
                int a = Integer.parseInt(txtSpeechInput.getText().toString());
                if (!confirmNext()) {
                    break;
                }
            case 11:
                myTTS.speak("What is your username?", TextToSpeech.QUEUE_FLUSH, null, null);
                promptSpeechInput();
                username = txtSpeechInput.getText().toString();
                if (!confirmNext()) {
                    break;
                }
            case 12:
                myTTS.speak("What is your password?", TextToSpeech.QUEUE_FLUSH, null, null);
                promptSpeechInput();
                password = txtSpeechInput.getText().toString();
                if (!confirmNext()) {
                    break;
                }
            case 13:
                myTTS.speak("What is your profile?", TextToSpeech.QUEUE_FLUSH, null, null);
                promptSpeechInput();
                prof = txtSpeechInput.getText().toString();
                if (!confirmNext()) {
                    break;
                }
        }
    }
}