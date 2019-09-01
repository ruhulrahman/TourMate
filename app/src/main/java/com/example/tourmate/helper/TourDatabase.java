package com.example.tourmate.helper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.Nullable;

public class TourDatabase extends Database {
    public static String TABLE_NAME = "tours";
    public static String COL_ID = "Id";
    public static String COL_TOUT_TITLE = "TourTitle";
    public static String COL_TOUR_LOCATION = "TourLocation";
    public static String COL_SATRT_DATE = "StartDate";
    public static String COL_END_DATE = "EndDate";
    public static String COL_TOUR_DESC = "TourDesc";
    public Context contex;

    public static String create_table = "create table " + TABLE_NAME + "(Id integer primary key, TourTitle Text, TourLocation Text, StartDate Text, EndDate Text, TourDesc Text)";

    public TourDatabase(Context context) {
        super(context, DATABASE_NAME, null, VERSION);
    }

    public TourDatabase(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    public void onCreate(SQLiteDatabase db) {
        db.execSQL(create_table);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public long insertData(String TourTitle, String TourLocation, String StartDate, String EndDate, String TourDesc) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_TOUT_TITLE, TourTitle);
        contentValues.put(COL_TOUR_LOCATION, TourLocation);
        contentValues.put(COL_SATRT_DATE, StartDate);
        contentValues.put(COL_END_DATE, EndDate);
        contentValues.put(COL_TOUR_DESC, TourDesc);

        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        long id = sqLiteDatabase.insert(TABLE_NAME, null, contentValues);
        sqLiteDatabase.close();
        return id;
    }

    public long insertData(String TourTitle, String TourLocation, String StartDate, String EndDate) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_TOUT_TITLE, TourTitle);
        contentValues.put(COL_TOUR_LOCATION, TourLocation);
        contentValues.put(COL_SATRT_DATE, StartDate);
        contentValues.put(COL_END_DATE, EndDate);

        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        long id = sqLiteDatabase.insert(TABLE_NAME, null, contentValues);
        sqLiteDatabase.close();
        return id;
    }

    public Cursor showData() {
        String showAll = "Select * From " + TABLE_NAME;
        SQLiteDatabase sqLiteDatabase = null;
        sqLiteDatabase = this.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery(showAll, null);
        return cursor;

    }

    public void deleteData(int id) {
        getReadableDatabase().delete(TABLE_NAME, "Id=?", new String[]{String.valueOf(id)});
    }

    public long updateData(int id, String TourTitle, String TourLocation, String StartDate, String EndDate, String TourDesc) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_ID, id);
        contentValues.put(COL_TOUT_TITLE, TourTitle);
        contentValues.put(COL_TOUR_LOCATION, TourLocation);
        contentValues.put(COL_SATRT_DATE, StartDate);
        contentValues.put(COL_END_DATE, EndDate);
        contentValues.put(COL_TOUR_DESC, TourDesc);

        SQLiteDatabase sqLiteDatabase = null;
        sqLiteDatabase = this.getReadableDatabase();
        sqLiteDatabase.update(TABLE_NAME, contentValues, "Id=?", new String[]{String.valueOf(id)});
        sqLiteDatabase.close();
        return 0;
    }

    public long updateData(int id, String TourTitle, String TourLocation, String StartDate, String EndDate) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_ID, id);
        contentValues.put(COL_TOUT_TITLE, TourTitle);
        contentValues.put(COL_TOUR_LOCATION, TourLocation);
        contentValues.put(COL_SATRT_DATE, StartDate);
        contentValues.put(COL_END_DATE, EndDate);

        SQLiteDatabase sqLiteDatabase = null;
        sqLiteDatabase = this.getReadableDatabase();
        sqLiteDatabase.update(TABLE_NAME, contentValues, "Id=?", new String[]{String.valueOf(id)});
        sqLiteDatabase.close();
        return 0;
    }

}
