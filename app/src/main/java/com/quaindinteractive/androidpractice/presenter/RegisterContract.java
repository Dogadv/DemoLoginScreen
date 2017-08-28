package com.quaindinteractive.androidpractice.presenter;

import android.content.Context;
import android.widget.Button;
import android.widget.EditText;

import com.quaindinteractive.androidpractice.presenter.contract.ViewContract;

public interface RegisterContract extends ViewContract {
    void setErrorMessage(final String message, EditText editText, int color);
    EditText getPasswordAgain();
    Button getRegister();
    void showProgress();
    void hideProgress();
    void finishView();
    EditText getUsername();
    EditText getPassword();
    Context getContext();
}
