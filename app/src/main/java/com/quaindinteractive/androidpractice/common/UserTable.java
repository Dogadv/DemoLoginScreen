package com.quaindinteractive.androidpractice.common;

public class UserTable {

    public static final String TABLE = "users";

    public static class COLUMN {
        public static final String ID = "_id";
        public static final String USERNAME = "username";
        public static final String PASSWORD = "password";
    }

    public static final String CREATE_SCRIPT =
            String.format("create table %s (" +
                          "%s integer primary key autoincrement," +
                          "%s text," +
                          "%s text" + ");",
                          TABLE, COLUMN.ID, COLUMN.USERNAME, COLUMN.PASSWORD);
}
