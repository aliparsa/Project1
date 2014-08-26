package com.pga.project1.DataModel;

import com.pga.project1.Utilities.JsonHelper;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by ashkan on 8/26/2014.
 */
public class WorkUnit {


    private int id;
    private String name;

    public static ArrayList<WorkUnit> getArrayFromJson(JSONArray jsonArray) {


        ArrayList<WorkUnit> itemlist = new ArrayList<WorkUnit>();
        try {


            for (int i = 0; i < jsonArray.length(); i++) {


                JSONObject json = (JSONObject) jsonArray.get(i);

                WorkUnit wu = new WorkUnit();

                JsonHelper helper = new JsonHelper();

                wu.setId(helper.getInt(json, "id", -1));
                if (helper.error)
                    continue;

                wu.setName(helper.getString(json, "name", ""));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        } finally {

            return itemlist;
        }
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }


    @Override
    public String toString() {
        return getName();
    }
}
