package com.pga.project1.DataModel;

import com.pga.project1.Intefaces.PathMapObject;
import com.pga.project1.Utilities.JsonHelper;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by aliparsa on 8/9/2014.
 */
@SuppressWarnings("serial")
public class Chart implements PathMapObject, Serializable {

    private int id;
    private int type_id;
    private String name;
    private String start_date;
    private String end_date;
    private String allow_delay;
    //    private String estimated_start;
//    private String estimated_end;
    private String status;
    private String price;
    private String is_pay;
    private String root_id;
    private String work_unit;

    public void setId(int id) {
        this.id = id;
    }

    public void setType_id(int type_id) {
        this.type_id = type_id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setStart_date(String start_date) {
        this.start_date = start_date;
    }

    public void setEnd_date(String end_date) {
        this.end_date = end_date;
    }

    public void setAllow_delay(String allow_delay) {
        this.allow_delay = allow_delay;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public void setIs_pay(String is_pay) {
        this.is_pay = is_pay;
    }

    public void setRoot_id(String root_id) {
        this.root_id = root_id;
    }

    public void setWork_unit(String work_unit) {
        this.work_unit = work_unit;
    }

    private int auto_percent;
    private int hand_percent;
    private ArrayList<Feature> featureList;
    private String image;
    // private int percent;



    private Personnel personnel;


    public Chart(int id, int type_id, String name, String start_date, String end_date, Personnel personnel, String allow_delay, String status, String price, String is_pay, String root_id, String work_unit, int auto_percent, int hand_percent, ArrayList<Feature> featureList, String image) {
        this.id = id;
        this.type_id = type_id;
        this.name = name;
        this.start_date = start_date;
        this.end_date = end_date;
        this.personnel = personnel;
        this.allow_delay = allow_delay;
//        this.estimated_start = estimated_start;
//        this.estimated_end = estimated_end;
        this.status = status;
        this.price = price;
        this.is_pay = is_pay;
        this.root_id = root_id;
        this.work_unit = work_unit;
        this.auto_percent = auto_percent;
        this.hand_percent = hand_percent;
        this.featureList = featureList;
        this.image = image;
    }

    public Chart() {

    }


    // Method

    public static ArrayList<Chart> getArrayFromJson(JSONArray jsonArray) {
        ArrayList<Chart> itemlist = new ArrayList<Chart>();


        for (int i = 0; i < jsonArray.length(); i++) {

            try {
                //if ((JSONObject) jsonArray.get(i)==null) continue;

                JSONObject jsonObject = (JSONObject) jsonArray.get(i);

                int id = jsonObject.getInt("id");
                int type_id = jsonObject.getInt("type_id");
                String name = jsonObject.getString("name");
                String start_date = jsonObject.getString("start_date");
                String end_date = jsonObject.getString("end_date");

                JSONObject personnelJson = jsonObject.getJSONObject("personnel");
                Personnel personnel = Personnel.getPersonnelFromJson(personnelJson);

                JSONArray featureJson = jsonObject.getJSONArray("features");
                ArrayList<Feature> featureList = Feature.getArrayFromJson(featureJson);

                String allow_delay = jsonObject.getString("allow_delay");
//                String estimated_start = jsonObject.getString("estimated_start");
//                String estimated_end = jsonObject.getString("estimated_end");
                String status = jsonObject.getString("status");
                String price = jsonObject.getString("price");
                String is_pay = jsonObject.getString("is_pay");
                String root_id = jsonObject.getString("root_id");
                String work_unit = jsonObject.getString("work_unit");


                int auto_percent = JsonHelper.getIntS(jsonObject, "auto_percent", 0);
                int hand_percent = JsonHelper.getIntS(jsonObject, "hand_percent", 0);

                String image = JsonHelper.getStringS(jsonObject, "image", null);

                Chart chartItem = new Chart(
                        id,
                        type_id,
                        name,
                        start_date,
                        end_date,
                        personnel,
                        allow_delay,
                        status,
                        price,
                        is_pay,
                        root_id,
                        work_unit,
                        auto_percent,
                        hand_percent,
                        featureList,
                        image
                );

                itemlist.add(chartItem);
            } catch (Exception e) {
                continue;
            }

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

    @Override
    public int getType() {
        return 0;
    }

    @Override
    public Object getSelf() {
        return this;
    }


    public String getStart_date() {
        return start_date;
    }

    public String getEnd_date() {
        return end_date;
    }

    public int getPersonnel_id() {
        return personnel.getId();
    }

    public String getAllow_delay() {
        return allow_delay;
    }

//    public String getEstimated_start() {
//        return estimated_start;
//    }

//    public String getEstimated_end() {
//        return estimated_end;
//    }

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

//    public int getPercent() {
//        return percent;
//    }
//
//    public void setPercent(int percent) {
//        this.percent = percent;
//    }

    public Personnel getPersonnel() {
        return personnel;
    }

    public void setPersonnel(Personnel personnel) {
        this.personnel = personnel;
    }

    public int getAuto_percent() {
        return auto_percent;
    }

    public void setAuto_percent(int auto_percent) {
        this.auto_percent = auto_percent;
    }

    public int getHand_percent() {
        return hand_percent;
    }

    public void setHand_percent(int hand_percent) {
        this.hand_percent = hand_percent;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}


