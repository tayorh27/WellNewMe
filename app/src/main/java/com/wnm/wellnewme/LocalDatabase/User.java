package com.wnm.wellnewme.LocalDatabase;

/**
 * Created by tayo on 11/9/2015.
 */
public class User {

    public String firstname,lastname,email,password;
    public int id;

    public User(int id,String first_name, String last_name, String email, String password){
        this.email =email;
        this.password=password;
        this.id = id;
        this.firstname = first_name;
        this.lastname = last_name;
    }
}
