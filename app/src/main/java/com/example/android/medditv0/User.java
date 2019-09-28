package com.example.android.medditv0;

import java.lang.reflect.Array;
import java.util.ArrayList;
import org.springframework.data.mongodb.repository.MongoRepository;


public class User {
    private String firstName;
    private String lastName;
    private int age;
    private ArrayList<String> medicalConditions = new ArrayList<String>();
    private ArrayList<String> preferences = new ArrayList<String>();
    private String sex;
    private int phone;
    private string email;
    boolean medical;
    boolean personal;
    private String state;
    private String city;

    public User(String firstName, String lastName, int age, String sex, ArrayList<String> medicalConditions, ArrayList<String> preferences, int phone,string email,String state,String city  ) {
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
    }
public User(){
    //TODO: Read from text file
}

    public int getAge() {
        return this.age;
    }
    public String getSex() {
        return this.sex;
    }
    public String getFirstName(){return this.firstName;}
    public String getLastName(){return this.lastName;}
    public int getPhone(){return this.phone;}
    public String getEmail(){return this.email;}
    public String getCity(){return this.city;}
    public String getState(){return this.state;}

    public ArrayList<String> getMedicalConditions() { return this.medicalConditions; }
    public ArrayList<String> getPreferences() {
        return this.preferences;
    }


}
