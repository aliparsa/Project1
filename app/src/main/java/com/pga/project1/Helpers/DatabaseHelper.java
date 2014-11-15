package com.pga.project1.Helpers;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.ArrayAdapter;

import com.pga.project1.DataModel.Anbar;
import com.pga.project1.DataModel.Chart;
import com.pga.project1.DataModel.Faliat;
import com.pga.project1.DataModel.ItemsProvider;
import com.pga.project1.DataModel.Personnel;
import com.pga.project1.DataModel.Taradod;
import com.pga.project1.DataModel.Work;

import org.json.JSONArray;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by aliparsa on 9/20/2014.
 */
public class DatabaseHelper extends SQLiteOpenHelper {

    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "db.db";

    // Contacts table names
    private static final String TABLE_PERSONNEL = "personnel";
    private static final String TABLE_TARADOD = "taradod";
    private static final String TABLE_WORK = "work";
    private static final String TABLE_FALIAT = "faliat";
    private static final String TABLE_PROJECTS = "projects";
    private static final String TABLE_ANBAR = "anbar";
    private static final String TABLE_ITEMS_PROVIDER = "items_provider";


    // Contacts Key names
    private static final String KEY_ID = "id";
    private static final String KEY_FIRSTNAME = "first_name";
    private static final String KEY_LASTNAME = "last_name";
    private static final String KEY_CODE = "personnel_code";
    private static final String KEY_IN_OUT = "in_out";
    private static final String KEY_DATE = "date";
    private static final String KEY_SENT = "sent";
    private static final String KEY_IMAGE = "image";
    private static final String KEY_NAME = "name";
    private static final String KEY_TYPE = "type";
    private static final String KEY_PRICE = "price";
    private static final String KEY_WORK_CODE = "work_id";
    private static final String KEY_AMOUNT = "amount";
    private static final String KEY_PHONE = "phone";
    private static final String KEY_TYPE_ID = "type_id";
    private static final String KEY_PERSONAL_ID = "personnel_id";
    private static final String KEY_PERSONAL = "personnel";
    private static final String KEY_START_DATE = "start_date";
    private static final String KEY_END_DATE = "end_date";
    private static final String KEY_CREATED_AT = "created_at";
    private static final String KEY_UPDATED_AT = "updated_at";
    private static final String KEY_PROJECT_ID = "project_id";
    private static final String KEY_HAS_ERROR = "has_error";
    private static final String KEY_IS_OWNER = "is_owner";
    private static final String KEY_OWNER = "owner";


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
                        + KEY_PHONE + " TEXT,"
                        + KEY_CODE + " TEXT"
                        + ")";
        db.execSQL(CREATE_PERSONNEL_TABLE);

        String CREATE_TARADOD_TABLE =
                "CREATE TABLE " + TABLE_TARADOD + "("
                        + KEY_ID + " INTEGER PRIMARY KEY,"
                        + KEY_CODE + " TEXT,"
                        + KEY_IN_OUT + " TEXT,"
                        + KEY_SENT + " TEXT,"
                        + KEY_PROJECT_ID + " TEXT,"
                        + KEY_HAS_ERROR + " TEXT,"
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


        String CREATE_FALIAT_TABLE =
                "CREATE TABLE " + TABLE_FALIAT + "("
                        + KEY_ID + " INTEGER PRIMARY KEY,"
                        + KEY_CODE + " TEXT,"
                        + KEY_WORK_CODE + " TEXT,"
                        + KEY_AMOUNT + " TEXT,"
                        + KEY_SENT + " TEXT,"
                        + KEY_PROJECT_ID + " TEXT,"
                        + KEY_DATE + " TEXT"
                        + ")";
        db.execSQL(CREATE_FALIAT_TABLE);

        String CREATE_PROJECTS_TABLE =
                "CREATE TABLE " + TABLE_PROJECTS + "("
                        + KEY_ID + " INTEGER PRIMARY KEY,"
                        + KEY_NAME + " TEXT,"
                        + KEY_TYPE_ID + " TEXT,"
                        + KEY_TYPE + " TEXT,"
                        + KEY_PERSONAL_ID + " TEXT,"
                        + KEY_PERSONAL + " TEXT,"
                        + KEY_START_DATE + " TEXT,"
                        + KEY_END_DATE + " TEXT,"
                        + KEY_PRICE + " TEXT,"
                        + KEY_CREATED_AT + " TEXT,"
                        + KEY_UPDATED_AT + " TEXT"
                        + ")";
        db.execSQL(CREATE_PROJECTS_TABLE);

