package com.quaindinteractive.androidpractice.presenter;

import android.content.Context;

public interface MainContract {
    void finishView();
    void setHelloText(final String message);
    Context getContext();
}
