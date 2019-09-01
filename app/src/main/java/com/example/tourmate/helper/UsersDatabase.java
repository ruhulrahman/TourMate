package com.example.tourmate.helper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.Nullable;

public class UsersDatabase extends Database {
    public static String TABLE_NAME = "users";
    public static String COL_ID = "Id";
    public static String COL_Name = "Name";
    public static String COL_Email = "Email";
    public static String COL_Phone = "Phone";
    public static String COL_TOUR_ID = "TourId";
    public Context contex;

    public static String create_table = "create table " + TABLE_NAME + "(Id integer primary key, Name Text, Email Text, Phone Text, TourId Text)";

    public UsersDatabase(Context context) {
        super(context, DATABASE_NAME, null, VERSION);
    }

    public void onCreate(SQLiteDatabase db) {
        db.execSQL(create_table);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public long insertData(String name, String email, String phone, String tourId) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_Name, name);
        contentValues.put(COL_Email, email);
        contentValues.put(COL_Phone, phone);
        contentValues.put(COL_TOUR_ID, tourId);

        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        long id = sqLiteDatabase.insert(TABLE_NAME, null, contentValues);
        sqLiteDatabase.close();
        return id;
    }
}
