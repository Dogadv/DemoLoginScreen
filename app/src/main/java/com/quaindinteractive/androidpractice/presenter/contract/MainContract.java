package com.quaindinteractive.androidpractice.presenter.contract;

import android.content.Context;

import com.quaindinteractive.androidpractice.presenter.contract.ViewContract;

public interface MainContract extends ViewContract {
    void finishView();
    void setHelloText(final String message);
    Context getContext();
}
