package com.quaindinteractive.androidpractice.model;

import android.content.Context;
import android.content.SharedPreferences;

public class PreferencesHelper {

    private static final String FILE_NAME = "currentUser";
    private static final String USERNAME = "username";
    private static final String PASSWORD = "password";
    private SharedPreferences.Editor editor;

    SharedPreferences sharedPrefs;

    public PreferencesHelper(Context context){
        sharedPrefs = context.getSharedPreferences(FILE_NAME, 0);
        editor = sharedPrefs.edit();
    }

    public String getCurrentUsername() {
        return sharedPrefs.getString(USERNAME, "null");
    }

    public String getCurrentPassword() {
        return sharedPrefs.getString(PASSWORD, "null");
    }

    public void changeUser(String username, String password) {
        editor.putString(USERNAME, username);
        editor.putString(PASSWORD, password);
        editor.commit();
    }

    public void clearUser() {
        editor.putString(USERNAME, "null");
        editor.putString(PASSWORD, "null");
        editor.commit();
    }
}
