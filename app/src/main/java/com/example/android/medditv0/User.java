package com.example.android.medditv0;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class User {
    private int age;
    private ArrayList<String> medicalConditions = new ArrayList<String>();
    private ArrayList<String> preferences = new ArrayList<String>();
    private String sex;
    private boolean blindness;

    public User(int age, String sex, ArrayList<String> medicalConditions, ArrayList<String> preferences, boolean blindness) {
        this.age = age;
        this.sex=sex;
        this.medicalConditions = medicalConditions;
        this.preferences = preferences;
        this.blindness = blindness;
    }

    public int getAge() {
        return this.age;
    }

    public String getSex() {
        return this.sex;
    }

    public ArrayList<String> getMedicalConditions() {
        return this.medicalConditions;
    }

    public ArrayList<String> getPreferences() {
        return this.preferences;
    }

    public boolean getBlindness() {
        return this.blindness;
    }
}
