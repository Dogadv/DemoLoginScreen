package com.quaindinteractive.androidpractice.dagger.component;

import com.quaindinteractive.androidpractice.dagger.module.ApplicationModule;
import com.quaindinteractive.androidpractice.view.LoginActivity;
import com.quaindinteractive.androidpractice.view.MainActivity;
import com.quaindinteractive.androidpractice.view.RegisterActivity;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {ApplicationModule.class})
public interface ApplicationComponent {

    void inject(LoginActivity loginActivity);
    void inject(RegisterActivity registerActivity);
    void inject(MainActivity mainActivity);
}
