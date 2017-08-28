package com.quaindinteractive.androidpractice.dagger.module;

import android.content.Context;

import com.quaindinteractive.androidpractice.dagger.DaggerApplication;
import com.quaindinteractive.androidpractice.model.DatabaseHelper;
import com.quaindinteractive.androidpractice.model.PreferencesHelper;
import com.quaindinteractive.androidpractice.model.UserModel;
import com.quaindinteractive.androidpractice.presenter.LoginPresenter;
import com.quaindinteractive.androidpractice.presenter.contract.LoginContract;
import com.quaindinteractive.androidpractice.presenter.contract.MainContract;
import com.quaindinteractive.androidpractice.presenter.MainPresenter;
import com.quaindinteractive.androidpractice.presenter.RegisterContract;
import com.quaindinteractive.androidpractice.presenter.RegisterPresenter;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class ApplicationModule {
    private final DaggerApplication application;

    public ApplicationModule(DaggerApplication application){this.application = application;}

    @Provides LoginPresenter provideLoginPresenter(LoginContract view, UserModel userModel, PreferencesHelper pHelper) {return new LoginPresenter(view, userModel, pHelper);}
    @Provides RegisterPresenter provideRegisterPresenter(RegisterContract view, UserModel userModel) {return new RegisterPresenter(view, userModel);}
    @Provides MainPresenter provideMainPresenter(MainContract view, PreferencesHelper pHelper) {return new MainPresenter(view, pHelper);}

    @Provides LoginContract provideLoginContract() {return ((LoginContract) application.getContract());}
    @Provides RegisterContract provideRegisterContract() {return ((RegisterContract)application.getContract());}
    @Provides MainContract provideMainContract() {return ((MainContract)application.getContract());}

    @Provides UserModel provideUserModel(DatabaseHelper dbHelper) {return new UserModel(dbHelper);}
    @Provides PreferencesHelper providePreferencesHelper(Context context){return new PreferencesHelper(context);}
    @Provides DatabaseHelper provideDatabaseHelper(Context context) {return new DatabaseHelper(context);}

    @Provides @Singleton Context provideContext() {return  application;}

}
