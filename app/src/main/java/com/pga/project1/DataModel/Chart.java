package com.pga.project1.DataModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by aliparsa on 8/9/2014.
 */
public class Chart {

    private int id;
    private int type_id;
    private String name;
    private String start_date;
    private String end_date;
    private String personnel_id;
    private String allow_delay;
    private String estimated_start;
    private String estimated_end;
    private String status;
    private String price;
    private String is_pay;
    private String root_id;
    private String work_unit;
    private ArrayList<Feature> featureList;


    public Chart(int id, int type_id, String name, String start_date, String end_date, String personnel_id, String allow_delay, String estimated_start, String estimated_end, String status, String price, String is_pay, String root_id, String work_unit) {
        this.id = id;
        this.type_id = type_id;
        this.name = name;
        this.start_date = start_date;
        this.end_date = end_date;
        this.personnel_id = personnel_id;
        this.allow_delay = allow_delay;
        this.estimated_start = estimated_start;
        this.estimated_end = estimated_end;
        this.status = status;
        this.price = price;
        this.is_pay = is_pay;
        this.root_id = root_id;
        this.work_unit = work_unit;
    }


    // Method

    public static ArrayList<Chart> getArrayFromJson(JSONArray jsonArray) {
        ArrayList<Chart> itemlist = new ArrayList<Chart>();
        try {


            for (int i = 0; i < jsonArray.length(); i++) {

                //if ((JSONObject) jsonArray.get(i)==null) continue;

                JSONObject jsonObject = (JSONObject) jsonArray.get(i);

                int id = jsonObject.getInt("id");
                int type_id = jsonObject.getInt("type_id");
                String name = jsonObject.getString("name");
                String start_date = jsonObject.getString("start_date");
                String end_date = jsonObject.getString("end_date");
                String personnel_id = jsonObject.getString("personnel_id");
                String allow_delay = jsonObject.getString("allow_delay");
                String estimated_start = jsonObject.getString("estimated_start");
                String estimated_end = jsonObject.getString("estimated_end");
                String status = jsonObject.getString("status");
                String price = jsonObject.getString("price");
                String is_pay = jsonObject.getString("is_pay");
                String root_id = jsonObject.getString("root_id");
                String work_unit = jsonObject.getString("work_unit");

                Chart chartItem = new Chart(
                        id,
                        type_id,
                        name,
                        start_date,
                        end_date,
                        personnel_id,
                        allow_delay,
                        estimated_start,
                        estimated_end,
                        status,
                        price,
                        is_pay,
                        root_id,
                        work_unit
                );

                itemlist.add(chartItem);

            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return itemlist;
    }

    public String getShamsiStartDate() {
        return "";
    }

    public String getShamsiEndDate() {
        return "";
    }

    public int getId() {
        return id;
    }

    public int getType_id() {
        return type_id;
    }

    public String getName() {
        return name;
    }

    public String getStart_date() {
        return start_date;
    }

    public String getEnd_date() {
        return end_date;
    }

    public String getPersonnel_id() {
        return personnel_id;
    }

    public String getAllow_delay() {
        return allow_delay;
    }

    public String getEstimated_start() {
        return estimated_start;
    }

    public String getEstimated_end() {
        return estimated_end;
    }

    public String getStatus() {
        return status;
    }

    public String getPrice() {
        return price;
    }

    public String getIs_pay() {
        return is_pay;
    }

    public String getRoot_id() {
        return root_id;
    }

    public String getWork_unit() {
        return work_unit;
    }

    public ArrayList<Feature> getFeatureList() {
        return featureList;
    }

    public void setFeatureList(ArrayList<Feature> featureList) {
        this.featureList = featureList;
    }
}


