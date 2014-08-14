package com.pga.project1.DataModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by aliparsa on 8/13/2014.
 */
public class Feature {
    private String name;
    private String value;


    public Feature(String name, String value) {

        this.name = name;
        this.value = value;

    }

    public static ArrayList<Feature> getArrayFromJson(JSONArray jsonArray) {
        ArrayList<Feature> itemlist = new ArrayList<Feature>();
        try {


            for (int i = 0; i < jsonArray.length(); i++) {

                JSONObject jsonObject = (JSONObject) jsonArray.get(i);
                String name = jsonObject.getString("name");
                String value = jsonObject.getString("value");
                Feature featureItem = new Feature(name, value);
                itemlist.add(featureItem);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return itemlist;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
