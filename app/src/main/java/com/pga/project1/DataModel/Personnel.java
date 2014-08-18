package com.pga.project1.DataModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by ashkan on 8/17/2014.
 */
public class Personnel {


    private int id;
    private String first_name;
    private String last_name;
    private String personnel_code;
    private String phone_number;
    private ArrayList<String> groups = new ArrayList<String>();


    private Personnel(){

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


        for (int i = 0; i < jsonArray.length(); i++) {


            try {

                JSONObject json = (JSONObject) jsonArray.get(i);
                Personnel p = new Personnel();
                p.id = json.getInt("id");
                p.first_name = json.getString("first_name");
                p.last_name = json.getString("last_name");
                p.personnel_code = json.getString("personnel_code");
                p.phone_number = json.getString("phone_number");

                //String json
                array.add(p);

            } catch (JSONException e) {
                e.printStackTrace();
                continue;
            }
            }


        return array;
    }


    public String getGroupsString() {

        String str = "";
        boolean isFirst = true;

        for (String group : groups){
            if(isFirst)
                str += str;
            else
                str += " ," + str;

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


}
