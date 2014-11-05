package com.pga.project1.DataModel;

import com.pga.project1.Utilities.PersianCalendar;

/**
 * Created by ashkan on 2014-11-02.
 */
public class Faliat {


    private int id;
    private String personnelCode;
    private String workId;
    private String amount;
    private String date;

    private Personnel personnel;
    private Work work;

    public Faliat(int id, String personnelCode, String workId, String amount, String date){


        this.id = id;
        this.personnelCode = personnelCode;
        this.workId = workId;
        this.amount = amount;
        this.date = date;
    }

    public Faliat(String personnelCode, String workId, String amount, String date) {


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
}
