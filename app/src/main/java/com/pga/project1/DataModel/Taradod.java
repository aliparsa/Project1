package com.pga.project1.DataModel;

/**
 * Created by ashkan on 2014-11-02.
 */
public class Taradod {

    private int id;
    private String personnelCode;
    private String inOut;
    private int sent;
    private int personnelId;

    /*TABLE_PERSONNEL + "("
            + KEY_ID + " INTEGER PRIMARY KEY,"
            + KEY_CODE + " TEXT,"
            + KEY_IN_OUT + " TEXT,"
            + KEY_SENT + " TEXT,"
            + KEY_DATE + " TEXT"*/




    public Taradod(int id, String personnelCode, String inOut, int sent, String date) {
        this.id = id;
        this.personnelCode = personnelCode;
        this.inOut = inOut;
        this.sent = sent;
        this.personnelId = personnelId;
    }

    public Taradod(String personnelCode, String inOut, int sent, String date) {
        this.personnelCode = personnelCode;
        this.inOut = inOut;
        this.sent = sent;
        this.personnelId = personnelId;
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
}
