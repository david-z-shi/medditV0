package com.example.android.medditv0;

import java.util.ArrayList;

public class User {
    int age;
    private String[] medicalConditions = new ArrayList<String>;
    private String[] preferences = new ArrayList<String>;
    private  String sex;
    public User(int age, String sex, String[] medicalConditions, String[] preferences) {
        this.age = age;
        this.sex=sex;
        this.medicalConditions = medicalConditions;
        this.preferences = preferences;
    }
    private boolean blindness;
    public User(boolean blindness,boolean){
        this.blindness=blindness;
    }
public String Impairment(){
//TODO: Implement Additional Constructors
    return null;
}

}
