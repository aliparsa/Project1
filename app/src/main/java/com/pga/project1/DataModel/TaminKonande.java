package com.pga.project1.DataModel;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by parsa on 2014-11-15.
 */
public class TaminKonande {
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    int id;
    String name;
    String owner;

    public TaminKonande(int id, String name, String owner) {
        this.id = id;
        this.name = name;
        this.owner = owner;

    }

    public static ArrayList<TaminKonande> getArrayFromJson(JSONArray jsonArray) {
        ArrayList<TaminKonande> itemlist = new ArrayList<TaminKonande>();


        for (int i = 0; i < jsonArray.length(); i++) {

            try {

                JSONObject jsonObject = (JSONObject) jsonArray.get(i);

                int id = jsonObject.getInt("id");
                String name = jsonObject.getString("name");
                String owner = jsonObject.getString("owner");


                TaminKonande taminKonande = new TaminKonande(id, name, owner);

                itemlist.add(taminKonande);

            } catch (Exception e) {
                continue;
            }

        }


        return itemlist;
    }
}
