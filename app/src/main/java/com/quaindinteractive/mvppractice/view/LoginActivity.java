package com.quaindinteractive.mvppractice.view;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.quaindinteractive.mvppractice.R;
import com.quaindinteractive.mvppractice.common.Constants;

public class LoginActivity extends AppCompatActivity {

    public static void createLogin(Context context) {
        startActivity(context, Constants.AuthMode.UNAUTHORIZED);
    }

    private static void startActivity(Context context, Constants.AuthMode authMode) {
        Intent intent = new Intent(context, LoginActivity.class);
        intent.putExtra(Constants.EXTRA_MODE, authMode);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

}
