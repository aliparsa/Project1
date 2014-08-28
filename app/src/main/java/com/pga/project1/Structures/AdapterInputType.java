package com.pga.project1.Structures;

import android.graphics.Bitmap;

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


    public String text3;
    public String text4;
    public String text5;

    public int layoutRes;


    public Bitmap image2;
    public Bitmap image3;

    //chart row item-------------------
    public String title;
    public String subTitle;
    public Bitmap image1;
    public boolean isFirstTimeItemShowed = true;


    //people row item------------------
    public String namePerson;
    public String phonePerson;
    public String groupPerson;
    public String imagePerson;


    public AdapterInputType(Object tag, String type, String namePerson, String phonePerson, String groupPerson, String imagePerson) {
        this.type = type;
        this.namePerson = namePerson;
        this.phonePerson = phonePerson;
        this.groupPerson = groupPerson;
        this.imagePerson = imagePerson;
        this.tag = tag;
    }

    //-----------------------------------

    private int id;
    private int type_id;

    public Object tag;

    public AdapterInputType(Object tag, String type, String title, String subtitle, Bitmap icon) {
        this.tag = tag;
        this.type = type;
        this.title = title;
        this.subTitle = subtitle;
        this.image1 = icon;
    }


    public AdapterInputType(Object tag, String type, String title, String subtitle, Bitmap icon, int id) {
        this.type = type;
        this.title = title;
        this.subTitle = subtitle;
        this.image1 = icon;
        this.id = id;
        this.tag = tag;
    }



    public AdapterInputType(String type) {
        this.type = type;
    }

    public AdapterInputType() {

    }



    /*public View getView(Context context, View convertView) {

        View view;
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService
                    (Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(layoutRes, null);

            return view;
        } else
            return convertView;

    }*/

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

    public Object getTag() {
        return tag;
    }

    public void setTag(Object tag) {
        this.tag = tag;
    }
}
