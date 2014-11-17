package com.pga.project1.Helpers;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.pga.project1.DataModel.Anbar;
import com.pga.project1.DataModel.AnbarTransaction;
import com.pga.project1.DataModel.Chart;
import com.pga.project1.DataModel.Faliat;
import com.pga.project1.DataModel.TaminKonande;
import com.pga.project1.DataModel.Personnel;
import com.pga.project1.DataModel.Product;
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
    private static final String TABLE_TAMIN_KONANDE = "items_provider";
    private static final String TABLE_ANBAR_TRANSACTION = "anbar_tansaction";
    private static final String TABLE_PRODUCT = "product";


    // Contacts Key names

    //TABLE__PERSONNEL
    private static final String PERSONNEL_KEY_ID = "personnel_id";
    private static final String PERSONNEL_KEY_FIRSTNAME = "personnel_firstname";
    private static final String PERSONNEL_KEY_LASTNAME = "personnel_lastname";
    private static final String PERSONNEL_KEY_IMAGE = "personnel_image";
    private static final String PERSONNEL_KEY_PHONE = "personnel_phone";
    private static final String PERSONNEL_KEY_CODE = "personnel_code";

    //TABLE_TARADOD
    private static final String TARADOD_KEY_ID = "taradod_id";
    private static final String TARADOD_KEY_CODE = "taradod_code";
    private static final String TARADOD_KEY_IN_OUT = "taradod_inout";
    private static final String TARADOD_KEY_SENT = "taradod_sent";
    private static final String TARADOD_KEY_PROJECT_ID = "taradod_project_id";
    private static final String TARADOD_KEY_HAS_ERROR = "taradod_has_error";
    private static final String TARADOD_KEY_DATE = "taradod_date";

    //TABLE_WORK
    private static final String WORK_KEY_ID = "work_id";
    private static final String WORK_KEY_NAME = "work_name";
    private static final String WORK_KEY_TYPE = "work_type";
    private static final String WORK_KEY_PRICE = "work_price";

    //TABLE_FALIAT
    private static final String FALIAT_KEY_ID = "faliat_id";
    private static final String FALIAT_KEY_CODE = "faliat_code";
    private static final String FALIAT_KEY_WORK_CODE = "faliat_work_code";
    private static final String FALIAT_KEY_AMOUNT = "faliat_amount";
    private static final String FALIAT_KEY_SENT = "faliat_sent";
    private static final String FALIAT_KEY_PROJECT_ID = "faliat_project_id";
    private static final String FALIAT_KEY_DATE = "faliat_date";

    //TABLE_PROJECT
    private static final String PROJECT_KEY_ID = "project_id";
    private static final String PROJECT_KEY_NAME = "project_name";
    private static final String PROJECT_KEY_TYPE_ID = "project_type_id";
    private static final String PROJECT_KEY_TYPE = "project_type";
    private static final String PROJECT_KEY_PERSONAL_ID = "project_personnel_id";
    private static final String PROJECT_KEY_PERSONAL = "project_personnel";
    private static final String PROJECT_KEY_START_DATE = "project_start_date";
    private static final String PROJECT_KEY_END_DATE = "project_end_date";
    private static final String PROJECT_KEY_PRICE = "project_price";
    private static final String PROJECT_KEY_CREATED_AT = "project_created_at";
    private static final String PROJECT_KEY_UPDATED_AT = "project_updated_at";

    //TABLE_ANBAR
    private static final String ANBAR_KEY_ID = "anbar_id";
    private static final String ANBAR_KEY_NAME = "anbar_name";
    private static final String ANBAR_KEY_IS_OWNER = "anbar_is_owner";

    //TABLE_TAMIN_KONANDE
    private static final String TAMIN_KONANDE_KEY_ID = "tamin_konande_id";
    private static final String TAMIN_KONANDE_KEY_NAME = "tamin_konande_name";
    private static final String TAMIN_KONANDE_KEY_OWNER = "tamin_konande_owner";

    //TABLE_ANBAR_TRANSACTION
    private static final String ANBAR_TRANSACTION_KEY_ID = "anbar_transaction_id";
    private static final String ANBAR_TRANSACTION_KEY_TYPE = "anbar_transaction_type";
    private static final String ANBAR_TRANSACTION_KEY_PRODUCT_ID = "anbar_transaction_product_id";
    private static final String ANBAR_TRANSACTION_KEY_ANBAR_ID = "anbar_transaction_anbar_id";
    private static final String ANBAR_TRANSACTION_KEY_PROVIDER_ID = "anbar_transaction_provider_id";
    private static final String ANBAR_TRANSACTION_KEY_TO_ANBAR_ID = "anbar_transaction_to_anbar_id";
    private static final String ANBAR_TRANSACTION_KEY_FROM_ANBAR_ID = "anbar_transaction_from_anbar_id";
    private static final String ANBAR_TRANSACTION_KEY_AMOUNT = "anbar_transaction_amount";
    private static final String ANBAR_TRANSACTION_KEY_DATE = "anbar_transaction_date";
    private static final String ANBAR_TRANSACTION_KEY_DESCRIPTION = "anbar_transaction_description";
    private static final String ANBAR_TRANSACTION_KEY_SENT = "anbar_transaction_sent";
    private static final String ANBAR_TRANSACTION_KEY_HAS_ERROR = "anbar_transaction_has_error";


    //TABLE_PRODUCT
    private static final String PRODUCT_KEY_ID = "product_id";
    private static final String PRODUCT_KEY_NAME = "product_name";


  /*  private static final String KEY_ID = "id";
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
    private static final String KEY_PRODUCT_ID = "product_id";
    private static final String KEY_ANBAR_ID = "anbar_id";
    private static final String KEY_PROVIDER_ID = "provider_id";
    private static final String KEY_TO_ANBAR_ID = "to_anbar_id";
    private static final String KEY_FROM_ANBAR_ID = "from_anbar_id";
    private static final String KEY_DESCRIPTION = "description";*/


    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);

    }

    // Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {


        String CREATE_PERSONNEL_TABLE =
                "CREATE TABLE " + TABLE_PERSONNEL + "("
                        + PERSONNEL_KEY_ID + " INTEGER PRIMARY KEY,"
                        + PERSONNEL_KEY_FIRSTNAME + " TEXT,"
                        + PERSONNEL_KEY_LASTNAME + " TEXT,"
                        + PERSONNEL_KEY_IMAGE + " TEXT,"
                        + PERSONNEL_KEY_PHONE + " TEXT,"
                        + PERSONNEL_KEY_CODE + " TEXT"
                        + ")";
        db.execSQL(CREATE_PERSONNEL_TABLE);

        String CREATE_TARADOD_TABLE =
                "CREATE TABLE " + TABLE_TARADOD + "("
                        + TARADOD_KEY_ID + " INTEGER PRIMARY KEY,"
                        + TARADOD_KEY_CODE + " TEXT,"
                        + TARADOD_KEY_IN_OUT + " TEXT,"
                        + TARADOD_KEY_SENT + " TEXT,"
                        + TARADOD_KEY_PROJECT_ID + " TEXT,"
                        + TARADOD_KEY_HAS_ERROR + " TEXT,"
                        + TARADOD_KEY_DATE + " TEXT"
                        + ")";
        db.execSQL(CREATE_TARADOD_TABLE);

        String CREATE_WORK_TABLE =
                "CREATE TABLE " + TABLE_WORK + "("
                        + WORK_KEY_ID + " INTEGER PRIMARY KEY,"
                        + WORK_KEY_NAME + " TEXT,"
                        + WORK_KEY_TYPE + " TEXT,"
                        + WORK_KEY_PRICE + " TEXT"
                        + ")";
        db.execSQL(CREATE_WORK_TABLE);


        String CREATE_FALIAT_TABLE =
                "CREATE TABLE " + TABLE_FALIAT + "("
                        + FALIAT_KEY_ID + " INTEGER PRIMARY KEY,"
                        + FALIAT_KEY_CODE + " TEXT,"
                        + FALIAT_KEY_WORK_CODE + " TEXT,"
                        + FALIAT_KEY_AMOUNT + " TEXT,"
                        + FALIAT_KEY_SENT + " TEXT,"
                        + FALIAT_KEY_PROJECT_ID + " TEXT,"
                        + FALIAT_KEY_DATE + " TEXT"
                        + ")";
        db.execSQL(CREATE_FALIAT_TABLE);

        String CREATE_PROJECTS_TABLE =
                "CREATE TABLE " + TABLE_PROJECTS + "("
                        + PROJECT_KEY_ID + " INTEGER PRIMARY KEY,"
                        + PROJECT_KEY_NAME + " TEXT,"
                        + PROJECT_KEY_TYPE_ID + " TEXT,"
                        + PROJECT_KEY_TYPE + " TEXT,"
                        + PROJECT_KEY_PERSONAL_ID + " TEXT,"
                        + PROJECT_KEY_PERSONAL + " TEXT,"
                        + PROJECT_KEY_START_DATE + " TEXT,"
                        + PROJECT_KEY_END_DATE + " TEXT,"
                        + PROJECT_KEY_PRICE + " TEXT,"
                        + PROJECT_KEY_CREATED_AT + " TEXT,"
                        + PROJECT_KEY_UPDATED_AT + " TEXT"
                        + ")";
        db.execSQL(CREATE_PROJECTS_TABLE);

        String CREATE_ANBAR_TABLE =
                "CREATE TABLE " + TABLE_ANBAR + "("
                        + ANBAR_KEY_ID + " INTEGER PRIMARY KEY,"
                        + ANBAR_KEY_NAME + " TEXT,"
                        + ANBAR_KEY_IS_OWNER + " TEXT"
                        + ")";
        db.execSQL(CREATE_ANBAR_TABLE);

        String CREATE_TAMIN_KONANDE_TABLE =
                "CREATE TABLE " + TABLE_TAMIN_KONANDE + "("
                        + TAMIN_KONANDE_KEY_ID + " INTEGER PRIMARY KEY,"
                        + TAMIN_KONANDE_KEY_NAME + " TEXT,"
                        + TAMIN_KONANDE_KEY_OWNER + " TEXT"
                        + ")";
        db.execSQL(CREATE_TAMIN_KONANDE_TABLE);

        String CREATE_ANBAR_TRANSACTION_TABLE =
                "CREATE TABLE " + TABLE_ANBAR_TRANSACTION + "("
                        + ANBAR_TRANSACTION_KEY_ID + " INTEGER PRIMARY KEY,"
                        + ANBAR_TRANSACTION_KEY_TYPE + " TEXT,"
                        + ANBAR_TRANSACTION_KEY_PRODUCT_ID + " TEXT,"
                        + ANBAR_TRANSACTION_KEY_ANBAR_ID + " TEXT,"
                        + ANBAR_TRANSACTION_KEY_PROVIDER_ID + " TEXT,"
                        + ANBAR_TRANSACTION_KEY_TO_ANBAR_ID + " TEXT,"
                        + ANBAR_TRANSACTION_KEY_FROM_ANBAR_ID + " TEXT,"
                        + ANBAR_TRANSACTION_KEY_AMOUNT + " TEXT,"
                        + ANBAR_TRANSACTION_KEY_DATE + " TEXT,"
                        + ANBAR_TRANSACTION_KEY_SENT + " INTEGER,"
                        + ANBAR_TRANSACTION_KEY_HAS_ERROR + " INTEGER,"
                        + ANBAR_TRANSACTION_KEY_DESCRIPTION + " TEXT"
                        + ")";
        db.execSQL(CREATE_ANBAR_TRANSACTION_TABLE);

        String CREATE_PRODUCT_TABLE =
                "CREATE TABLE " + TABLE_PRODUCT + "("
                        + PRODUCT_KEY_ID + " INTEGER PRIMARY KEY,"
                        + PRODUCT_KEY_NAME + " TEXT"
                        + ")";
        db.execSQL(CREATE_PRODUCT_TABLE);


    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i2) {

    }

    public void insertProduct(Product product) {
        ContentValues values = new ContentValues();
        values.put(PRODUCT_KEY_ID, product.getId());
        values.put(PRODUCT_KEY_NAME, product.getName());
        this.getWritableDatabase().insert(TABLE_PRODUCT, null, values);
    }

    public void insertAnbar(Anbar anbar) {
        ContentValues values = new ContentValues();
        values.put(ANBAR_KEY_ID, anbar.getId());
        values.put(ANBAR_KEY_NAME, anbar.getName());
        values.put(ANBAR_KEY_IS_OWNER, anbar.getIs_owner());
        this.getWritableDatabase().insert(TABLE_ANBAR, null, values);
    }

    public void insertTaradod(Taradod taradod) {
        ContentValues values = new ContentValues();
        values.put(TARADOD_KEY_CODE, taradod.getPersonnelID());
        values.put(TARADOD_KEY_IN_OUT, taradod.getInOut());
        values.put(TARADOD_KEY_SENT, "0");
        values.put(TARADOD_KEY_HAS_ERROR, "0");
        values.put(TARADOD_KEY_DATE, taradod.getDate());
        values.put(TARADOD_KEY_PROJECT_ID, taradod.getProjectID());
        this.getWritableDatabase().insert(TABLE_TARADOD, null, values);
    }

    public void insertProject(Chart chart) {
        ContentValues values = new ContentValues();
        values.put(PROJECT_KEY_ID, chart.getId());
        values.put(PROJECT_KEY_NAME, chart.getName());
        values.put(PROJECT_KEY_TYPE_ID, chart.getType_id());
        values.put(PROJECT_KEY_TYPE, chart.getType());
        values.put(PROJECT_KEY_PERSONAL_ID, chart.getPersonnel_id());
        values.put(PROJECT_KEY_PERSONAL, "");
        values.put(PROJECT_KEY_START_DATE, chart.getStart_date());
        values.put(PROJECT_KEY_END_DATE, chart.getEnd_date());
        values.put(PROJECT_KEY_PRICE, chart.getPrice());

        this.getWritableDatabase().insert(TABLE_PROJECTS, null, values);
    }

    public void insertPersonnel(Personnel personnel) {
        ContentValues values = new ContentValues();
        values.put(PERSONNEL_KEY_ID, personnel.getId());
        values.put(PERSONNEL_KEY_FIRSTNAME, personnel.getFirst_name());
        values.put(PERSONNEL_KEY_LASTNAME, personnel.getLast_name());
        values.put(PERSONNEL_KEY_CODE, personnel.getPersonnel_code());
        values.put(PERSONNEL_KEY_IMAGE, personnel.getPersonnel_image());
        values.put(PERSONNEL_KEY_PHONE, personnel.getPhone_number());
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
                    personnel.setId(cursor.getInt(cursor.getColumnIndex(PERSONNEL_KEY_ID)));
                    personnel.setFirst_name(cursor.getString(cursor.getColumnIndex(PERSONNEL_KEY_FIRSTNAME)));
                    personnel.setLast_name(cursor.getString(cursor.getColumnIndex(PERSONNEL_KEY_LASTNAME)));
                    personnel.setPersonnel_code(cursor.getString(cursor.getColumnIndex(PERSONNEL_KEY_CODE)));
                    personnel.setPersonnel_image((cursor.getString(cursor.getColumnIndex(PERSONNEL_KEY_IMAGE))));
                    personnel.setPhone_number((cursor.getString(cursor.getColumnIndex(PERSONNEL_KEY_PHONE))));

                    personnels.add(personnel);

                } while (cursor.moveToNext());

            }
        }
        return personnels;
    }

    public void emptyPersonnelTable() {
        getReadableDatabase().execSQL("Delete from " + TABLE_PERSONNEL);
    }

    public ArrayList<Taradod> getAllUnsentTaradod() {

        ArrayList<Taradod> taradods = new ArrayList<Taradod>();

        SQLiteDatabase db = getReadableDatabase();
        final Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_TARADOD + " WHERE " + TARADOD_KEY_SENT + " = \"0\";", null);

        if (cursor != null) {
            if (cursor.moveToFirst()) {

                do {

                    Taradod taradod = new Taradod(
                            cursor.getInt(cursor.getColumnIndex(TARADOD_KEY_ID)),
                            cursor.getString(cursor.getColumnIndex(TARADOD_KEY_CODE)),
                            cursor.getString(cursor.getColumnIndex(TARADOD_KEY_IN_OUT)),
                            cursor.getInt(cursor.getColumnIndex(TARADOD_KEY_SENT)),
                            cursor.getString(cursor.getColumnIndex(TARADOD_KEY_DATE)),
                            cursor.getString(cursor.getColumnIndex(TARADOD_KEY_PROJECT_ID)),
                            cursor.getString(cursor.getColumnIndex(TARADOD_KEY_HAS_ERROR))
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
        values.put(WORK_KEY_ID, work.getId());
        values.put(WORK_KEY_NAME, work.getName());
        values.put(WORK_KEY_TYPE, work.getType());
        values.put(WORK_KEY_PRICE, work.getPrice());
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
                    work.setId(cursor.getInt(cursor.getColumnIndex(WORK_KEY_ID)));
                    work.setName(cursor.getString(cursor.getColumnIndex(WORK_KEY_NAME)));
                    work.setType(cursor.getString(cursor.getColumnIndex(WORK_KEY_TYPE)));
                    work.setPrice(cursor.getInt(cursor.getColumnIndex(WORK_KEY_PRICE)));
                    works.add(work);
                } while (cursor.moveToNext());
            }
        }
        return works;
    }

    public void insertFaliat(Faliat faliat) {

        ContentValues values = new ContentValues();

        values.put(FALIAT_KEY_CODE, faliat.getPersonnelID());
        values.put(FALIAT_KEY_WORK_CODE, faliat.getWorkId());
        values.put(FALIAT_KEY_AMOUNT, faliat.getAmount());
        values.put(FALIAT_KEY_DATE, faliat.getDate());
        values.put(FALIAT_KEY_PROJECT_ID, faliat.getProjectID());
        values.put(FALIAT_KEY_SENT, 0);
        this.getWritableDatabase().insert(TABLE_FALIAT, null, values);

    }

    public ArrayList<Taradod> getAllTaradodsWithPersonnel(String projectID) {

        ArrayList<Taradod> taradods = new ArrayList<Taradod>();

        SQLiteDatabase db = getReadableDatabase();

        String query = "SELECT * FROM " + TABLE_TARADOD + " t, " + TABLE_PERSONNEL + " p" +
                " WHERE t." + TARADOD_KEY_PROJECT_ID + "=\"" + projectID + "\" AND t." + TARADOD_KEY_CODE + " = p." + PERSONNEL_KEY_ID + " order by " + TARADOD_KEY_ID + " desc";

        Log.i("ali", query);

        final Cursor cursor = db.rawQuery(query, null);

        if (cursor != null) {
            if (cursor.moveToFirst()) {

                do {

                    Taradod taradod = new Taradod(
                            cursor.getInt(cursor.getColumnIndex(TARADOD_KEY_ID)),
                            cursor.getString(cursor.getColumnIndex(TARADOD_KEY_CODE)),
                            cursor.getString(cursor.getColumnIndex(TARADOD_KEY_IN_OUT)),
                            cursor.getInt(cursor.getColumnIndex(TARADOD_KEY_SENT)),
                            cursor.getString(cursor.getColumnIndex(TARADOD_KEY_DATE)),
                            cursor.getString(cursor.getColumnIndex(TARADOD_KEY_PROJECT_ID)),
                            cursor.getString(cursor.getColumnIndex(TARADOD_KEY_HAS_ERROR))
                    );

                    Personnel personnel = new Personnel();
                    personnel.setId(cursor.getInt(cursor.getColumnIndex(PERSONNEL_KEY_ID)));
                    personnel.setFirst_name(cursor.getString(cursor.getColumnIndex(PERSONNEL_KEY_FIRSTNAME)));
                    personnel.setLast_name(cursor.getString(cursor.getColumnIndex(PERSONNEL_KEY_LASTNAME)));
                    personnel.setPersonnel_code(cursor.getString(cursor.getColumnIndex(PERSONNEL_KEY_CODE)));
                    personnel.setPersonnel_image((cursor.getString(cursor.getColumnIndex(PERSONNEL_KEY_IMAGE))));
                    personnel.setPhone_number((cursor.getString(cursor.getColumnIndex(PERSONNEL_KEY_PHONE))));

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
                " WHERE f." + FALIAT_KEY_PROJECT_ID + "=\"" + projectID + "\" AND  f." + FALIAT_KEY_CODE + " = p." + PERSONNEL_KEY_ID +
                " AND f." + FALIAT_KEY_WORK_CODE + " = w." + WORK_KEY_ID + " order by f." + FALIAT_KEY_ID + " desc";

        final Cursor cursor = db.rawQuery(query, null);

        if (cursor != null) {
            if (cursor.moveToFirst()) {

                do {

                    Faliat faliat = new Faliat(
                            cursor.getInt(cursor.getColumnIndex(FALIAT_KEY_ID)),
                            cursor.getString(cursor.getColumnIndex(FALIAT_KEY_CODE)),
                            cursor.getString(cursor.getColumnIndex(FALIAT_KEY_WORK_CODE)),
                            cursor.getString(cursor.getColumnIndex(FALIAT_KEY_AMOUNT)),
                            cursor.getString(cursor.getColumnIndex(FALIAT_KEY_DATE)),
                            cursor.getInt(cursor.getColumnIndex(FALIAT_KEY_SENT)),
                            cursor.getString(cursor.getColumnIndex(FALIAT_KEY_PROJECT_ID))
                    );

                    Personnel personnel = new Personnel();
                    personnel.setId(cursor.getInt(cursor.getColumnIndex(PERSONNEL_KEY_ID)));
                    personnel.setFirst_name(cursor.getString(cursor.getColumnIndex(PERSONNEL_KEY_FIRSTNAME)));
                    personnel.setLast_name(cursor.getString(cursor.getColumnIndex(PERSONNEL_KEY_LASTNAME)));
                    personnel.setPersonnel_code(cursor.getString(cursor.getColumnIndex(PERSONNEL_KEY_CODE)));
                    personnel.setPersonnel_image((cursor.getString(cursor.getColumnIndex(PERSONNEL_KEY_IMAGE))));
                    personnel.setPhone_number((cursor.getString(cursor.getColumnIndex(PERSONNEL_KEY_PHONE))));

                    faliat.setPersonnel(personnel);

                    Work work = new Work();
                    work.setId(cursor.getInt(cursor.getColumnIndex(WORK_KEY_ID)));
                    work.setName(cursor.getString(cursor.getColumnIndex(WORK_KEY_NAME)));
                    work.setType(cursor.getString(cursor.getColumnIndex(WORK_KEY_TYPE)));
                    work.setPrice(cursor.getInt(cursor.getColumnIndex(WORK_KEY_PRICE)));

                    faliat.setWork(work);

                    faliats.add(faliat);

                } while (cursor.moveToNext());
            }
        }

        return faliats;

    }

    public String getPersonnelInOrOut(Personnel personnel) {
        SQLiteDatabase db = getReadableDatabase();

        String query = "SELECT * FROM " + TABLE_TARADOD + " WHERE " + TARADOD_KEY_CODE + "=\"" + personnel.getId() + "\"" + " order by " + TARADOD_KEY_ID + " desc limit 1";

        final Cursor cursor = db.rawQuery(query, null);

        String in_out = null;
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                in_out = cursor.getString(cursor.getColumnIndex(TARADOD_KEY_IN_OUT));
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
                    chart.setId(cursor.getInt(cursor.getColumnIndex(PROJECT_KEY_ID)));
                    chart.setName(cursor.getString(cursor.getColumnIndex(PROJECT_KEY_NAME)));
                    chart.setType_id(Integer.parseInt(cursor.getString(cursor.getColumnIndex(PROJECT_KEY_TYPE_ID))));
                    chart.setStart_date((cursor.getString(cursor.getColumnIndex(PROJECT_KEY_START_DATE))));
                    chart.setEnd_date((cursor.getString(cursor.getColumnIndex(PROJECT_KEY_END_DATE))));
                    chart.setPrice(cursor.getString(cursor.getColumnIndex(PROJECT_KEY_PRICE)));
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
        final Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_FALIAT + " WHERE " + FALIAT_KEY_SENT + " = \"0\" ;", null);

        if (cursor != null) {
            if (cursor.moveToFirst()) {

                do {

                    Faliat faliat = new Faliat(
                            cursor.getInt(cursor.getColumnIndex(FALIAT_KEY_ID)),
                            cursor.getString(cursor.getColumnIndex(FALIAT_KEY_CODE)),
                            cursor.getString(cursor.getColumnIndex(FALIAT_KEY_WORK_CODE)),
                            cursor.getString(cursor.getColumnIndex(FALIAT_KEY_AMOUNT)),
                            cursor.getString(cursor.getColumnIndex(FALIAT_KEY_DATE)),
                            cursor.getInt(cursor.getColumnIndex(FALIAT_KEY_SENT)),
                            cursor.getString(cursor.getColumnIndex(FALIAT_KEY_PROJECT_ID))


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
                            cursor.getInt(cursor.getColumnIndex(FALIAT_KEY_ID)),
                            cursor.getString(cursor.getColumnIndex(FALIAT_KEY_CODE)),
                            cursor.getString(cursor.getColumnIndex(FALIAT_KEY_WORK_CODE)),
                            cursor.getString(cursor.getColumnIndex(FALIAT_KEY_AMOUNT)),
                            cursor.getString(cursor.getColumnIndex(FALIAT_KEY_DATE)),
                            cursor.getInt(cursor.getColumnIndex(FALIAT_KEY_SENT)),
                            cursor.getString(cursor.getColumnIndex(FALIAT_KEY_PROJECT_ID))


                    );

                    faliats.add(faliat);

                } while (cursor.moveToNext());
            }
        }

        return faliats;
    }

    public void markAsSentFaliat(ArrayList<Faliat> faliats) {

        String idIn = "(";

        if (faliats.size() < 1)
            return;

        idIn += faliats.get(0).getId();

        for (int i = 1; i < faliats.size(); i++) {

            idIn += ", " + faliats.get(i).getId();
        }

        idIn += ")";

        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("UPDATE " + TABLE_FALIAT + " SET " + FALIAT_KEY_SENT + " =\"1\" Where " + FALIAT_KEY_ID + " In " + idIn + ";");

    }

    public void markAsSentTaradod(ArrayList<Taradod> taradods) {

        String idIn = "(";

        if (taradods.size() < 1)
            return;

        idIn += taradods.get(0).getId();

        for (int i = 1; i < taradods.size(); i++) {

            idIn += ", " + taradods.get(i).getId();
        }

        idIn += ")";

        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("UPDATE " + TABLE_TARADOD + " SET " + TARADOD_KEY_SENT + "=\"1\" Where " + TARADOD_KEY_ID + " In " + idIn + ";");

    }

    public void cleanOldData(int days) {
        SQLiteDatabase db = getWritableDatabase();
        Date d = new Date(System.currentTimeMillis() - (86400000) * days);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
        String date = sdf.format(d);
        final Cursor cursor1 = db.rawQuery("delete from " + TABLE_FALIAT + " Where " + FALIAT_KEY_DATE + " < '" + date + "' AND " + FALIAT_KEY_SENT + "=\"1\"", null);
        final Cursor cursor2 = db.rawQuery("delete from " + TABLE_TARADOD + " Where " + TARADOD_KEY_DATE + " < '" + date + "' AND " + TARADOD_KEY_SENT + "=\"1\"", null);

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
            db.execSQL("UPDATE " + TABLE_TARADOD + " SET " + TARADOD_KEY_HAS_ERROR + "=\"1\" Where " + TARADOD_KEY_ID + " In " + idIn + ";");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void emptyAnbarTable() {
        getReadableDatabase().execSQL("Delete from " + TABLE_ANBAR);
    }

    public ArrayList<Anbar> getAllAnbars() {
        ArrayList<Anbar> anbars = new ArrayList<Anbar>();

        SQLiteDatabase db = getReadableDatabase();
        final Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_ANBAR, null);

        if (cursor != null) {
            if (cursor.moveToFirst()) {

                do {

                    Anbar anbar = new Anbar(
                            cursor.getInt(cursor.getColumnIndex(ANBAR_KEY_ID)),
                            cursor.getString(cursor.getColumnIndex(ANBAR_KEY_NAME)),
                            cursor.getInt(cursor.getColumnIndex(ANBAR_KEY_IS_OWNER))
                    );

                    anbars.add(anbar);

                } while (cursor.moveToNext());
            }
        }

        return anbars;
    }

    public ArrayList<Anbar> getMyAnbars() {

        ArrayList<Anbar> anbars = new ArrayList<Anbar>();

        SQLiteDatabase db = getReadableDatabase();
        final Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_ANBAR + " WHERE " + ANBAR_KEY_IS_OWNER + " = \"1\"", null);

        if (cursor != null) {
            if (cursor.moveToFirst()) {

                do {

                    Anbar anbar = new Anbar(
                            cursor.getInt(cursor.getColumnIndex(ANBAR_KEY_ID)),
                            cursor.getString(cursor.getColumnIndex(ANBAR_KEY_NAME)),
                            cursor.getInt(cursor.getColumnIndex(ANBAR_KEY_IS_OWNER))
                    );

                    anbars.add(anbar);

                } while (cursor.moveToNext());
            }
        }

        return anbars;
    }

    public void insertItemsProvider(TaminKonande taminKonande) {
        ContentValues values = new ContentValues();
        values.put(TAMIN_KONANDE_KEY_ID, taminKonande.getId());
        values.put(TAMIN_KONANDE_KEY_NAME, taminKonande.getName());
        values.put(TAMIN_KONANDE_KEY_OWNER, taminKonande.getOwner());
        this.getWritableDatabase().insert(TABLE_TAMIN_KONANDE, null, values);
    }

    public void emptyItemsProviderTable() {
        getReadableDatabase().execSQL("Delete from " + TABLE_TAMIN_KONANDE);
    }

    public void emptyProductTable() {
        getReadableDatabase().execSQL("Delete from " + TABLE_PRODUCT);
    }

    public ArrayList<AnbarTransaction> getAnbarTransactions(Anbar anbar) {
        ArrayList<AnbarTransaction> anbarTransactions = new ArrayList<AnbarTransaction>();

        SQLiteDatabase db = getReadableDatabase();

        String query = "SELECT * FROM " + TABLE_ANBAR_TRANSACTION + " WHERE " +
                ANBAR_TRANSACTION_KEY_ANBAR_ID + "  = " + anbar.getId();

        final Cursor cursor = db.rawQuery(query, null);

        if (cursor != null) {
            if (cursor.moveToFirst()) {

                do {

                    AnbarTransaction anbarTransaction = new AnbarTransaction(
                            cursor.getInt(cursor.getColumnIndex(ANBAR_TRANSACTION_KEY_ID)),
                            cursor.getInt(cursor.getColumnIndex(ANBAR_TRANSACTION_KEY_TYPE)),
                            cursor.getInt(cursor.getColumnIndex(ANBAR_TRANSACTION_KEY_PRODUCT_ID)),
                            cursor.getInt(cursor.getColumnIndex(ANBAR_TRANSACTION_KEY_ANBAR_ID)),
                            cursor.getInt(cursor.getColumnIndex(ANBAR_TRANSACTION_KEY_PROVIDER_ID)),
                            cursor.getInt(cursor.getColumnIndex(ANBAR_TRANSACTION_KEY_TO_ANBAR_ID)),
                            cursor.getInt(cursor.getColumnIndex(ANBAR_TRANSACTION_KEY_FROM_ANBAR_ID)),
                            cursor.getInt(cursor.getColumnIndex(ANBAR_TRANSACTION_KEY_AMOUNT)),
                            cursor.getString(cursor.getColumnIndex(ANBAR_TRANSACTION_KEY_DATE)),
                            cursor.getString(cursor.getColumnIndex(ANBAR_TRANSACTION_KEY_DESCRIPTION)),
                            cursor.getInt(cursor.getColumnIndex(ANBAR_TRANSACTION_KEY_SENT)),
                            cursor.getInt(cursor.getColumnIndex(ANBAR_TRANSACTION_KEY_HAS_ERROR))

                    );


                    // - - - - - - - -   GET PRODUCT
                    String query3 = "SELECT * FROM " + TABLE_PRODUCT + " WHERE " +
                            PRODUCT_KEY_ID + "=" + anbarTransaction.getProduct_id();
                    final Cursor cursor3 = db.rawQuery(query3, null);
                    if (cursor3 != null) {
                        if (cursor3.moveToFirst()) {

                            Product product = new Product(
                                    cursor3.getInt(cursor3.getColumnIndex(PRODUCT_KEY_ID)),
                                    cursor3.getString(cursor3.getColumnIndex(PRODUCT_KEY_NAME))
                            );
                            anbarTransaction.setProduct(product);
                        }
                    }


                    // - - - - - - - - - -  AZ >> TAMIN_KONANDE  BE >> ANBAR MA
                    if (anbarTransaction.getType() == 1) {

                        String query2 = "SELECT * FROM " + TABLE_TAMIN_KONANDE + " WHERE " +
                                TAMIN_KONANDE_KEY_ID + "=" + anbarTransaction.getTaminKonande_id();
                        final Cursor cursor2 = db.rawQuery(query2, null);
                        if (cursor2 != null) {
                            if (cursor2.moveToFirst()) {
                                TaminKonande tamin = new TaminKonande(
                                        cursor2.getInt(cursor2.getColumnIndex(TAMIN_KONANDE_KEY_ID)),
                                        cursor2.getString(cursor2.getColumnIndex(TAMIN_KONANDE_KEY_NAME)),
                                        cursor2.getString(cursor2.getColumnIndex(TAMIN_KONANDE_KEY_OWNER))
                                );
                                anbarTransaction.setTaminKonande(tamin);
                            }
                        }
                    }


                    // - - - - - - - -   AZ >> ANBAR DIGAR  BE >> ANBAR MA
                    if (anbarTransaction.getType() == 3) {
                        String query2 = "SELECT * FROM " + TABLE_ANBAR + " WHERE " +
                                ANBAR_KEY_ID + "=" + anbarTransaction.getFrom_anbar_id();

                        final Cursor cursor2 = db.rawQuery(query2, null);
                        if (cursor2 != null) {
                            if (cursor2.moveToFirst()) {
                                Anbar fromAnbar = new Anbar(
                                        cursor2.getInt(cursor2.getColumnIndex(ANBAR_KEY_ID)),
                                        cursor2.getString(cursor2.getColumnIndex(ANBAR_KEY_NAME)),
                                        cursor2.getInt(cursor2.getColumnIndex(ANBAR_KEY_IS_OWNER))
                                );
                                anbarTransaction.setFromAnbar(fromAnbar);
                            }
                        }
                    }

                    // - - - - - - - -   AZ >> ANBAR MA  BE >> ANBAR DIGAR
                    if (anbarTransaction.getType() == 2) {
                        String query2 = "SELECT * FROM " + TABLE_ANBAR + " WHERE " +
                                ANBAR_KEY_ID + "=" + anbarTransaction.getTo_anbar_id();

                        final Cursor cursor2 = db.rawQuery(query2, null);
                        if (cursor2 != null) {
                            if (cursor2.moveToFirst()) {
                                Anbar toAnbar = new Anbar(
                                        cursor2.getInt(cursor2.getColumnIndex(ANBAR_KEY_ID)),
                                        cursor2.getString(cursor2.getColumnIndex(ANBAR_KEY_NAME)),
                                        cursor2.getInt(cursor2.getColumnIndex(ANBAR_KEY_IS_OWNER))
                                );
                                anbarTransaction.setToAnbar(toAnbar);
                            }
                        }
                    }

                    anbarTransactions.add(anbarTransaction);

                } while (cursor.moveToNext());
            }
        }

        return anbarTransactions;
    }

    public ArrayList<AnbarTransaction> getAllAnbarTransactions() {
        ArrayList<AnbarTransaction> anbarTransactions = new ArrayList<AnbarTransaction>();

        SQLiteDatabase db = getReadableDatabase();

        String query = "SELECT * FROM " + TABLE_ANBAR_TRANSACTION;

        final Cursor cursor = db.rawQuery(query, null);

        if (cursor != null) {
            if (cursor.moveToFirst()) {

                do {

                    AnbarTransaction anbarTransaction = new AnbarTransaction(
                            cursor.getInt(cursor.getColumnIndex(ANBAR_TRANSACTION_KEY_ID)),
                            cursor.getInt(cursor.getColumnIndex(ANBAR_TRANSACTION_KEY_TYPE)),
                            cursor.getInt(cursor.getColumnIndex(ANBAR_TRANSACTION_KEY_PRODUCT_ID)),
                            cursor.getInt(cursor.getColumnIndex(ANBAR_TRANSACTION_KEY_ANBAR_ID)),
                            cursor.getInt(cursor.getColumnIndex(ANBAR_TRANSACTION_KEY_PROVIDER_ID)),
                            cursor.getInt(cursor.getColumnIndex(ANBAR_TRANSACTION_KEY_TO_ANBAR_ID)),
                            cursor.getInt(cursor.getColumnIndex(ANBAR_TRANSACTION_KEY_FROM_ANBAR_ID)),
                            cursor.getInt(cursor.getColumnIndex(ANBAR_TRANSACTION_KEY_AMOUNT)),
                            cursor.getString(cursor.getColumnIndex(ANBAR_TRANSACTION_KEY_DATE)),
                            cursor.getString(cursor.getColumnIndex(ANBAR_TRANSACTION_KEY_DESCRIPTION)),
                            cursor.getInt(cursor.getColumnIndex(ANBAR_TRANSACTION_KEY_SENT)),
                            cursor.getInt(cursor.getColumnIndex(ANBAR_TRANSACTION_KEY_HAS_ERROR))

                    );


                    // - - - - - - - -   GET PRODUCT
                    String query3 = "SELECT * FROM " + TABLE_PRODUCT + " WHERE " +
                            PRODUCT_KEY_ID + "=" + anbarTransaction.getProduct_id();
                    final Cursor cursor3 = db.rawQuery(query3, null);
                    if (cursor3 != null) {
                        if (cursor3.moveToFirst()) {

                            Product product = new Product(
                                    cursor3.getInt(cursor3.getColumnIndex(PRODUCT_KEY_ID)),
                                    cursor3.getString(cursor3.getColumnIndex(PRODUCT_KEY_NAME))
                            );
                            anbarTransaction.setProduct(product);
                        }
                    }


                    // - - - - - - - - - -  AZ >> TAMIN_KONANDE  BE >> ANBAR MA
                    if (anbarTransaction.getType() == 1) {

                        String query2 = "SELECT * FROM " + TABLE_TAMIN_KONANDE + " WHERE " +
                                TAMIN_KONANDE_KEY_ID + "=" + anbarTransaction.getTaminKonande_id();
                        final Cursor cursor2 = db.rawQuery(query2, null);
                        if (cursor2 != null) {
                            if (cursor2.moveToFirst()) {
                                TaminKonande tamin = new TaminKonande(
                                        cursor2.getInt(cursor2.getColumnIndex(TAMIN_KONANDE_KEY_ID)),
                                        cursor2.getString(cursor2.getColumnIndex(TAMIN_KONANDE_KEY_NAME)),
                                        cursor2.getString(cursor2.getColumnIndex(TAMIN_KONANDE_KEY_OWNER))
                                );
                                anbarTransaction.setTaminKonande(tamin);
                            }
                        }
                    }


                    // - - - - - - - -   AZ >> ANBAR DIGAR  BE >> ANBAR MA
                    if (anbarTransaction.getType() == 3) {
                        String query2 = "SELECT * FROM " + TABLE_ANBAR + " WHERE " +
                                ANBAR_KEY_ID + "=" + anbarTransaction.getFrom_anbar_id();

                        final Cursor cursor2 = db.rawQuery(query2, null);
                        if (cursor2 != null) {
                            if (cursor2.moveToFirst()) {
                                Anbar fromAnbar = new Anbar(
                                        cursor2.getInt(cursor2.getColumnIndex(ANBAR_KEY_ID)),
                                        cursor2.getString(cursor2.getColumnIndex(ANBAR_KEY_NAME)),
                                        cursor2.getInt(cursor2.getColumnIndex(ANBAR_KEY_IS_OWNER))
                                );
                                anbarTransaction.setFromAnbar(fromAnbar);
                            }
                        }
                    }

                    // - - - - - - - -   AZ >> ANBAR MA  BE >> ANBAR DIGAR
                    if (anbarTransaction.getType() == 2) {
                        String query2 = "SELECT * FROM " + TABLE_ANBAR + " WHERE " +
                                ANBAR_KEY_ID + "=" + anbarTransaction.getTo_anbar_id();

                        final Cursor cursor2 = db.rawQuery(query2, null);
                        if (cursor2 != null) {
                            if (cursor2.moveToFirst()) {
                                Anbar toAnbar = new Anbar(
                                        cursor2.getInt(cursor2.getColumnIndex(ANBAR_KEY_ID)),
                                        cursor2.getString(cursor2.getColumnIndex(ANBAR_KEY_NAME)),
                                        cursor2.getInt(cursor2.getColumnIndex(ANBAR_KEY_IS_OWNER))
                                );
                                anbarTransaction.setToAnbar(toAnbar);
                            }
                        }
                    }

                    anbarTransactions.add(anbarTransaction);

                } while (cursor.moveToNext());
            }
        }

        return anbarTransactions;
    }

    public void insertAnbarTransaction(AnbarTransaction anbarTransaction) {
        ContentValues values = new ContentValues();
        values.put(ANBAR_TRANSACTION_KEY_TYPE, anbarTransaction.getType());
        values.put(ANBAR_TRANSACTION_KEY_PRODUCT_ID, anbarTransaction.getProduct_id());
        values.put(ANBAR_TRANSACTION_KEY_ANBAR_ID, anbarTransaction.getAnbar_id());
        values.put(ANBAR_TRANSACTION_KEY_PROVIDER_ID, anbarTransaction.getTaminKonande_id());
        values.put(ANBAR_TRANSACTION_KEY_TO_ANBAR_ID, anbarTransaction.getTo_anbar_id());
        values.put(ANBAR_TRANSACTION_KEY_FROM_ANBAR_ID, anbarTransaction.getFrom_anbar_id());
        values.put(ANBAR_TRANSACTION_KEY_AMOUNT, anbarTransaction.getAmount());
        values.put(ANBAR_TRANSACTION_KEY_DATE, anbarTransaction.getDate());
        values.put(ANBAR_TRANSACTION_KEY_DESCRIPTION, anbarTransaction.getDescription());
        this.getWritableDatabase().insert(TABLE_ANBAR_TRANSACTION, null, values);
    }

    public ArrayList<Product> getProducts(String key) {
        ArrayList<Product> products = new ArrayList<Product>();

        SQLiteDatabase db = getReadableDatabase();

        String condition = "";
        if ((key != null) && (key.length() > 0))
            condition = " WHERE " + PRODUCT_KEY_NAME + " like \"" + key + "%\"";
        final Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_PRODUCT + condition, null);


        if (cursor != null) {
            if (cursor.moveToFirst()) {

                do {

                    Product product = new Product(
                            cursor.getInt(cursor.getColumnIndex(PRODUCT_KEY_ID)),
                            cursor.getString(cursor.getColumnIndex(PRODUCT_KEY_NAME))
                    );

                    products.add(product);

                } while (cursor.moveToNext());
            }
        }

        return products;
    }

    public ArrayList<TaminKonande> getTaminKonnande(String key) {
        ArrayList<TaminKonande> taminKonandes = new ArrayList<TaminKonande>();

        SQLiteDatabase db = getReadableDatabase();

        String condition = "";

        if ((key != null) && (key.length() > 0))
            condition = " WHERE " + TAMIN_KONANDE_KEY_NAME + " like \"" + key + "%\"";

        final Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_TAMIN_KONANDE + condition, null);


        if (cursor != null) {
            if (cursor.moveToFirst()) {

                do {

                    TaminKonande tamin = new TaminKonande(
                            cursor.getInt(cursor.getColumnIndex(TAMIN_KONANDE_KEY_ID)),
                            cursor.getString(cursor.getColumnIndex(TAMIN_KONANDE_KEY_NAME)),
                            cursor.getString(cursor.getColumnIndex(TAMIN_KONANDE_KEY_OWNER))
                    );


                    taminKonandes.add(tamin);

                } while (cursor.moveToNext());
            }
        }

        return taminKonandes;
    }


}