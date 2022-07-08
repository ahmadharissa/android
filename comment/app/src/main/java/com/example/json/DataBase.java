package com.example.json;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DataBase extends SQLiteOpenHelper {
    public DataBase(Context context){
        super (context,"Login.db",null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
    String query="CREATE TABLE users(email TEXT PRIMARY KEY,password TEXT )";
    db.execSQL (query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }

    public boolean insertUser(String email, String password){
        SQLiteDatabase db = this.getWritableDatabase ();
        ContentValues contentValues = new ContentValues ();
        contentValues.put ("Email",email);
        contentValues.put ("Password",password);
        Long ins = db.insert ("users",null,contentValues);
        if (ins==-1)
            return false;
        else
            return true;
    }

    public boolean checkEmai(String email){
        SQLiteDatabase db = this.getReadableDatabase ();
        Cursor cursor = db.rawQuery ("SELECT * FROM users WHERE email=?",new String[]{email});
        if (cursor.getCount () > 0)
            return false;
        else
            return true;
    }
}
