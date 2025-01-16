package com.example.flatfeet;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper{
    private static final String DB_NAME = "users.db";
    private static final int DB_VERSION = 1;
    private static final String TB_NAME = "users";

    public DBHelper(Context context){
        super(context, DB_NAME, null , 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("Create table " + TB_NAME + "(id text primary key, password text)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // table삭제 후 재생성
        db.execSQL("Drop table if exists " + TB_NAME);
        onCreate(db);
    }
    public void addUser(String id, String password){
        SQLiteDatabase db = this.getWritableDatabase();
        //읽고쓰는 db 불러옴
        ContentValues values = new ContentValues();
        values.put("id", id);
        values.put("password", password);
        long result = db.insert(TB_NAME, null, values);
        db.close();
    }

    public boolean checkUser(String id){
        SQLiteDatabase db = this.getReadableDatabase();
        //읽기전용 db불러옴
        Cursor cursor = db.rawQuery("SELECT * FROM users WHERE id = ?", new String[]{id});
        return cursor.getCount() > 0 ? true : false;
    }

    public boolean checkPassword(String id, String password){
        SQLiteDatabase db = this.getReadableDatabase();
        //읽기전용 db불러옴
        Cursor cursor = db.rawQuery("SELECT * FROM users WHERE id = ? and password = ?", new String[]{id, password});
        return cursor.getCount() > 0 ? true : false;
    }
}
