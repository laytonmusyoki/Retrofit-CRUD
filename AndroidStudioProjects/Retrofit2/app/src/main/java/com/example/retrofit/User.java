package com.example.retrofit;

import android.service.autofill.UserData;

import java.util.List;

public class User {
    String status;

    List<userData> users;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<userData> getUsers() {
        return users;
    }

    public void setUsers(List<userData> users) {
        this.users = users;
    }
}


class userData{
    String id,email,username;

    public userData(String id, String email, String username) {
        this.id = id;
        this.email = email;
        this.username = username;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}