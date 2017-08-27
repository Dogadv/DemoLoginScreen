package com.quaindinteractive.androidpractice.presenter;

import android.widget.Button;
import android.widget.EditText;

public interface RegisterContract extends ViewContract{
    void setErrorMessage(final String message, EditText editText, int color);
    EditText getPasswordAgain();
    Button getRegister();
}
