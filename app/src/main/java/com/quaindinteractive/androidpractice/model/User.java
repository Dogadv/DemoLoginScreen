package com.quaindinteractive.androidpractice.model;

public class User {

    private int id;
    private String username;
    private String password;

    void setID(final int id){
        this.id = id;
    }
    void setUsername(final String username) {
        this.username = username;
    }
    void setPassword(final String password) {
        this.password = password;
    }

    int getId(){
        return  id;
    }
    public String getUsername() {
        return username;
    }
    public String getPassword() {
        return password;
    }
}
