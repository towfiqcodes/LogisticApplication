package com.rit.logisticapplication.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.rit.logisticapplication.models.User;

public class UserDatabaseManager {
    DatabaseHelper databaseHelper;

    public UserDatabaseManager(Context context) {
        databaseHelper=new DatabaseHelper( context );
    }
    public long addUser(User user){
        SQLiteDatabase sqLiteDatabase=databaseHelper.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(DatabaseHelper.COLUMN_USER_NAME,user.getUserName());
        contentValues.put(DatabaseHelper.COLUMN_USER_PASSWORD,user.getUserPassword());
        long insertedRow=sqLiteDatabase.insert(DatabaseHelper.TABLE_USER,null,contentValues);
        sqLiteDatabase.close();
        return insertedRow;
    }
    public Boolean findpassword(String name, String password) {
        SQLiteDatabase sqLiteDatabase = databaseHelper.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery( "SELECT * FROM " + DatabaseHelper.TABLE_USER, null );
        boolean result = false;
        if (cursor.getCount() == 0) {
           /*Toast t=Toast.makeText(StudentDatabaseManager.this,"No data found! ",Toast.LENGTH_LONG);
            t.show();*/
        } else {
            while (cursor.moveToNext()) {
                String userName = cursor.getString( cursor.getColumnIndex( DatabaseHelper.COLUMN_USER_NAME ) );
                String userPass = cursor.getString( cursor.getColumnIndex( DatabaseHelper.COLUMN_USER_PASSWORD ) );
                if (userName.equals( name ) && userPass.equals( password )) {
                    result = true;
                    break;
                }
            }
        }
        return result;
    }
}
