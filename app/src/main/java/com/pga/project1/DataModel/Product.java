package com.pga.project1.DataModel;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by parsa on 2014-11-15.
 */
public class Product {
    int id;
    String name;

    public Product(int id, String name) {
        this.id = id;
        this.name = name;
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


    public static ArrayList<Product> getArrayFromJson(JSONArray jsonArray) {
        ArrayList<Product> itemlist = new ArrayList<Product>();


        for (int i = 0; i < jsonArray.length(); i++) {

            try {
                //if ((JSONObject) jsonArray.get(i)==null) continue;

                JSONObject jsonObject = (JSONObject) jsonArray.get(i);

                int id = jsonObject.getInt("id");
                String name = jsonObject.getString("name");


                Product product = new Product(id, name);

                itemlist.add(product);

            } catch (Exception e) {
                continue;
            }

        }


        return itemlist;
    }
}
