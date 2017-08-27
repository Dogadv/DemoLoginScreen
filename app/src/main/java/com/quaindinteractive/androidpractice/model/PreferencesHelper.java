package com.quaindinteractive.androidpractice.model;

import android.content.Context;
import android.content.SharedPreferences;

public class PreferencesHelper {

    private static final String FILE_NAME = "currentUser";
    private static final String USERNAME = "username";
    private static final String PASSWORD = "password";

    private SharedPreferences sharedPrefs;

    public PreferencesHelper(Context context){
        sharedPrefs = context.getSharedPreferences(FILE_NAME, 0);
    }

    public String getCurrentUsername() {
        return sharedPrefs.getString(USERNAME, "null");
    }

    public String getCurrentPassword() {
        return sharedPrefs.getString(PASSWORD, "null");
    }

    public void changeUser(String username, String password) {
        getEditor().putString(USERNAME, username).apply();
        getEditor().putString(PASSWORD, password).apply();
    }

    public void clearUser() {
        getEditor().putString(USERNAME, "null").apply();
        getEditor().putString(PASSWORD, "null").apply();
    }

    private SharedPreferences.Editor getEditor() {
        return sharedPrefs.edit();
    }
}
