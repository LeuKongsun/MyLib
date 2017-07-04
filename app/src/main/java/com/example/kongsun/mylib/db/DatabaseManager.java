package com.example.kongsun.mylib.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.kongsun.mylib.dataset.Category;

import java.util.List;

/**
 * Created by kongsun on 5/13/17.
 */

public class DatabaseManager extends SQLiteOpenHelper {
    //It is a kind of abstract class. abstract method need to implement.
    //Default constructor
    //Define Database name and Version. Name "E-library" and Version 1.
    public DatabaseManager(Context context) {
        super(context, "E-library.db", null, 1);
    }
// Two function below are abstract method
// Create table schema(structure)
    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE tbCategory(id integer primary key autoincrement," +
                "title text)";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void insertCategory(Category category) {
        ContentValues row = new ContentValues();
        row.put("title", category.getCategory_list());
        SQLiteDatabase db = getWritableDatabase();
        db.insert("tbCategory", null, row);
    }

    public Category[] getAllCategories() {
        SQLiteDatabase db = getReadableDatabase();
        String[] columns = {"id", "title"};
        Cursor cursor = db.query("tbCategory", columns, null, null, null, null, "id desc"); //null mean all
        Category[] categories = new Category[cursor.getCount()];
        int index = 0;
        while (cursor.moveToNext()) {
            int id = cursor.getInt(0);
            String title = cursor.getString(1);
            Category category = new Category(id, title);
            categories[index] = category;
            index++;
        }
        return categories;
    }


}
