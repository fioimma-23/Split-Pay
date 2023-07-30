package com.example.propay;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {
    static String datebasename = "datebase.db";
    static String tablename1 = "tables1";
    static String tablename2 = "tables2";
    static String t1column1 = "uname";
    static String t1column2 = "password";
    static String t1column3 = "income";
    static String t1column4 = "out";

    static String t2column1 = "uname";
    static String t2column2 = "toname";
    static String t2column3 = "tocost";
    static String t2column4 = "inorout";
    static int version = 1;
    static String tb1 = "create table "+tablename1+" ( "+ t1column1 +" TEXT NOT NULL , " + t1column2 + " TEXT , "+ t1column3 +" INTEGER , "+ t1column4 +" INTEGER ); ";
    static String tb2 = "create table "+tablename2+" ( "+ t2column1 +" TEXT NOT NULL , " + t2column2 + " TEXT , "+ t2column3 +" INTEGER , "+ t2column4 +" INTEGER ); ";

    DatabaseHelper(Context context){
        super(context,datebasename,null,version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(tb1);
        db.execSQL(tb2);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + tablename1);
        onCreate(db);
        db.execSQL("DROP TABLE IF EXISTS " + tablename2);
        onCreate(db);
    }
}
