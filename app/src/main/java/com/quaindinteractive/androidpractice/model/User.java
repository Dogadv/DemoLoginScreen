package com.quaindinteractive.androidpractice.model;

public class User {

    int id;
    String username;
    String password;

    public void setID(final int id){
        this.id = id;
    }
    public void setUsername(final String username) {
        this.username = username;
    }
    public void setPassword(final String password) {
        this.password = password;
    }

    public int getId(){
        return  id;
    }
    public String getUsername() {
        return username;
    }
    public String getPassword() {
        return password;
    }
}
