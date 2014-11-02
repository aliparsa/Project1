package com.pga.project1.DataModel;

import com.pga.project1.Utilities.JsonHelper;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by aliparsa on 11/2/2014.
 */
public class Work {
    private int id;
    private String name;
    private String type;

    public Work() {

    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    private int price;


    public Work(int id, String name, String type, int price) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.price = price;
    }

    public static ArrayList<Work> getArrayFromJson(JSONArray jsonArray) {
        ArrayList<Work> itemlist = new ArrayList<Work>();


        for (int i = 0; i < jsonArray.length(); i++) {

            try {

                JSONObject jsonObject = (JSONObject) jsonArray.get(i);
                int id = jsonObject.getInt("id");
                String name = jsonObject.getString("name");
                String type = jsonObject.getString("type");
                int price = jsonObject.getInt("price");


                Work work = new Work(
                        id,
                        name,
                        type,
                        price
                );

                itemlist.add(work);
            } catch (Exception e) {
                continue;
            }

        }


        return itemlist;
    }

}
