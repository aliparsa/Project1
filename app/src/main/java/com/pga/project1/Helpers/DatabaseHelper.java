package com.pga.project1.Helpers;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.pga.project1.DataModel.Personnel;
import com.pga.project1.DataModel.Taradod;
import com.pga.project1.DataModel.Work;

import java.util.ArrayList;

/**
 * Created by aliparsa on 9/20/2014.
 */
public class DatabaseHelper extends SQLiteOpenHelper {


    // All Static variables
    SQLiteDatabase database;
    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "db.db";

    // Contacts table name
    private static final String TABLE_PERSONNEL = "personnel";
    private static final String TABLE_TARADOD = "taradod";
    private static final String TABLE_WORK = "work";


    private static final String KEY_ID = "id";
    private static final String KEY_FIRSTNAME = "first_name";
    private static final String KEY_LASTNAME = "last_name";
    private static final String KEY_CODE = "personnel_code";
    private static final String KEY_IN_OUT = "in_out";
    private static final String KEY_DATE = "date";
    private static final String KEY_SENT = "sent";
    private static final String KEY_IMAGE = "image";
    private static final String KEY_NUMBER = "number";
    private static final String KEY_NAME = "name";
    private static final String KEY_TYPE = "type";
    private static final String KEY_PRICE = "price";


    SQLiteDatabase db;


    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);

    }

    // Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {


        String CREATE_PERSONNEL_TABLE =
                "CREATE TABLE " + TABLE_PERSONNEL + "("
                        + KEY_ID + " INTEGER PRIMARY KEY,"
                        + KEY_FIRSTNAME + " TEXT,"
                        + KEY_LASTNAME + " TEXT,"
                        + KEY_IMAGE + " TEXT,"
                        + KEY_NUMBER + " TEXT,"
                        + KEY_CODE + " TEXT"
                        + ")";
        db.execSQL(CREATE_PERSONNEL_TABLE);

        String CREATE_TARADOD_TABLE =
                "CREATE TABLE " + TABLE_TARADOD + "("
                        + KEY_ID + " INTEGER PRIMARY KEY,"
                        + KEY_CODE + " TEXT,"
                        + KEY_IN_OUT + " TEXT,"
                        + KEY_SENT + " TEXT,"
                        + KEY_DATE + " TEXT"
                        + ")";
        db.execSQL(CREATE_TARADOD_TABLE);

        String CREATE_WORK_TABLE =
                "CREATE TABLE " + TABLE_WORK + "("
                        + KEY_ID + " INTEGER PRIMARY KEY,"
                        + KEY_NAME + " TEXT,"
                        + KEY_TYPE + " TEXT,"
                        + KEY_PRICE + " TEXT"
                        + ")";
        db.execSQL(CREATE_WORK_TABLE);


        database = db;
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i2) {

    }


    public void insertTaradod(Personnel personnel, String in_out, String DateTime) {
        ContentValues values = new ContentValues();
        values.put(KEY_CODE, personnel.getPersonnel_code());
        values.put(KEY_IN_OUT, in_out);
        values.put(KEY_SENT, "0");
        values.put(KEY_DATE, DateTime);
        this.getWritableDatabase().insert(TABLE_TARADOD, null, values);
    }

    public void insertPersonnel(Personnel personnel) {
        ContentValues values = new ContentValues();
        values.put(KEY_FIRSTNAME, personnel.getFirst_name());
        values.put(KEY_LASTNAME, personnel.getLast_name());
        values.put(KEY_CODE, personnel.getPersonnel_code());
        this.getWritableDatabase().insert(TABLE_PERSONNEL, null, values);
    }


    public ArrayList<Personnel> getAllPersonnels() {
        SQLiteDatabase db = getReadableDatabase();
        final Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_PERSONNEL, null);
        ArrayList<Personnel> personnels = new ArrayList<Personnel>();


        if (cursor != null) {
            if (cursor.moveToFirst()) {

                do {
                    Personnel personnel = new Personnel();
                    personnel.setFirst_name(cursor.getString(cursor.getColumnIndex(KEY_FIRSTNAME)));
                    personnel.setLast_name(cursor.getString(cursor.getColumnIndex(KEY_LASTNAME)));
                    personnel.setPersonnel_code(cursor.getString(cursor.getColumnIndex(KEY_CODE)));
                    personnel.setId(Integer.parseInt(cursor.getString(cursor.getColumnIndex(KEY_ID))));
                    personnels.add(personnel);

                } while (cursor.moveToNext());

            }
        }
        return personnels;
    }

    public void emptyPersonnelTable() {
        getReadableDatabase().execSQL("Delete from " + TABLE_PERSONNEL);
    }

    public ArrayList<Taradod> getAllUnsentTaradod(){

        ArrayList<Taradod> taradods = new ArrayList<Taradod>();

        SQLiteDatabase db = getReadableDatabase();
        final Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_TARADOD + " WHERE " + KEY_IN_OUT + " = 0;", null);

        if (cursor != null) {
            if (cursor.moveToFirst()) {

                do {

                   Taradod taradod = new Taradod(
                           cursor.getInt(cursor.getColumnIndex(KEY_ID)),
                           cursor.getString(cursor.getColumnIndex(KEY_CODE)),
                           cursor.getString(cursor.getColumnIndex(KEY_IN_OUT)),
                           cursor.getInt(cursor.getColumnIndex(KEY_SENT)),
                           cursor.getString(cursor.getColumnIndex(KEY_DATE))
                           );

                    taradods.add(taradod);

                } while (cursor.moveToNext());

            }
        }


        return taradods;

    }


    public void emptyWorkTable() {
        getReadableDatabase().execSQL("Delete from " + TABLE_WORK);
    }

    public void insertWork(Work work) {
        ContentValues values = new ContentValues();
        values.put(KEY_ID, work.getId());
        values.put(KEY_NAME, work.getName());
        values.put(KEY_TYPE, work.getType());
        values.put(KEY_PRICE, work.getPrice());
        this.getWritableDatabase().insert(TABLE_WORK, null, values);
    }

    public ArrayList<Work> getAllWorks() {
        SQLiteDatabase db = getReadableDatabase();
        final Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_WORK, null);
        ArrayList<Work> works = new ArrayList<Work>();


        if (cursor != null) {
            if (cursor.moveToFirst()) {
                do {
                    Work work = new Work();
                    work.setId(cursor.getInt(cursor.getColumnIndex(KEY_ID)));
                    work.setName(cursor.getString(cursor.getColumnIndex(KEY_NAME)));
                    work.setType(cursor.getString(cursor.getColumnIndex(KEY_TYPE)));
                    work.setPrice(cursor.getInt(cursor.getColumnIndex(KEY_PRICE)));
                    works.add(work);
                } while (cursor.moveToNext());
            }
        }
        return works;
    }
}