package com.quaindinteractive.androidpractice.presenter;


import com.quaindinteractive.androidpractice.model.PreferencesHelper;
import com.quaindinteractive.androidpractice.model.User;
import com.quaindinteractive.androidpractice.model.UserModel;
import com.quaindinteractive.androidpractice.view.MainActivity;
import com.quaindinteractive.androidpractice.view.RegisterActivity;

import java.util.HashMap;

public class LoginPresenter {

    private LoginContract view;
    private UserModel model;
    private PreferencesHelper preferencesHelper;
    private HashMap<String, User> users;

    private static final String INCORRECT_ERROR = "Incorrect username or password.";
    private static final String NOT_FILLED_EROR = "Not all fields are filled.";
    private static final String EMPTY = "";
    private String ERROR;

    public LoginPresenter(UserModel model, final PreferencesHelper preferencesHelper) {
        this.model = model;
        this.preferencesHelper = preferencesHelper;
        model.loadUsers(new UserModel.LoadsersCallback() {
            @Override
            public void onLoaded(HashMap<String, User> usersDb) {
                users = usersDb;
                if(users.containsKey(preferencesHelper.getCurrentUsername())) {
                    User user = users.get(preferencesHelper.getCurrentUsername());
                    if (user.getPassword().contentEquals(preferencesHelper.getCurrentPassword())) {
                        MainActivity.createMain(view.getContext());
                        view.finishView();
                    }
                }
            }
        });
    }

    public void attachView(LoginContract view) {
        this.view = view;
    }

    public void detachView() {
        view = null;
        model = null;
        users = null;
        preferencesHelper = null;
    }

    public void onViewResume() {
        model.loadUsers(new UserModel.LoadsersCallback() {
            @Override
            public void onLoaded(HashMap<String, User> usersDb) {
                users = usersDb;
            }});
    }

    public void onLoginPressed(String username, String password) {
        if(!username.isEmpty() && !password.isEmpty()) {
            if (users.containsKey(username)) {
                User user = users.get(username);
                if (user.getPassword().contentEquals(password)) {
                    ERROR = EMPTY;
                    preferencesHelper.changeUser(username, password);
                    MainActivity.createMain(view.getContext());
                    view.finishView();
                } else {
                    ERROR = INCORRECT_ERROR;
                }
            } else {
                ERROR = INCORRECT_ERROR;
            }
        } else {
            ERROR = NOT_FILLED_EROR;
        }
        view.setErrorMessage(ERROR);
    }
    public void onRegisterPressed() {
        RegisterActivity.createRegister(view.getContext());
    }

    private void loadUsers() {
        model.loadUsers(new UserModel.LoadsersCallback() {
            @Override
            public void onLoaded(HashMap<String, User> usersDb) {
                users = usersDb;
            }
        });
    }
}
