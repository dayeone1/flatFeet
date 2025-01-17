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
        super(context, DB_NAME, null , 2);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + TB_NAME + " (id TEXT PRIMARY KEY, password TEXT)");
        db.execSQL("CREATE TABLE product (num INTEGER PRIMARY KEY, name TEXT, price TEXT)");
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // table삭제 후 재생성, 이 코드에선 사용할 일 없음
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

    public void addProduct(int num, String name, String price){
        SQLiteDatabase db = this.getWritableDatabase();
        //읽고쓰는 db 불러옴
        ContentValues values = new ContentValues();
        values.put("num", num);
        values.put("name", name);
        values.put("price", price);
        long result = db.insert("product", null, values);
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

    public String getName(int num) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = null;
        String name = null;
        try {
            cursor = db.query("product", new String[]{"name"}, "num = ?", new String[]{String.valueOf(num)}, null, null, null);

            if (cursor != null && cursor.moveToFirst()) {
                // name과 price를 배열에 저장
                name = cursor.getString(cursor.getColumnIndexOrThrow("name"));
            }
        } catch (Exception e) {
            e.printStackTrace(); // 오류 메시지 출력
        } finally {
            if (cursor != null) {
                cursor.close(); // 커서 닫기
            }
        }
        return name; // productInfo가 null일 경우 해당 ID의 데이터가 없음을 의미
    }

    public String getPrice(int num) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = null;
        String price = "데이터 불러오기 실패";

        try {
            cursor = db.query("product", new String[]{"price"}, "num = ?", new String[]{String.valueOf(num)}, null, null, null);

            if (cursor != null && cursor.moveToFirst()) {
                price = cursor.getString(cursor.getColumnIndexOrThrow("price"));
            }
        } catch (Exception e) {
            e.printStackTrace(); // 오류 메시지 출력
        } finally {
            if (cursor != null) {
                cursor.close(); // 커서 닫기
            }
        }
        return price; // productInfo가 null일 경우 해당 ID의 데이터가 없음을 의미
    }



}
