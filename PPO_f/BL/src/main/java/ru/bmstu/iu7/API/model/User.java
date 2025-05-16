package ru.bmstu.iu7.API.model;

public class User
{
    public int id;
    public String name;
    public int age;
    public boolean gender;
    public String password;

    public User(String name, String password) {
        this.name = name;
        this.password = password;
    }
}
