package com.quaindinteractive.androidpractice.presenter;

import com.quaindinteractive.androidpractice.model.PreferencesHelper;
import com.quaindinteractive.androidpractice.view.LoginActivity;
import com.quaindinteractive.androidpractice.view.MainActivity;

public class MainPresenter {

    private MainContract view;
    private PreferencesHelper preferencesHelper;

    public MainPresenter(PreferencesHelper preferencesHelper) {
        this.preferencesHelper = preferencesHelper;
    }

    public void attachView(MainActivity view) {
        this.view = view;
    }

    public void viewIsReady() {
        view.setHelloText("Hello, " + preferencesHelper.getCurrentUsername() + "! Now you can log out ;)");
    }

    public void detachView() {
        this.view = null;
    }

    public void onLogoutPressed() {
        preferencesHelper.clearUser();
        LoginActivity.createLogin(view.getContext());
        view.finishView();
    }


}
