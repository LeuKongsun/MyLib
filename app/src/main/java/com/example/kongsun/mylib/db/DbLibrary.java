package com.example.kongsun.mylib.db;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

import static com.example.kongsun.mylib.R.drawable.library;

/**
 * Created by kongsun on 6/30/17.
 */

public class DbLibrary extends SQLiteAssetHelper {
    private static final String DATABASE_NAME ="lib.db.sqlite";

    public DbLibrary(Context context) {
        super(context, DATABASE_NAME, null, null, 1);
    }


    public User login(String username, String password) {
        SQLiteDatabase db = getReadableDatabase();
        String condition = "_username = ? and _password = ?";
        String[] conditionArguments = {username, password};
        Cursor cursor = db.query("tblUser", null, condition, conditionArguments, null, null, null);
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            int id = cursor.getInt(0);
            String firstName = cursor.getString(3);
            String lastName = cursor.getString(4);
            User user = new User(id, username, password, firstName, lastName);
            cursor.close();
            return user;
        } else {
            cursor.close();
            return null;
        }
    }
}
