package com.quaindinteractive.androidpractice.model;

import android.content.ContentValues;
import android.database.Cursor;
import android.os.AsyncTask;

import java.util.HashMap;
import java.util.concurrent.TimeUnit;

public class UserModel {

    private static DatabaseHelper database;

    public UserModel(final DatabaseHelper database) {
        UserModel.database = database;
    }

    public void loadUsers(LoadsersCallback callback) {
        LoadUsersTask loadUsersTask = new LoadUsersTask(callback);
        loadUsersTask.execute();
    }

    public void addUser(ContentValues user, AddusersCallback callback) {
        AddUserTask addUserTask = new AddUserTask(callback);
        addUserTask.execute(user);
    }

    public interface LoadsersCallback {
        void onLoaded(HashMap<String, User> users);
    }

    public interface AddusersCallback {
        void onAdded();
    }

    static class LoadUsersTask extends AsyncTask<Void, Void, HashMap<String, User>> {

        final LoadsersCallback callback;

        LoadUsersTask(LoadsersCallback callback) {
            this.callback = callback;
        }
        @Override
        protected HashMap<String, User> doInBackground(Void... voids) {
            HashMap<String, User> users = new HashMap<>();
            Cursor cursor = database.getReadableDatabase().query(UserTable.TABLE, null, null, null, null, null, null);
            while(cursor.moveToNext()) {
                User user = new User();
                user.setID(cursor.getInt(cursor.getColumnIndex(UserTable.COLUMN.ID)));
                user.setUsername(cursor.getString(cursor.getColumnIndex(UserTable.COLUMN.USERNAME)));
                user.setPassword(cursor.getString(cursor.getColumnIndex(UserTable.COLUMN.PASSWORD)));
                users.put(cursor.getString(cursor.getColumnIndex(UserTable.COLUMN.USERNAME)), user);
                cursor.close();
            }
            return users;
        }

        @Override
        protected void onPostExecute(HashMap<String, User> users) {
            if (callback != null) callback.onLoaded(users);
        }
    }

    static class AddUserTask extends  AsyncTask<ContentValues, Void, Void> {

        final AddusersCallback callback;

        AddUserTask(AddusersCallback callback) {
            this.callback = callback;
        }

        @Override
        protected Void doInBackground(ContentValues... params) {
            ContentValues user = params[0];
            database.getWritableDatabase().insert(UserTable.TABLE, null, user);
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        public void onPostExecute(Void v) {
            if(callback != null) callback.onAdded();
        }
    }

    public void dispose() {
        database = null;
    }
}
