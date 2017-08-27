package com.quaindinteractive.androidpractice.presenter;

import android.content.ContentValues;
import android.graphics.Color;
import android.text.Editable;

import com.quaindinteractive.androidpractice.common.UserTable;
import com.quaindinteractive.androidpractice.model.User;
import com.quaindinteractive.androidpractice.model.UserModel;

import java.util.HashMap;

public class RegisterPresenter {

    private RegisterContract view;
    private UserModel userModel;
    private HashMap<String, User> users;

    private final String EMPTY = "";
    private final String PASSWORD_MATCH_ERROR = "Passwords do not match.";
    private final String USERNAME_EXISTS_ERROR = "This username is already used.\n";
    private final String USERNAME_CHARACTER_ERROR = "Invalid characters.\n";
    private String USERNAME_ERROR = "";
    private String PASSWORD_ERROR = "";
    private String ERROR = USERNAME_ERROR + PASSWORD_ERROR;
    private boolean isUsernameEmpty = true, isPasswordAgainEmpty = true;
    private int usernameColor, passwordColor;

    public RegisterPresenter(RegisterContract view, UserModel userModel) {
        this.view = view;
        this.userModel = userModel;
    }

    public void detachAll() {
        view = null;
        userModel = null;
        users = null;
    }

    public void onViewCreate() {
        loadUsers();
    }

    public void onRegisterPressed(String username, String password) {
        addUser(username, password);
    }

    private void registerButtonAvailable() {
        if(ERROR.isEmpty() && !isUsernameEmpty && !isPasswordAgainEmpty) view.getRegister().setEnabled(true);
        else view.getRegister().setEnabled(false);
        System.out.println(ERROR.isEmpty() && !isUsernameEmpty && !isPasswordAgainEmpty);
    }

    public void onUsernameChanged(final Editable username) {
       isUsernameEmpty = username.toString().isEmpty();

        if(username.toString().matches(".*\\W+.*"))  {
            USERNAME_ERROR = USERNAME_CHARACTER_ERROR;
            usernameColor = Color.RED;
        } else if(users.containsKey(username.toString())) {
            USERNAME_ERROR = USERNAME_EXISTS_ERROR;
            usernameColor = Color.RED;
        } else if (username.toString().matches(".*\\W+.*") && users.containsKey(username.toString())) {
            USERNAME_ERROR = USERNAME_EXISTS_ERROR + USERNAME_CHARACTER_ERROR;
            usernameColor = Color.RED;
        } else {
            USERNAME_ERROR = EMPTY;
            usernameColor = Color.BLACK;
        }
        ERROR = USERNAME_ERROR + PASSWORD_ERROR;
        view.setErrorMessage(ERROR, view.getUsername(), usernameColor);
        registerButtonAvailable();
    }

    public void onPasswordChanged(final Editable password, final Editable passwordAgain) {
        isPasswordAgainEmpty = passwordAgain.toString().isEmpty();
        if (!passwordAgain.toString().isEmpty() && !password.toString().contentEquals(passwordAgain)) {
            PASSWORD_ERROR = PASSWORD_MATCH_ERROR;
            passwordColor = Color.RED;
        } else {
            PASSWORD_ERROR = EMPTY;
            passwordColor = Color.BLACK;
        }
        ERROR = USERNAME_ERROR + PASSWORD_ERROR;
        view.setErrorMessage(ERROR, view.getPasswordAgain(), passwordColor);
        registerButtonAvailable();
    }

    private void addUser(String username, String password) {
        ContentValues cv = new ContentValues(2);
        cv.put(UserTable.COLUMN.USERNAME, username);
        cv.put(UserTable.COLUMN.PASSWORD, password);
        view.showProgress();
        userModel.addUser(cv, new UserModel.AddusersCallback() {
            @Override
            public void onAdded() {
                view.hideProgress();
                view.finishView();
            }
        });
    }
    private void loadUsers() {
        userModel.loadUsers(new UserModel.LoadsersCallback() {
            @Override
            public void onLoaded(HashMap<String, User> usersDb) {
                users = usersDb;
            }
        });
    }
}
