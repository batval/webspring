package com.batval.model;


import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class User {

    @NotBlank(message = "Name is required!")
    @Size(min=3, max=30, message = "Name should be from 3 to 30")
    private  String name;
    @NotBlank(message = "Surname is required!")
    @Size(min=3, max=30, message = "Surname should be from 3 to 30")
    private  String surname;
    @Email
    private  String email;

    public User(){
    }

    public User(String name, String surname, String email) {
        this.name = name;
        this.surname = surname;
        this.email = email;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getEmail() {
        return email;
    }
}
