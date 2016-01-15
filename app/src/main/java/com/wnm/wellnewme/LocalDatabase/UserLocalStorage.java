package com.wnm.wellnewme.LocalDatabase;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by tayo on 11/9/2015.
 */
public class UserLocalStorage {

    private static final String SP_NAME = "userDetails";

    SharedPreferences userLocalDatabase;
    Context context;

    public UserLocalStorage(Context context){
        this.context = context;
        userLocalDatabase = context.getSharedPreferences(SP_NAME,0);
    }

    public void storeUserData(User user){
        SharedPreferences.Editor editor = userLocalDatabase.edit();
        editor.putInt("id",user.id);
        editor.putString("fn", user.firstname);
        editor.putString("ln",user.lastname);
        editor.putString("email", user.email);
        editor.putString("password",user.password);
        editor.apply();
    }

    public User getLoggedInUser(){
        int id = userLocalDatabase.getInt("id",0);
        String email = userLocalDatabase.getString("email","email");
        String password = userLocalDatabase.getString("password","password");
        String fistname = userLocalDatabase.getString("fn","fn");
        String lastname = userLocalDatabase.getString("ln","ln");

        User user = new User(id,fistname,lastname,email,password);
        return user;
    }

    public void setUserLoggedIn(boolean log){
        SharedPreferences.Editor editor = userLocalDatabase.edit();
        editor.putBoolean("logged",log);
        editor.apply();
    }

    public boolean getUserLoggedIn(){
        if(userLocalDatabase.getBoolean("logged",false) == true){
            return true;
        }else{
            return false;
        }
    }

    public void setCompleted(boolean log){
        SharedPreferences.Editor editor = userLocalDatabase.edit();
        editor.putBoolean("completed",log);
        editor.apply();
    }

    public String getCompleted(){
        if(userLocalDatabase.getBoolean("completed",false) == true){
            return "true";
        }else{
            return "false";
        }
    }

    public void clearDatabase(){
        SharedPreferences.Editor editor = userLocalDatabase.edit();
        editor.clear();
        editor.apply();
    }
}
