package com.leo.model;

public class User {

    private String name;
    private int age;
    private String pass;

    public User(String name, int age, String pass) {
        this.name = name;
        this.age = age;
        this.pass = pass;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String getPass() {
        return pass;
    }
}
