package com.example.android.medditv0;

import android.util.JsonReader;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import javax.net.ssl.HttpsURLConnection;


public class User {
    private String firstName;
    private String lastName;
    private int age;
    private String medicalConditions;
    private String preferences;
    private String sex;
    private int phone;
    private String email;
    private String state;
    private String city;

    private String username;
    private String password;
    private String profile;

    private final String USER_AGENT = "meddit_V0";


    public User(String firstName, String lastName, int age, String sex,
                String medicalConditions, String preferences, int phone,
                String email,String state,String city, String username,
                String password, String profile) {
        this.firstName=firstName;
        this.lastName=lastName;
        this.age = age;
        this.sex=sex;
        this.medicalConditions = medicalConditions;
        this.preferences = preferences;
        this.phone=phone;
        this.email=email;
        this.city=city;
        this.state=state;
        this.username = username;
        this.password=  password;
        this.profile = profile;
    }
    public User(){
        //Dummy Method
    }

    public int getAge() {
        return this.age;
    }
    public int getPhone(){return this.phone;}

    public String getSex() {
        return this.sex;
    }
    public String getFirstName(){return this.firstName;}
    public String getLastName(){return this.lastName;}
    public String getEmail(){return this.email;}
    public String getCity(){return this.city;}
    public String getState(){return this.state;}
    public String getUsername() {return this.username;}
    public String getPassword() {return this.password;}
    public String getProfile() {return this.profile;}

    public String getMedicalConditions() { return this.medicalConditions; }
    public String getPreferences() {
        return this.preferences;
    }

    // Creating toString
    @Override
    public String toString()
    {
        return "{\"First Name\":\"" + getFirstName()
                + "\", \"Last Name:\"" + getLastName()
                + "\", \"Age\":" + getAge()
                + "\", \"Sex\":" + getSex()
                + "\", \"City\":" + getCity()
                + "\", \"State\":" + getState()
                + "\", \"Condition\":" + getMedicalConditions()
                + "\", \"Preferences\":" + getPreferences()
                + "\", \"Phone\":" + getPhone()
                + "\", \"Email\":" + getEmail()
                + "\", \"Personal\":" + false
                + ", \"Medical\":" + true
                + "}";
    }
}
