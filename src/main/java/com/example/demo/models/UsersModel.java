package com.example.demo.models;

import java.io.Serializable;

public class UsersModel implements Serializable {
    private long id;
    private String username;
    private String password;

    public UsersModel(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public UsersModel() {

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "UsersModel [id=" + id + ", password=" + password + ", username=" + username + "]";
    }

}