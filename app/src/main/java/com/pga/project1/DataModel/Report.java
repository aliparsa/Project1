package com.pga.project1.DataModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by aliparsa on 8/17/2014.
 */
public class Report {
    public Report(int id, int chart_id, int personnel_id, String date, String report, int percent) {
        this.id = id;
        this.chart_id = chart_id;
        this.personnel_id = personnel_id;
        this.date = date;
        this.report = report;
        this.percent = percent;
    }

    private int id;
    private int chart_id;
    private int personnel_id;
    private String date;
    private String report;
    private int percent;


    public static ArrayList<Report> getArrayFromJson(JSONArray jsonArray) {
        ArrayList<Report> itemlist = new ArrayList<Report>();
        try {


            for (int i = 0; i < jsonArray.length(); i++) {

                JSONObject jsonObject = (JSONObject) jsonArray.get(i);
                int id = jsonObject.getInt("id");
                int chart_id = jsonObject.getInt("chart_id");
                int personnel_id = jsonObject.getInt("personnel_id");
                String date = jsonObject.getString("date");
                String report = jsonObject.getString("report");
                int percent = jsonObject.getInt("percent");

                Report reportItem = new Report(id, chart_id, personnel_id, date, report, percent);
                itemlist.add(reportItem);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return itemlist;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getChart_id() {
        return chart_id;
    }

    public void setChart_id(int chart_id) {
        this.chart_id = chart_id;
    }

    public int getPersonnel_id() {
        return personnel_id;
    }

    public void setPersonnel_id(int personnel_id) {
        this.personnel_id = personnel_id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getPercent() {
        return percent;
    }

    public void setPercent(int percent) {
        this.percent = percent;
    }

    public String getReport() {
        return report;
    }

    public void setReport(String report) {
        this.report = report;
    }
}