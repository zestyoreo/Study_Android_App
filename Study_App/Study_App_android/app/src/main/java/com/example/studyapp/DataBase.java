package com.example.studyapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.Locale;

public class DataBase extends SQLiteOpenHelper {
    static final private String DBNAME = "events";
    static final private String DBTABLE = "mytable";
    static final private int VER = 1;
    private  static DataBase sInstance;

    Context ctx;
    SQLiteDatabase myDB;

    public static synchronized DataBase getInstance(Context context) {
        // Use the application context, which will ensure that you
        // don't accidentally leak an Activity's context.
        // See this article for more information: http://bit.ly/6LRzfx
        if (sInstance == null) {
            sInstance = new DataBase(context.getApplicationContext());
        }
        return sInstance;
    }

    public DataBase(Context ct){
        super(ct, DBNAME,null, VER);
        ctx = ct;
    }

    public void Delete(int __id){
        myDB = getWritableDatabase();
        myDB.delete(DBTABLE,"_id=?",new String[]{String.valueOf(__id)} );
    }
    @Override
    public void onCreate(SQLiteDatabase Data){
        Data.execSQL("create table "+DBTABLE+"(_id integer primary key autoincrement, title text, description text, starting date, ending date, type integer);");
        Log.i("Database"," Table created!");
    }

    @Override
    public void onUpgrade(SQLiteDatabase Data, int i, int i1){
        Data.execSQL("drop table if exists "+DBTABLE+";");
            onCreate(Data);
    }
    public int insertData(String n, String d, Date st, Date en, int ty){
        myDB = getWritableDatabase();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.US);
        ContentValues dat = new ContentValues();
        dat.put("title",n);
        dat.put("description",d);
        dat.put("starting", dateFormat.format(st));
        dat.put("ending", dateFormat.format(en));
        dat.put("type",ty);
        int rowId = (int) myDB.insert(DBTABLE,null,dat);
        Toast.makeText(ctx,"event Saved Successfully!",Toast.LENGTH_SHORT).show();
        return rowId;
    }
    // 0 for Study Plan, 1 for Assignment, 2 for Exam, 3 for Lecture
    public LinkedList<event> getAssignments() throws ParseException {
        myDB = getReadableDatabase();
        LinkedList<event> res= new LinkedList<event>();
        Cursor c = myDB.rawQuery("Select * from "+DBTABLE+" where type = 1",null);
        while(c.moveToNext()){
            int i = c.getInt(0);
            String t = c.getString(1);
            String d = c.getString(2);
            Date s;
            try {
                s = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.US).parse(c.getString(3));
            }catch(ParseException e){
                s = new Date(System.currentTimeMillis());
            }
            Date e;
            try {
                e = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.US).parse(c.getString(4));
            }catch(ParseException E){
                e = new Date(System.currentTimeMillis());
            }
            int ty = c.getInt(5);
            res.add(new event(i,t,d,s,e,ty));
        }

        c.close();
        return res;
    }
    public LinkedList<event> getExams() throws ParseException {
        myDB = getReadableDatabase();
        LinkedList<event> res= new LinkedList<event>();
        Cursor c = myDB.rawQuery("Select * from "+DBTABLE+" where type = 2",null);
        while(c.moveToNext()){
            int i = c.getInt(0);
            String t = c.getString(1);
            String d = c.getString(2);
            Date s;
            try {
                s = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.US).parse(c.getString(3));
            }catch(ParseException e){
                s = new Date(System.currentTimeMillis());
            }
            Date e;
            try {
                e = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.US).parse(c.getString(4));
            }catch(ParseException E){
                e = new Date(System.currentTimeMillis());
            }
            int ty = c.getInt(5);
            res.add(new event(i,t,d,s,e,ty));
        }

        c.close();
        return res;
    }
    public LinkedList<event> getPlans() throws ParseException {
        myDB = getReadableDatabase();
        LinkedList<event> res= new LinkedList<event>();
        Cursor c = myDB.rawQuery("Select * from "+DBTABLE+" where type = 0",null);
        while(c.moveToNext()){
            int i = c.getInt(0);
            String t = c.getString(1);
            String d = c.getString(2);
            Date s;
            try {
                s = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.US).parse(c.getString(3));
            }catch(ParseException e){
                s = new Date(System.currentTimeMillis());
            }
            Date e;
            try {
                e = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.US).parse(c.getString(4));
            }catch(ParseException E){
                e = new Date(System.currentTimeMillis());
            }
            int ty = c.getInt(5);
            res.add(new event(i,t,d,s,e,ty));
        }

        c.close();
        return res;
    }
    public LinkedList<event> getLectures() throws ParseException {
        myDB = getReadableDatabase();
        LinkedList<event> res= new LinkedList<event>();
        Cursor c = myDB.rawQuery("Select * from "+DBTABLE+" where type = 3",null);
        while(c.moveToNext()){
            int i = c.getInt(0);
            String t = c.getString(1);
            String d = c.getString(2);
            Date s;
            try {
                s = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.US).parse(c.getString(3));
            }catch(ParseException e){
                s = new Date(System.currentTimeMillis());
            }
            Date e;
            try {
                e = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.US).parse(c.getString(4));
            }catch(ParseException E){
                e = new Date(System.currentTimeMillis());
            }
            int ty = c.getInt(5);
            res.add(new event(i,t,d,s,e,ty));
        }

        c.close();
        return res;
    }
    public LinkedList<event> getAll() throws ParseException {
        myDB = getReadableDatabase();
        LinkedList<event> res= new LinkedList<event>();
        Cursor c = myDB.rawQuery("Select * from "+DBTABLE,null);
        while(c.moveToNext()){
            int i = c.getInt(0);
            String t = c.getString(1);
            String d = c.getString(2);
            Date s;
            try {
                s = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.US).parse(c.getString(3));
            }catch(ParseException e){
                s = new Date(System.currentTimeMillis());
            }
            Date e;
            try {
                e = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.US).parse(c.getString(4));
            }catch(ParseException E){
                e = new Date(System.currentTimeMillis());
            }
            int ty = c.getInt(5);
            res.add(new event(i,t,d,s,e,ty));
        }

        c.close();
        return res;
    }
}
