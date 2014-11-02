package com.pga.project1.DataModel;

/**
 * Created by ashkan on 2014-11-02.
 */
public class Faliat {


    private int id;
    private String personnelCode;
    private String workId;
    private String amount;
    private String date;

    public Faliat(int id, String personnelCode, String workId, String amount, String date){


        this.id = id;
        this.personnelCode = personnelCode;
        this.workId = workId;
        this.amount = amount;
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
}
