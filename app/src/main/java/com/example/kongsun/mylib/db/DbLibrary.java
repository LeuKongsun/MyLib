package com.example.kongsun.mylib.db;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;
import com.example.kongsun.mylib.db.User;
/**
 * Created by kongsun on 6/30/17.
 */

public class DbLibrary extends SQLiteAssetHelper {

    public DbLibrary(Context context) {
        super(context, "E-library.db.sqlite", null, null, 1);
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
