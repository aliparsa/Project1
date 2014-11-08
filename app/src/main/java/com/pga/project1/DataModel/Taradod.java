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
    private String personnelCode;
    private String inOut;
    private int sent;
    private String date;
    private int personnelId;
    private String projectID;


    private Personnel personnel;

    /*TABLE_PERSONNEL + "("
            + KEY_ID + " INTEGER PRIMARY KEY,"
            + KEY_CODE + " TEXT,"
            + KEY_IN_OUT + " TEXT,"
            + KEY_SENT + " TEXT,"
            + KEY_DATE + " TEXT"*/


    public Taradod(int id, String personnelCode, String inOut, int sent, String date, String projectID) {
        this.id = id;
        this.personnelCode = personnelCode;
        this.inOut = inOut;
        this.sent = sent;
        this.date = date;
        this.personnelId = personnelId;
        this.projectID = projectID;
    }

    public Taradod(String personnelCode, String inOut, int sent, String date, String projectID) {
        this.personnelCode = personnelCode;
        this.inOut = inOut;
        this.sent = sent;
        this.personnelId = personnelId;
        this.projectID = projectID;
        this.date = date;

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPersonnelCode() {
        return personnelCode;
    }

    public void setPersonnelCode(String personnelCode) {
        this.personnelCode = personnelCode;
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

    public int getPersonnelId() {
        return personnelId;
    }

    public void setPersonnelId(int personnelId) {
        this.personnelId = personnelId;
    }


    public static JSONArray convertArrayToJson(List<Taradod> taradods){

        JSONArray taradodsJson = new JSONArray();

        for (Taradod tar : taradods) {
            try {
                JSONObject json = new JSONObject();
                json.put("id", tar.getId());
                json.put("personnel_code", tar.getPersonnelCode());
                json.put("in_out", tar.inOut);
                json.put("date", tar.getDate());
                json.put("project_id", tar.getProjectID());

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
}
