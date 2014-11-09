package com.pga.project1.DataModel;

import com.pga.project1.Utilities.PersianCalendar;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

/**
 * Created by ashkan on 2014-11-02.
 */
public class Taradod {

    private int id;
    private String personnelID;
    private String inOut;
    private int sent;
    private String date;
    private String projectID;
    private int has_error;


    private Personnel personnel;

    /*TABLE_PERSONNEL + "("
            + KEY_ID + " INTEGER PRIMARY KEY,"
            + KEY_CODE + " TEXT,"
            + KEY_IN_OUT + " TEXT,"
            + KEY_SENT + " TEXT,"
            + KEY_DATE + " TEXT"*/


    public Taradod(int id, String personnelId, String inOut, int sent, String date, String projectID) {
        this.id = id;
        this.inOut = inOut;
        this.sent = sent;
        this.date = date;
        this.personnelID = personnelId;
        this.projectID = projectID;
    }

    public Taradod(String personnelId, String inOut, int sent, String date, String projectID) {

        this.inOut = inOut;
        this.sent = sent;
        this.projectID = projectID;
        this.date = date;
        this.personnelID = personnelId;


    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public String getInOut() {
        return inOut;
    }

    public void setInOut(String inOut) {
        this.inOut = inOut;
    }

    public int getSent() {
        return sent;
    }

    public void setSent(int sent) {
        this.sent = sent;
    }




    public static JSONArray convertArrayToJson(List<Taradod> taradods){

        JSONArray taradodsJson = new JSONArray();

        for (Taradod tar : taradods) {
            try {
                JSONObject json = new JSONObject();

                json.put("uid", tar.getId());
                json.put("personnel_id", tar.getPersonnelID());
                json.put("chart_id", tar.getProjectID());
                json.put("in_out", tar.inOut.equals("in") ? "1" : "0");
                json.put("date", tar.getDate());


                taradodsJson.put(json);
            } catch (JSONException e) {
                e.printStackTrace();
            }

        }

        return taradodsJson;
    }

    public String getDate() {
        return date;
    }

    public String getPersianDate() {
        PersianCalendar pc = new PersianCalendar(getDate());
        return pc.getIranianDateTime();
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Personnel getPersonnel() {
        return personnel;
    }

    public void setPersonnel(Personnel personnel) {
        this.personnel = personnel;
    }

    public String getProjectID() {
        return projectID;
    }

    public void setProjectID(String projectID) {
        this.projectID = projectID;
    }

    public String getPersonnelID() {
        return personnelID;
    }

    public void setPersonnelID(String personnelID) {
        this.personnelID = personnelID;
    }

    public int getHas_error() {
        return has_error;
    }

    public void setHas_error(int has_error) {
        this.has_error = has_error;
    }
}
