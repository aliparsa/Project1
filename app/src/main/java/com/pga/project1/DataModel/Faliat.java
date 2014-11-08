package com.pga.project1.DataModel;

import com.pga.project1.Utilities.PersianCalendar;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

/**
 * Created by ashkan on 2014-11-02.
 */
public class Faliat {


    private int id;
    private String personnelCode;
    private String workId;
    private String amount;
    private String date;
    private int sent;
    private String projectID;

    private Personnel personnel;
    private Work work;

    public Faliat(int id, String personnelCode, String workId, String amount, String date, int sent, String projectID) {


        this.id = id;
        this.personnelCode = personnelCode;
        this.workId = workId;
        this.amount = amount;
        this.date = date;
        this.sent = sent;
        this.projectID = projectID;

    }

    public Faliat(String personnelCode, String workId, String amount, String date, int sent, String projectID) {


        this.personnelCode = personnelCode;
        this.workId = workId;
        this.amount = amount;
        this.date = date;
        this.sent = sent;
        this.projectID = projectID;

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

    public String getWorkId() {
        return workId;
    }

    public void setWorkId(String workId) {
        this.workId = workId;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getPersianDate() {
        PersianCalendar pc = new PersianCalendar(getDate());
        return pc.getIranianDateTime();
    }

    public Personnel getPersonnel() {
        return personnel;
    }

    public void setPersonnel(Personnel personnel) {
        this.personnel = personnel;
    }

    public Work getWork() {
        return work;
    }

    public void setWork(Work work) {
        this.work = work;
    }

    public static JSONArray convertArrayToJson(List<Faliat> faliats) {

        JSONArray faliatJson = new JSONArray();

        for (Faliat faliat : faliats) {
            try {
                JSONObject json = new JSONObject();
                json.put("id", 0);
                json.put("work_code", faliat.getWorkId());
                json.put("personnel_code", faliat.getPersonnelCode());
                json.put("amount", faliat.getAmount());
                json.put("date", faliat.getDate());

                faliatJson.put(json);
            } catch (JSONException e) {
                e.printStackTrace();
            }

        }

        return faliatJson;
    }

    public String getProjectID() {
        return projectID;
    }

    public void setProjectID(String projectID) {
        this.projectID = projectID;
    }
}
