package com.poojithabijjam.firebasedemo;


import java.io.Serializable;
import java.util.HashMap;

public class Student implements Serializable {
    String rollNum;
    String name;
    HashMap<String, Boolean> attendence = new HashMap<>();

    @Override
    public String toString() {
        return name;
    }


    public Student(String rollNum, String name, HashMap<String, Boolean> attendence) {
        this.rollNum = rollNum;
        this.name = name;
        this.attendence = attendence;
    }

    public Student(String rollNum, String name) {
        this.rollNum = rollNum;
        this.name = name;
        this.attendence = new HashMap<>();
    }

    public Student() {
    }


    public String getRollNum() {
        return rollNum;
    }

    public void setRollNum(String rollNum) {
        this.rollNum = rollNum;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public HashMap<String, Boolean> getAttendence() {
        return attendence;
    }

    public void setAttendence(HashMap<String, Boolean> attendence) {
        this.attendence = attendence;
    }

}