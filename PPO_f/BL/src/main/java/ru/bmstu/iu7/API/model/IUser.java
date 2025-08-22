package ru.bmstu.iu7.API.model;


import jakarta.persistence.*;

public interface IUser
{
    Long getId();
    void setId(Long id);
    String getName();
    void setName(String name);
    int getAge();
    void setAge(int age);
    boolean isGender();
    void setGender(boolean gender);
    String getPassword();
    void setPassword(String password);

}
