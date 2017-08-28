package com.quaindinteractive.androidpractice.presenter;

import com.quaindinteractive.androidpractice.model.PreferencesHelper;
import com.quaindinteractive.androidpractice.presenter.contract.MainContract;
import com.quaindinteractive.androidpractice.view.LoginActivity;

public class MainPresenter {

    private MainContract view;
    private PreferencesHelper pHelper;

    public MainPresenter(MainContract view, PreferencesHelper preferencesHelper) {
        this.view = view;
        this.pHelper = preferencesHelper;
    }

    public void viewIsReady() {
        view.setHelloText("Hello, " + pHelper.getCurrentUsername() + "! Now you can log out ;)");
    }

    public void detachAll() {
        view = null;
        pHelper = null;
    }

    public void onLogoutPressed() {
        pHelper.clearUser();
        LoginActivity.createLogin(view.getContext());
        view.finishView();
    }
}
