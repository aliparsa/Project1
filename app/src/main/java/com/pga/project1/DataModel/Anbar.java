package com.pga.project1.DataModel;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.pga.project1.R;
import com.pga.project1.Utilities.JsonHelper;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by parsa on 11/12/2014.
 */
public class Anbar {

    private int id;
    private String name;
    private int is_owner;

    public Anbar(int id, String name, int is_owner) {
        this.id = id;
        this.name = name;
        this.is_owner = is_owner;
    }


    public static ArrayList<Anbar> getArrayFromJson(JSONArray jsonArray) {
        ArrayList<Anbar> itemlist = new ArrayList<Anbar>();


        for (int i = 0; i < jsonArray.length(); i++) {

            try {
                //if ((JSONObject) jsonArray.get(i)==null) continue;

                JSONObject jsonObject = (JSONObject) jsonArray.get(i);

                int id = jsonObject.getInt("id");
                String name = jsonObject.getString("name");
                int is_owner = jsonObject.getInt("is_owner");


                Anbar anbar = new Anbar(id, name, is_owner);

                itemlist.add(anbar);

            } catch (Exception e) {
                continue;
            }

        }


        return itemlist;
    }

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

    public int getIs_owner() {
        return is_owner;
    }

    public void setIs_owner(int is_owner) {
        this.is_owner = is_owner;
    }


    public View getView(Context context, View oldView) {
        if (oldView == null || !(oldView.getTag() instanceof Anbar)) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            oldView = inflater.inflate(R.layout.anbar_item, null);
            Holder anbarHolder = new Holder();
            oldView.setTag(anbarHolder);
            getTaradodItem(anbarHolder, oldView);


            return oldView;
        } else {
            Holder anbarHolder = (Holder) oldView.getTag();
            getTaradodItem(anbarHolder, oldView);
            return oldView;
        }
    }

    private void getTaradodItem(Holder holder, View view) {

        holder.anbar = this;

        if (holder.anbarName == null)
            holder.anbarName = (TextView) view.findViewById(R.id.anbar_name);

        holder.anbarName.setText(getName());


    }


    public class Holder {
        TextView anbarName;
        Anbar anbar;
    }
}
