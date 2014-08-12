package com.pga.project1.Structures;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;

/**
 * Created by aliparsa on 8/5/2014.
 */
public class AdapterInputType {
/*
list of types that adapter can be handel
    "image"  a view filled by a large image
    "icon+title+subtitle"  an icon + one line text + sub text

 */

    public String type = "";

    public String text1;
    public String text2;
    public String text3;
    public String text4;
    public String text5;

    public int layoutRes;

    public Bitmap image1;
    public Bitmap image2;
    public Bitmap image3;

    private int id;
    private int type_id;

    public AdapterInputType(String type, String title, String subtitle, Bitmap icon) {
        this.type = type;
        this.text1 = title;
        this.text2 = subtitle;
        this.image1 = icon;
    }

    public AdapterInputType(String type, String title, String subtitle, Bitmap icon, int id) {
        this.type = type;
        this.text1 = title;
        this.text2 = subtitle;
        this.image1 = icon;
        this.id = id;
    }

    public AdapterInputType() {

    }


    public View getView(Context context, View convertView) {

        View view;
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService
                    (Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(layoutRes, null);

            return view;
        } else
            return convertView;

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getType_id() {
        return type_id;
    }

    public void setType_id(int type_id) {
        this.type_id = type_id;
    }
}
