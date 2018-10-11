package com.rit.logisticapplication.database;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME="user";
    private static  final int DATABASE_VERSION=1;
    public static final String TABLE_USER="user";
    public static final String COLUMN_USER_ID="id";
    public static final String COLUMN_USER_NAME="name";
    public static final String COLUMN_USER_PASSWORD="password";
    public static final String CREATE_USER_QUERY="create table "+TABLE_USER
            +"("+COLUMN_USER_ID+" integer primary key autoincrement,"
            +COLUMN_USER_NAME+" text,"+COLUMN_USER_PASSWORD+" text);";

    public DatabaseHelper(Context context) {
        super( context, DATABASE_NAME, null, DATABASE_VERSION );
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        try {
            db.execSQL( CREATE_USER_QUERY );
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        try {
            db.execSQL("drop table if EXISTS "+TABLE_USER);
            onCreate(db);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
