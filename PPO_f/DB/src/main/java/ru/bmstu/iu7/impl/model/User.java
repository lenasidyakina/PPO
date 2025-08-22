package ru.bmstu.iu7.impl.model;


import jakarta.persistence.*;
import ru.bmstu.iu7.API.model.IUser;

@Entity
@Table(name="users")
public class User implements IUser
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;
    public String name;
    public int age;
    public boolean gender;
    public String password;

    public User(){}
    public User(String name, String password) {
        this.name = name;
        this.password = password;
    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int getAge() {
        return this.age;
    }

    @Override
    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public boolean isGender() {
        return this.gender;
    }

    @Override
    public void setGender(boolean gender) {
        this.gender = gender;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public void setPassword(String password) {
        this.password = password;
    }

}
