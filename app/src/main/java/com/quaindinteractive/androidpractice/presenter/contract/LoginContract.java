package com.quaindinteractive.androidpractice.presenter.contract;

import android.content.Context;
import android.widget.EditText;

public interface LoginContract extends ViewContract{
    void setErrorMessage(final String message);
    void finishView();
    EditText getUsername();
    EditText getPassword();
    Context getContext();
}
