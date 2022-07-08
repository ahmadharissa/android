package com.example.post.SQLite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.EditText;

public class DataBase extends SQLiteOpenHelper {
    public DataBase(Context context) {
        super (context, "user.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE users(email TEXT PRIMARY KEY , password TEXT NOT NULL)";
        db.execSQL (query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL ("DROP TABLE IF EXISTS users");
        onCreate (db);
    }

    public boolean insert(String email, String password) {
        SQLiteDatabase db = this.getWritableDatabase ();
        ContentValues contentValues = new ContentValues ();
        contentValues.put ("email", String.valueOf (email));
        contentValues.put ("password", String.valueOf (password));
        Long ins = db.insert ("users", null, contentValues);
        if (ins == -1)
            return false;
        else
            return true;
    }

    public boolean checkEmail(String email) {
        SQLiteDatabase db = this.getReadableDatabase ();
        Cursor cursor = db.rawQuery ("SELECT * FROM users WHERE email=?", new String[]{email});
        if (cursor.getCount () > 0)
            return false;
        else
            return true;
    }

    public boolean exist() {
        SQLiteDatabase db = this.getReadableDatabase ();
        Cursor cursor = db.rawQuery ("SELECT * FROM users", null);
        if (cursor.getCount () > 0)
            return true;
        else
            return false;
    }

    public String email() {
        SQLiteDatabase db = this.getReadableDatabase ();
        Cursor cursor = db.rawQuery ("SELECT email FROM users", null);
        String email = null;
        while (cursor.moveToNext ()) {
            email = cursor.getString (0);
        }
        return email;
    }

    public void deletAllData() {
        SQLiteDatabase db = this.getWritableDatabase ();
        Cursor cursor = db.rawQuery ("SELECT email FROM users", null);
        String email = null;
        while (cursor.moveToNext ()) {
            email = cursor.getString (0);
        }
        db.delete ("users", "email=?", new String[]{email});
    }
}
