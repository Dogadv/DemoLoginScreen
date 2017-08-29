package com.quaindinteractive.androidpractice.dagger;

import android.app.Application;

import com.quaindinteractive.androidpractice.dagger.component.ApplicationComponent;
import com.quaindinteractive.androidpractice.dagger.component.DaggerApplicationComponent;
import com.quaindinteractive.androidpractice.dagger.module.ApplicationModule;
import com.quaindinteractive.androidpractice.presenter.contract.ViewContract;

public class DaggerApplication extends Application {
    ApplicationComponent component;
    ViewContract contract;

    public void onCreate() {
        super.onCreate();

        component = DaggerApplicationComponent.builder().applicationModule(new ApplicationModule(this)).build();
    }

    public ApplicationComponent getAppComponent(ViewContract contract) {
        this.contract = contract;
        return component;
    }

    public ViewContract getContract() {
        return contract;
    }
}
