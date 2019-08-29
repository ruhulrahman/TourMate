package com.example.tourmate.helper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.Nullable;

public class ExpenseDatabase extends Database {

    public static String TABLE_NAME = "expense";
    public static String COL_ID = "Id";
    public static String COL_AMOUNT = "Amount";
    public static String COL_PAYMENT_TYPE = "PaymentType";
    public static String COL_DATE = "Date";
    public static String COL_TIME = "Time";
    public static String COL_DESCRIPTION = "Description";
    public static String COL_COST_TYPE = "CostType";

    public static String create_table = "create table "+TABLE_NAME+"(Id integer primary key, Amount Text, PaymentType Text, Date Text, Time Text, Description Text, CostType Text)";

    public ExpenseDatabase(Context context) {
        super(context, DATABASE_NAME, null, VERSION);
    }

    public ExpenseDatabase(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    public void onCreate(SQLiteDatabase db) {
        db.execSQL(create_table);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public long insertData(String amount, String payment,String date,String time,String desc,String cost){
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_AMOUNT, amount);
        contentValues.put(COL_PAYMENT_TYPE, payment);
        contentValues.put(COL_DATE, date);
        contentValues.put(COL_TIME, time);
        contentValues.put(COL_DESCRIPTION, desc);
        contentValues.put(COL_COST_TYPE, cost);

        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        long id = sqLiteDatabase.insert(TABLE_NAME, null, contentValues);
        sqLiteDatabase.close();
        return id;
    }

    public Cursor showData(){
        String showAll = "Select * From "+TABLE_NAME;
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery(showAll, null);
        return  cursor;
    }

    public void deleteData(int id){
        getReadableDatabase().delete(TABLE_NAME, "Id=?", new String[]{String.valueOf(id)});
    }

    public void updateData(int id, String amount, String payment,String date,String time,String desc,String cost){
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_ID, id);
        contentValues.put(COL_AMOUNT, amount);
        contentValues.put(COL_PAYMENT_TYPE, payment);
        contentValues.put(COL_DATE, date);
        contentValues.put(COL_TIME, time);
        contentValues.put(COL_DESCRIPTION, desc);
        contentValues.put(COL_COST_TYPE, cost);

        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        sqLiteDatabase.update(TABLE_NAME, contentValues, "Id=?", new String[]{String.valueOf(id)});
        sqLiteDatabase.close();
    }

}
