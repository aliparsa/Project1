package com.pga.project1.DataModel;

import com.pga.project1.Utilities.JsonHelper;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by ashkan on 8/17/2014.
 */

@SuppressWarnings("serial")
public class Personnel implements Serializable {


    private int id;
    private String first_name;
    private String last_name;
    private String personnel_code;
    private String phone_number;
    private String personnel_image;
    private ArrayList<String> workGroups = new ArrayList<String>();


    public Personnel(int id, String first_name, String last_name, String personnel_code, String phone_number, String personnel_image, ArrayList<String> workGroups) {
        this.id = id;
        this.first_name = first_name;
        this.last_name = last_name;
        this.personnel_code = personnel_code;
        this.phone_number = phone_number;
        this.personnel_image = personnel_image;
        this.workGroups = workGroups;
    }


    private Personnel() {

    }

    public Personnel(int id, String first_name, String last_name, String personnel_code, String phone_number) {
        this.id = id;
        this.first_name = first_name;
        this.last_name = last_name;
        this.personnel_code = personnel_code;
        this.phone_number = phone_number;
    }

    public static ArrayList<Personnel> getArrayFromJson(JSONArray jsonArray) {

        ArrayList<Personnel> array = new ArrayList<Personnel>();

        try {

            for (int i = 0; i < jsonArray.length(); i++) {

                JSONObject json = (JSONObject) jsonArray.get(i);

                Personnel p = getPersonnelFromJson(json);

                if (p == null)
                    continue;

                //String json
                array.add(p);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }


        return array;
    }


    public static Personnel getPersonnelFromJson(JSONObject json) {

        Personnel p = null;

        try {
            JsonHelper jsonHelper = new JsonHelper();

            p = new Personnel();

            p.id = jsonHelper.getInt(json, "id", 0);  //json.getString("id");

            if (jsonHelper.error)
                return null;

            p.first_name = jsonHelper.getString(json, "first_name", ""); //json.getString("first_name");
            p.last_name = jsonHelper.getString(json, "last_name", ""); //json.getString("last_name");
            p.personnel_code = jsonHelper.getString(json, "personnel_code", ""); //json.getString("personnel_code");
            p.personnel_image = jsonHelper.getString(json, "personnel_image", "");

            if (jsonHelper.error)
                return null;

            p.phone_number = jsonHelper.getString(json, "phone_number", ""); //json.getString("phone_number");

            JSONArray groupsJson = jsonHelper.getJsonArray(json, "groups", new JSONArray());

            for (int j = 0; j < groupsJson.length(); j++) {
                p.workGroups.add(groupsJson.getString(j));

            }
        } catch (JSONException e) {
            e.printStackTrace();
        } finally {
            return p;
        }
    }

    public String getGroupsString() {

        String str = "";
        boolean isFirst = true;

        for (String group : workGroups) {
            if (isFirst)
                str += group;
            else
                str += " ," + group;

            isFirst = false;
        }

        return str;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getPersonnel_code() {
        return personnel_code;
    }

    public void setPersonnel_code(String personnel_code) {
        this.personnel_code = personnel_code;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }


    public String getFullName() {
        return this.getFirst_name() + " " + this.getLast_name();
    }

    public String getPersonnel_image() {
        return personnel_image;
    }

    public void setPersonnel_image(String personnel_image) {
        this.personnel_image = personnel_image;
    }
}
