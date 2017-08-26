package com.quaindinteractive.androidpractice.view;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.quaindinteractive.androidpractice.R;
import com.quaindinteractive.androidpractice.common.Constants;
import com.quaindinteractive.androidpractice.model.DatabaseHelper;
import com.quaindinteractive.androidpractice.model.PreferencesHelper;
import com.quaindinteractive.androidpractice.model.UserModel;
import com.quaindinteractive.androidpractice.presenter.LoginContract;
import com.quaindinteractive.androidpractice.presenter.LoginPresenter;
import com.quaindinteractive.androidpractice.presenter.RegisterPresenter;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LoginActivity extends AppCompatActivity implements LoginContract {

    @BindView(R.id.username)
    EditText username;

    @BindView(R.id.password)
    EditText password;

    @BindView(R.id.login)
    Button login;

    @BindView(R.id.register)
    TextView register;

    @BindView(R.id.errorMessageLogin)
    TextView errorMessage;

    private ProgressDialog progress;
    private DatabaseHelper databaseHelper;
    private PreferencesHelper preferencesHelper;
    private UserModel model;
    private LoginPresenter presenter;

    public static void createLogin(Context context) {
        Intent intent = new Intent(context, LoginActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);

        databaseHelper = new DatabaseHelper(this);
        preferencesHelper = new PreferencesHelper(this);
        model = new UserModel(databaseHelper);
        presenter = new LoginPresenter(model, preferencesHelper);

        presenter.attachView(this);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.onLoginPressed(username.getText().toString(), password.getText().toString());
            }
        });

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.onRegisterPressed();
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        presenter.onViewResume();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        presenter.detachView();
        presenter = null;
        model.dispose();
        model = null;
        databaseHelper = null;
        progress = null;
        preferencesHelper = null;
    }

    public void showToast(final String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showProgress() {
        progress = ProgressDialog.show(this, "", "Please, wait.");
    }

    @Override
    public void hideProgress() {
        if(progress != null) progress.hide();
    }

    @Override
    public void finishView() {
        finish();
    }

    @Override
    public EditText getUsername() {
        return username;
    }

    @Override
    public EditText getPassword() {
        return password;
    }

    public Context getContext() {
        return this;
    }

    @Override
    public void setErrorMessage(String message) {
        errorMessage.setText(message);
    }
}
