package com.epifi.epifi.Utils;


import com.google.firebase.database.IgnoreExtraProperties;

/**
 * Created by srgera on 20/3/18.
 */
@IgnoreExtraProperties
public class User {
    private String user_id;
    public String username;
    public String email;

    public User() {
        // Default constructor required for calls to DataSnapshot.getValue(User.class)
    }

    public User(String user_id,String username ,String email) {
        this.user_id = user_id;
        this.username = username;
        this.email = email;
    }



    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}