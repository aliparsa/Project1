package com.pga.project1.DataModel;

import com.pga.project1.Utilities.JsonHelper;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by aliparsa on 8/17/2014.
 */
@SuppressWarnings("serial")
public class Report implements Serializable {


    private String image;

    public Report(int id, Chart chart, int personnel_id, String date, String report, int percent) {
        this.id = id;
        this.chart_id = chart.getId();
        this.personnel_id = personnel_id;
        this.date = date;
        this.report = report;
        this.percent = percent;
        this.chart = chart;


    }


    public Report(int id, int chart_id, int personnel_id, String date, String report, int percent) {
        this.id = id;
        this.chart_id = chart_id;
        this.personnel_id = personnel_id;
        this.date = date;
        this.report = report;
        this.percent = percent;
        this.chart = chart;
    }

    public Report(int id, int chart_id, int personnel_id, String date, String report, int percent, ArrayList<String> imageUrls) {

        this.id = id;
        this.chart_id = chart_id;
        this.personnel_id = personnel_id;
        this.date = date;
        this.report = report;
        this.percent = percent;
        this.imageUrls = imageUrls;
    }

    private int id;
    private int chart_id;
    private int personnel_id;
    private String date;
    private String report;
    private int percent;
    private Chart chart;
    private ArrayList<String> imageUrls = new ArrayList<String>();


    public static ArrayList<Report> getArrayFromJson(JSONArray jsonArray) {
        ArrayList<Report> itemlist = new ArrayList<Report>();
        try {


            for (int i = 0; i < jsonArray.length(); i++) {

                JSONObject jsonObject = (JSONObject) jsonArray.get(i);
                int id = JsonHelper.getIntS(jsonObject, "id", -1);
                int chart_id = jsonObject.getInt("chart_id");
                int personnel_id = jsonObject.getInt("personnel_id");
                String date = jsonObject.getString("date");
                String report = jsonObject.getString("report");
                int percent = jsonObject.getInt("percent");

                JSONArray imageJson = jsonObject.getJSONArray("images");

                ArrayList<String> imageUrls = new ArrayList<String>();

                for (int j = 0; j < imageJson.length(); j++) {

                    imageUrls.add(JsonHelper.getStringS((JSONObject) imageJson.get(j), "image", ""));
                }

                Report reportItem = new Report(id, chart_id, personnel_id, date, report, percent, imageUrls);
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

    public Chart getChart() {
        return chart;
    }

    public void setChart(Chart chart) {
        this.chart = chart;
    }


    public ArrayList<String> getImageUrls() {
        return imageUrls;
    }

    public void setImageUrls(ArrayList<String> imageUrls) {
        this.imageUrls = imageUrls;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}