        String CREATE_ANBAR_TABLE =
                "CREATE TABLE " + TABLE_ANBAR + "("
                        + KEY_ID + " INTEGER PRIMARY KEY,"
                        + KEY_NAME + " TEXT,"
                        + KEY_IS_OWNER + " TEXT"
                        + ")";
        db.execSQL(CREATE_ANBAR_TABLE);

        String CREATE_ITEMS_PROVIDER_TABLE =
                "CREATE TABLE " + TABLE_ITEMS_PROVIDER + "("
                        + KEY_ID + " INTEGER PRIMARY KEY,"
                        + KEY_NAME + " TEXT,"
                        + KEY_OWNER + " TEXT"
                        + ")";
        db.execSQL(CREATE_ITEMS_PROVIDER_TABLE);


    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i2) {

    }

    public void insertAnbar(Anbar anbar) {
        ContentValues values = new ContentValues();
        values.put(KEY_ID, anbar.getId());
        values.put(KEY_NAME, anbar.getName());
        values.put(KEY_IS_OWNER, anbar.getIs_owner());
        this.getWritableDatabase().insert(TABLE_ANBAR, null, values);
    }

    public void insertTaradod(Taradod taradod) {
        ContentValues values = new ContentValues();
        values.put(KEY_CODE, taradod.getPersonnelID());
        values.put(KEY_IN_OUT, taradod.getInOut());
        values.put(KEY_SENT, "0");
        values.put(KEY_HAS_ERROR, "0");
        values.put(KEY_DATE, taradod.getDate());
        values.put(KEY_PROJECT_ID, taradod.getProjectID());
        this.getWritableDatabase().insert(TABLE_TARADOD, null, values);
    }

    public void insertProject(Chart chart) {
        ContentValues values = new ContentValues();
        values.put(KEY_ID, chart.getId());
        values.put(KEY_NAME, chart.getName());
        values.put(KEY_TYPE_ID, chart.getType_id());
        values.put(KEY_TYPE, chart.getType());
        values.put(KEY_PERSONAL_ID, chart.getPersonnel_id());
        values.put(KEY_PERSONAL, "");
        values.put(KEY_START_DATE, chart.getStart_date());
        values.put(KEY_END_DATE, chart.getEnd_date());
        values.put(KEY_PRICE, chart.getPrice());

        this.getWritableDatabase().insert(TABLE_PROJECTS, null, values);
    }

    public void insertPersonnel(Personnel personnel) {
        ContentValues values = new ContentValues();
        values.put(KEY_ID, personnel.getId());
        values.put(KEY_FIRSTNAME, personnel.getFirst_name());
        values.put(KEY_LASTNAME, personnel.getLast_name());
        values.put(KEY_CODE, personnel.getPersonnel_code());
        values.put(KEY_IMAGE, personnel.getPersonnel_image());
        values.put(KEY_PHONE, personnel.getPhone_number());
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
                    personnel.setId(cursor.getInt(cursor.getColumnIndex(KEY_ID)));
                    personnel.setFirst_name(cursor.getString(cursor.getColumnIndex(KEY_FIRSTNAME)));
                    personnel.setLast_name(cursor.getString(cursor.getColumnIndex(KEY_LASTNAME)));
                    personnel.setPersonnel_code(cursor.getString(cursor.getColumnIndex(KEY_CODE)));
                    personnel.setPersonnel_image((cursor.getString(cursor.getColumnIndex(KEY_IMAGE))));
                    personnel.setPhone_number((cursor.getString(cursor.getColumnIndex(KEY_PHONE))));

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
        final Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_TARADOD + " WHERE " + KEY_SENT + " = \"0\";", null);

        if (cursor != null) {
            if (cursor.moveToFirst()) {

                do {

                   Taradod taradod = new Taradod(
                           cursor.getInt(cursor.getColumnIndex(KEY_ID)),
                           cursor.getString(cursor.getColumnIndex(KEY_CODE)),
                           cursor.getString(cursor.getColumnIndex(KEY_IN_OUT)),
                           cursor.getInt(cursor.getColumnIndex(KEY_SENT)),
                           cursor.getString(cursor.getColumnIndex(KEY_DATE)),
                           cursor.getString(cursor.getColumnIndex(KEY_PROJECT_ID)),
                           cursor.getString(cursor.getColumnIndex(KEY_HAS_ERROR))
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

    public void insertFaliat(Faliat faliat) {

        ContentValues values = new ContentValues();

        values.put(KEY_CODE, faliat.getPersonnelID());
        values.put(KEY_WORK_CODE, faliat.getWorkId());
        values.put(KEY_AMOUNT, faliat.getAmount());
        values.put(KEY_DATE, faliat.getDate());
        values.put(KEY_PROJECT_ID, faliat.getProjectID());
        values.put(KEY_SENT, 0);
        this.getWritableDatabase().insert(TABLE_FALIAT, null, values);

    }

    public ArrayList<Taradod> getAllTaradodsWithPersonnel(String projectID) {

        ArrayList<Taradod> taradods = new ArrayList<Taradod>();

        SQLiteDatabase db = getReadableDatabase();

        String query = "SELECT * FROM " + TABLE_TARADOD + " t, " + TABLE_PERSONNEL + " p" +
                " WHERE t." + KEY_PROJECT_ID + "=\"" + projectID + "\" AND t." + KEY_CODE + " = p." + KEY_ID + " order by t.id desc";

        Log.i("ali", query);

        final Cursor cursor = db.rawQuery(query, null);

        if (cursor != null) {
            if (cursor.moveToFirst()) {

                do {

                    Taradod taradod = new Taradod(
                            cursor.getInt(cursor.getColumnIndex(TABLE_TARADOD + "." + KEY_ID)),
                            cursor.getString(cursor.getColumnIndex(TABLE_TARADOD + "." + KEY_CODE)),
                            cursor.getString(cursor.getColumnIndex(KEY_IN_OUT)),
                            cursor.getInt(cursor.getColumnIndex(KEY_SENT)),
                            cursor.getString(cursor.getColumnIndex(KEY_DATE)),
                            cursor.getString(cursor.getColumnIndex(KEY_PROJECT_ID)),
                            cursor.getString(cursor.getColumnIndex(KEY_HAS_ERROR))
                    );

                    Personnel personnel = new Personnel();
                    personnel.setId(cursor.getInt(cursor.getColumnIndex(TABLE_PERSONNEL +"."+ KEY_ID)));
                    personnel.setFirst_name(cursor.getString(cursor.getColumnIndex(KEY_FIRSTNAME)));
                    personnel.setLast_name(cursor.getString(cursor.getColumnIndex(KEY_LASTNAME)));
                    personnel.setPersonnel_code(cursor.getString(cursor.getColumnIndex(TABLE_PERSONNEL +"."+KEY_CODE)));
                    personnel.setPersonnel_image((cursor.getString(cursor.getColumnIndex(KEY_IMAGE))));
                    personnel.setPhone_number((cursor.getString(cursor.getColumnIndex(KEY_PHONE))));

                    taradod.setPersonnel(personnel);

                    taradods.add(taradod);

                } while (cursor.moveToNext());
            }
        }

        return taradods;
    }

    public ArrayList<Faliat> getAllFaliatsWithPersonnelAndWork(String projectID) {

        ArrayList<Faliat> faliats = new ArrayList<Faliat>();

        SQLiteDatabase db = getReadableDatabase();

        String query = "SELECT * FROM " + TABLE_FALIAT + " f , " + TABLE_PERSONNEL + " p, " + TABLE_WORK + " w" +
                " WHERE f." + KEY_PROJECT_ID + "=\"" + projectID + "\" AND  f." + KEY_CODE + " = p." + KEY_ID +
                " AND f." + KEY_WORK_CODE + " = w." + KEY_ID + " order by f.id desc";

        final Cursor cursor = db.rawQuery(query, null);

        if (cursor != null) {
            if (cursor.moveToFirst()) {

                do {

                    Faliat faliat = new Faliat(
                            cursor.getInt(cursor.getColumnIndex(TABLE_FALIAT +"."+ KEY_ID)),
                            cursor.getString(cursor.getColumnIndex(TABLE_FALIAT +"."+ KEY_CODE)),
                            cursor.getString(cursor.getColumnIndex(TABLE_FALIAT +"."+ KEY_WORK_CODE)),
                            cursor.getString(cursor.getColumnIndex(KEY_AMOUNT)),
                            cursor.getString(cursor.getColumnIndex(KEY_DATE)),
                            cursor.getInt(cursor.getColumnIndex(KEY_SENT)),
                            cursor.getString(cursor.getColumnIndex(KEY_PROJECT_ID))
                    );

                    Personnel personnel = new Personnel();
                    personnel.setId(cursor.getInt(cursor.getColumnIndex(TABLE_PERSONNEL +"."+ KEY_ID)));
                    personnel.setFirst_name(cursor.getString(cursor.getColumnIndex(KEY_FIRSTNAME)));
                    personnel.setLast_name(cursor.getString(cursor.getColumnIndex(KEY_LASTNAME)));
                    personnel.setPersonnel_code(cursor.getString(cursor.getColumnIndex(TABLE_PERSONNEL +"."+KEY_CODE)));
                    personnel.setPersonnel_image((cursor.getString(cursor.getColumnIndex(KEY_IMAGE))));
                    personnel.setPhone_number((cursor.getString(cursor.getColumnIndex(KEY_PHONE))));

                    faliat.setPersonnel(personnel);

                    Work work = new Work();
                    work.setId(cursor.getInt(cursor.getColumnIndex(TABLE_WORK +"."+KEY_ID)));
                    work.setName(cursor.getString(cursor.getColumnIndex(KEY_NAME)));
                    work.setType(cursor.getString(cursor.getColumnIndex(KEY_TYPE)));
                    work.setPrice(cursor.getInt(cursor.getColumnIndex(KEY_PRICE)));

                    faliat.setWork(work);

                    faliats.add(faliat);

                } while (cursor.moveToNext());
            }
        }

        return faliats;

    }

    public String getPersonnelInOrOut(Personnel personnel) {
        SQLiteDatabase db = getReadableDatabase();

        String query = "SELECT * FROM " + TABLE_TARADOD + " WHERE " + KEY_CODE + "=\"" + personnel.getId() + "\"" + " order by " + KEY_ID + " desc limit 1";

        final Cursor cursor = db.rawQuery(query, null);

        String in_out = null;
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                in_out = cursor.getString(cursor.getColumnIndex(KEY_IN_OUT));
            }
        }

        return in_out;
    }

    public ArrayList<Chart> getProjects() {
        SQLiteDatabase db = getReadableDatabase();
        final Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_PROJECTS, null);
        ArrayList<Chart> charts = new ArrayList<Chart>();


        if (cursor != null) {
            if (cursor.moveToFirst()) {
                do {
                    Chart chart = new Chart();
                    chart.setId(cursor.getInt(cursor.getColumnIndex(KEY_ID)));
                    chart.setName(cursor.getString(cursor.getColumnIndex(KEY_NAME)));
                    chart.setType_id(Integer.parseInt(cursor.getString(cursor.getColumnIndex(KEY_TYPE_ID))));
                    chart.setStart_date((cursor.getString(cursor.getColumnIndex(KEY_START_DATE))));
                    chart.setEnd_date((cursor.getString(cursor.getColumnIndex(KEY_END_DATE))));
                    chart.setPrice(cursor.getString(cursor.getColumnIndex(KEY_PRICE)));
                    charts.add(chart);
                } while (cursor.moveToNext());
            }
        }
        return charts;
    }

    public void emptyProjectsTable() {
        getReadableDatabase().execSQL("Delete from " + TABLE_PROJECTS);
    }

    public ArrayList<Faliat> getAllUnsentFaliat() {

        ArrayList<Faliat> faliats = new ArrayList<Faliat>();

        SQLiteDatabase db = getReadableDatabase();
        final Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_FALIAT + " WHERE " + KEY_SENT + " = \"0\" ;", null);

        if (cursor != null) {
            if (cursor.moveToFirst()) {

                do {

                    Faliat faliat = new Faliat(
                            cursor.getInt(cursor.getColumnIndex(KEY_ID)),
                            cursor.getString(cursor.getColumnIndex(KEY_CODE)),
                            cursor.getString(cursor.getColumnIndex(KEY_WORK_CODE)),
                            cursor.getString(cursor.getColumnIndex(KEY_AMOUNT)),
                            cursor.getString(cursor.getColumnIndex(KEY_DATE)),
                            cursor.getInt(cursor.getColumnIndex(KEY_SENT)),
                            cursor.getString(cursor.getColumnIndex(KEY_PROJECT_ID))


                    );

                    faliats.add(faliat);

                } while (cursor.moveToNext());
            }
        }

        return faliats;
    }

    public ArrayList<Faliat> getAllFaliat() {

        ArrayList<Faliat> faliats = new ArrayList<Faliat>();

        SQLiteDatabase db = getReadableDatabase();
        final Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_FALIAT, null);

        if (cursor != null) {
            if (cursor.moveToFirst()) {

                do {

                    Faliat faliat = new Faliat(
                            cursor.getInt(cursor.getColumnIndex(TABLE_FALIAT + "." + KEY_ID)),
                            cursor.getString(cursor.getColumnIndex(TABLE_FALIAT + "." + KEY_CODE)),
                            cursor.getString(cursor.getColumnIndex(TABLE_FALIAT + "." + KEY_WORK_CODE)),
                            cursor.getString(cursor.getColumnIndex(KEY_AMOUNT)),
                            cursor.getString(cursor.getColumnIndex(KEY_DATE)),
                            cursor.getInt(cursor.getColumnIndex(KEY_SENT)),
                            cursor.getString(cursor.getColumnIndex(KEY_PROJECT_ID))


                    );

                    faliats.add(faliat);

                } while (cursor.moveToNext());
            }
        }

        return faliats;
    }

    public void markAsSentFaliat(ArrayList<Faliat> faliats){

        String idIn = "(";

        if (faliats.size() < 1)
            return;

        idIn += faliats.get(0).getId();

        for (int i = 1; i < faliats.size(); i++) {

            idIn += ", " + faliats.get(i).getId();
        }

        idIn += ")";

        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("UPDATE " + TABLE_FALIAT + " SET sent =\"1\" Where " + KEY_ID + " In " + idIn + ";");

    }

    public void markAsSentTaradod(ArrayList<Taradod> taradods){

        String idIn = "(";

        if (taradods.size() < 1)
            return;

        idIn += taradods.get(0).getId();

        for (int i = 1; i < taradods.size(); i++) {

            idIn += ", " + taradods.get(i).getId();
        }

        idIn += ")";

        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("UPDATE " + TABLE_TARADOD + " SET sent=\"1\" Where " + KEY_ID + " In " + idIn + ";");

    }

    public void cleanOldData(int days) {
        SQLiteDatabase db = getWritableDatabase();
        Date d = new Date(System.currentTimeMillis() - (86400000) * days);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
        String date = sdf.format(d);
        final Cursor cursor1 = db.rawQuery("delete from " + TABLE_FALIAT + " Where " + KEY_DATE + " < '" + date + "' AND " + KEY_SENT + "=\"1\"", null);
        final Cursor cursor2 = db.rawQuery("delete from " + TABLE_TARADOD + " Where " + KEY_DATE + " < '" + date + "' AND " + KEY_SENT + "=\"1\"", null);

    }

    public void makeHasErrorTrue(JSONArray jsonArray) {
        try {
            String idIn = "(";

            if (jsonArray.length() < 0)
                return;

            idIn += jsonArray.get(0);

            for (int i = 1; i < jsonArray.length(); i++) {

                idIn += ", " + jsonArray.get(i);
            }

            idIn += ")";

            SQLiteDatabase db = getWritableDatabase();
            db.execSQL("UPDATE " + TABLE_TARADOD + " SET " + KEY_HAS_ERROR + "=\"1\" Where " + KEY_ID + " In " + idIn + ";");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void emptyAnbarTable() {
        getReadableDatabase().execSQL("Delete from " + TABLE_ANBAR);
    }

    public void insertItemsProvider(ItemsProvider itemsProvider) {
        ContentValues values = new ContentValues();
        values.put(KEY_ID, itemsProvider.getId());
        values.put(KEY_NAME, itemsProvider.getName());
        values.put(KEY_OWNER, itemsProvider.getOwner());
        this.getWritableDatabase().insert(TABLE_ITEMS_PROVIDER, null, values);
    }

    public void emptyItemsProviderTable() {
        getReadableDatabase().execSQL("Delete from " + TABLE_ITEMS_PROVIDER);
    }
}