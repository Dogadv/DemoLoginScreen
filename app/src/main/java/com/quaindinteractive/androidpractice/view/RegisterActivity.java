package com.quaindinteractive.androidpractice.view;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.quaindinteractive.androidpractice.R;
import com.quaindinteractive.androidpractice.model.DatabaseHelper;
import com.quaindinteractive.androidpractice.model.UserModel;
import com.quaindinteractive.androidpractice.presenter.RegisterContract;
import com.quaindinteractive.androidpractice.presenter.RegisterPresenter;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RegisterActivity extends AppCompatActivity implements RegisterContract {

    @BindView(R.id.errorMessageRegister)
    TextView errorMessage;

    @BindView(R.id.usernameReg)
    EditText username;

    @BindView(R.id.passwordReg)
    EditText password;

    @BindView(R.id.passwordAgainReg)
    EditText passwordAgain;

    @BindView(R.id.register)
    Button register;

    private ProgressDialog progressDialog;
    private DatabaseHelper dbHelper;
    private UserModel userModel;
    private RegisterPresenter presenter;

    public  static void createRegister(Context context) {
        Intent intent = new Intent(context, RegisterActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        ButterKnife.bind(this);
        dbHelper = new DatabaseHelper(this);
        userModel = new UserModel(dbHelper);
        presenter = new RegisterPresenter(this, userModel);
        presenter.onViewCreate();

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.onRegisterPressed(username.getText().toString(), password.getText().toString());
            }
        });

        username.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                presenter.onUsernameChanged(username.getText());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        password.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                presenter.onPasswordChanged(password.getText(), passwordAgain.getText());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        passwordAgain.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                presenter.onPasswordChanged(password.getText(), passwordAgain.getText());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        presenter.detachAll();
        presenter = null;
        userModel.dispose();
        userModel = null;
        dbHelper = null;
        progressDialog = null;
    }

    @Override
    public void showProgress() {
        progressDialog = ProgressDialog.show(this, "", "Please, wait.");
    }

    @Override
    public void hideProgress() {
        if(progressDialog != null) progressDialog.hide();
    }

    public void setErrorMessage(final String message, EditText editText, int color) {
        editText.setTextColor(color);
        errorMessage.setText(message);
    }

    @Override
    public EditText getUsername() {
        return username;
    }

    @Override
    public EditText getPassword() {
        return password;
    }

    @Override
    public EditText getPasswordAgain(){
        return passwordAgain;
    }

    @Override
    public Button getRegister(){
        return register;
    }

    @Override
    public Context getContext() {
        return this;
    }

    @Override
    public void finishView() {
        finish();
    }
}
