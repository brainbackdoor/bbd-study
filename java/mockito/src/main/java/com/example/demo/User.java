package com.example.demo;

public class User {
    private long id;
    private String name;
    private String age;

    public User(long id, String name, String age) {
        this.id =  id;
        this.name = name;
        this.age = age;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getAge() {
        return age;
    }
}
