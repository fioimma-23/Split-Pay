package com.example.propay;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class DatabaseManager {

    Context context;
    DatabaseHelper dbHelper;
    SQLiteDatabase db;

    DatabaseManager(Context c){
        context = c;
    }

    void open() throws SQLException {
        dbHelper = new DatabaseHelper(context);
        db = dbHelper.getWritableDatabase();
    }

    void close(){
        dbHelper.close();
    }

    void insert1(String name, String password, double in, double out){
        ContentValues contentValues = new ContentValues();
        contentValues.put(DatabaseHelper.t1column1,name);
        contentValues.put(DatabaseHelper.t1column2,password);
        contentValues.put(DatabaseHelper.t1column3,in);
        contentValues.put(DatabaseHelper.t1column4,out);
        db.insert(DatabaseHelper.tablename1,null,contentValues);
    }

    void insert2(String name, String toname, double cost, double inorot){
        ContentValues contentValues = new ContentValues();
        contentValues.put(DatabaseHelper.t2column1,name);
        contentValues.put(DatabaseHelper.t2column2,toname);
        contentValues.put(DatabaseHelper.t2column3,cost);
        contentValues.put(DatabaseHelper.t2column4,inorot);
        db.insert(DatabaseHelper.tablename2,null,contentValues);
    }

    void update1(String name, double in, double out){
        ContentValues contentValues = new ContentValues();
        contentValues.put(DatabaseHelper.t1column1,name);
        contentValues.put(DatabaseHelper.t1column3,in);
        contentValues.put(DatabaseHelper.t1column4,out);
        db.update(DatabaseHelper.tablename1,contentValues,DatabaseHelper.t1column1 + " = " +"\""+name+"\"", null);
    }

    void update2(String name,String toname, double cost, double inorout){
        ContentValues contentValues = new ContentValues();
        contentValues.put(DatabaseHelper.t2column1,name);
        contentValues.put(DatabaseHelper.t2column2,toname);
        contentValues.put(DatabaseHelper.t2column3,cost);
        contentValues.put(DatabaseHelper.t2column4,inorout);
        db.update(DatabaseHelper.tablename2,contentValues,DatabaseHelper.t2column1 + " = " +"\""+name+"\"", null);
    }

    Cursor fetch1(){
        String[] val = new String[] {DatabaseHelper.t1column1, DatabaseHelper.t1column2, DatabaseHelper.t1column3, DatabaseHelper.t1column4};
        Cursor c = db.rawQuery("select * from "+DatabaseHelper.tablename1, null);
//        Cursor cursor = db.query(DatabaseHelper.TableName,val,null,null,null,null,null);
//        if(cursor.getCount() != 0){
//            cursor.moveToNext();
//        }
        return c;
    }
    Cursor fetch2(){
        String[] val = new String[] {DatabaseHelper.t2column1, DatabaseHelper.t2column2, DatabaseHelper.t2column3, DatabaseHelper.t2column4};
        Cursor c = db.rawQuery("select * from "+DatabaseHelper.tablename2, null);
//        Cursor cursor = db.query(DatabaseHelper.TableName,val,null,null,null,null,null);
//        if(cursor.getCount() != 0){
//            cursor.moveToNext();
//        }
        return c;
    }

    Cursor fetch1(String name){
        String[] val = new String[] {DatabaseHelper.t1column1, DatabaseHelper.t1column2, DatabaseHelper.t1column3, DatabaseHelper.t1column4};
        Cursor c = db.rawQuery("select * from "+DatabaseHelper.tablename1+" where uname = "+"\""+name+"\"", null);
//        Cursor cursor = db.query(DatabaseHelper.TableName,val,null,null,null,null,null);
//        if(cursor.getCount() != 0){
//            cursor.moveToNext();
//        }
        return c;
    }
    Cursor fetch2(String name){
        String[] val = new String[] {DatabaseHelper.t2column1, DatabaseHelper.t2column2, DatabaseHelper.t2column3, DatabaseHelper.t2column4};
        Cursor c = db.rawQuery("select * from "+DatabaseHelper.tablename2+" where uname = "+"\""+name+"\"", null);
//        Cursor cursor = db.query(DatabaseHelper.TableName,val,null,null,null,null,null);
//        if(cursor.getCount() != 0){
//            cursor.moveToNext();
//        }
        return c;
    }
}
