package com.pga.project1.DataModel;

import com.pga.project1.Intefaces.PathMapObject;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by aliparsa on 8/9/2014.
 */
@SuppressWarnings("serial")
public class Task implements Serializable {


    private String name;
    private String Price;
    private String start_date;
    private String end_date;
    private String KolKar;
    private String VahedKar;
    private String Tozihat;

    public Task(String name, String price, String start_date, String end_date, String kolKar, String vahedKar, String tozihat) {
        this.name = name;
        Price = price;
        this.start_date = start_date;
        this.end_date = end_date;
        KolKar = kolKar;
        VahedKar = vahedKar;
        Tozihat = tozihat;
    }


    public String getStart_date() {
        return start_date;
    }

    public void setStart_date(String start_date) {
        this.start_date = start_date;
    }

    public String getEnd_date() {
        return end_date;
    }

    public void setEnd_date(String end_date) {
        this.end_date = end_date;
    }

    public String getKolKar() {
        return KolKar;
    }

    public void setKolKar(String kolKar) {
        KolKar = kolKar;
    }

    public String getVahedKar() {
        return VahedKar;
    }

    public void setVahedKar(String vahedKar) {
        VahedKar = vahedKar;
    }

    public String getTozihat() {
        return Tozihat;
    }

    public void setTozihat(String tozihat) {
        Tozihat = tozihat;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return Price;
    }

    public void setPrice(String price) {
        Price = price;
    }


    // Method
//
//    public static ArrayList<Task> getArrayFromJson(JSONArray jsonArray) {
//        ArrayList<Task> itemlist = new ArrayList<Task>();
//
//
//        for (int i = 0; i < jsonArray.length(); i++) {
//
//            try {
//                //if ((JSONObject) jsonArray.get(i)==null) continue;
//
//                JSONObject jsonObject = (JSONObject) jsonArray.get(i);
//
//                int id = jsonObject.getInt("id");
//                int type_id = jsonObject.getInt("type_id");
//                String name = jsonObject.getString("name");
//                String start_date = jsonObject.getString("start_date");
//                String end_date = jsonObject.getString("end_date");
//                String personnel_id = jsonObject.getString("personnel_id");
//                String allow_delay = jsonObject.getString("allow_delay");
//                String estimated_start = jsonObject.getString("estimated_start");
//                String estimated_end = jsonObject.getString("estimated_end");
//                String status = jsonObject.getString("status");
//                String price = jsonObject.getString("price");
//                String is_pay = jsonObject.getString("is_pay");
//                String root_id = jsonObject.getString("root_id");
//                String work_unit = jsonObject.getString("work_unit");
//                int percent = jsonObject.getInt("percent");
//
//                Task chartItem = new Task(
//                        id,
//                        type_id,
//                        name,
//                        start_date,
//                        end_date,
//                        personnel_id,
//                        allow_delay,
//                        estimated_start,
//                        estimated_end,
//                        status,
//                        price,
//                        is_pay,
//                        root_id,
//                        work_unit,
//                        percent
//                );
//
//                itemlist.add(chartItem);
//            } catch (Exception e) {
//                continue;
//            }
//
//        }
//
//
//        return itemlist;
//    }
//
//    public String getShamsiStartDate() {
//        return "";
//    }
//
//    public String getShamsiEndDate() {
//        return "";
//    }
//
//    public int getId() {
//        return id;
//    }
//
//    public int getType_id() {
//        return type_id;
//    }
//
//    public String getName() {
//        return name;
//    }
//
//    @Override
//    public int getType() {
//        return 0;
//    }
//
//    @Override
//    public Object getSelf() {
//        return this;
//    }
//
//
//    public String getStart_date() {
//        return start_date;
//    }
//
//    public String getEnd_date() {
//        return end_date;
//    }
//
//    public String getPersonnel_id() {
//        return personnel_id;
//    }
//
//    public String getAllow_delay() {
//        return allow_delay;
//    }
//
//    public String getEstimated_start() {
//        return estimated_start;
//    }
//
//    public String getEstimated_end() {
//        return estimated_end;
//    }
//
//    public String getStatus() {
//        return status;
//    }
//
//    public String getPrice() {
//        return price;
//    }
//
//    public String getIs_pay() {
//        return is_pay;
//    }
//
//    public String getRoot_id() {
//        return root_id;
//    }
//
//    public String getWork_unit() {
//        return work_unit;
//    }
//
//    public ArrayList<Feature> getFeatureList() {
//        return featureList;
//    }
//
//    public void setFeatureList(ArrayList<Feature> featureList) {
//        this.featureList = featureList;
//    }
//
//    public int getPercent() {
//        return percent;
//    }
//
//    public void setPercent(int percent) {
//        this.percent = percent;
//    }
}


