package com.pga.project1.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.pga.project1.R;
import com.pga.project1.Structures.AdapterInputType;

import java.util.List;

/**
 * Created by aliparsa on 8/5/2014.
 */
public class ListViewCustomAdapter extends ArrayAdapter<AdapterInputType> {

    public static String ICON_TITLE_SUBTITLE = "icon+title+subtitle";
    public static String PERSONNEL_ITEM = "personnelItem";
    public List<AdapterInputType> itemList;
    Context context;
    int layoutResID;
    String IMAGE_DRAWER_ITEM = "image";
    String FOOTER = "footer";

    Object tag;


    // main linear layout in view
    LinearLayout lv_image;
    LinearLayout lv_icon_title_subtitle;
    private LinearLayout ll_people;


    public ListViewCustomAdapter(Context context, int layoutResourceID,
                                 List<AdapterInputType> itemList) {
        super(context, layoutResourceID, itemList);

        this.context = context;
        this.itemList = itemList;
        this.layoutResID = layoutResourceID;

    }

    /*@Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub

        View view = convertView;

        DrawerItemHolder drawerItemHolder = new DrawerItemHolder();
        //View view = convertView;


        if (view == null) {
            // TODO New View Created

            //create view
            LayoutInflater inflater = (LayoutInflater) context.getSystemService
                    (Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.drawer_item, null);


            lv_image = (LinearLayout) view.findViewById(R.id.lv_image);
            lv_icon_title_subtitle = (LinearLayout) view.findViewById(R.id.lv_icon_title_subtitle);
            ll_people = (LinearLayout) view.findViewById(R.id.ll_people);

            // define item holder
            drawerItemHolder.icon = (ImageView) view.findViewById(R.id.icon);
            drawerItemHolder.icon_in_title_subtitle = (ImageView) view.findViewById(R.id.icon2);
            drawerItemHolder.title = (TextView) view.findViewById(R.id.title);
            drawerItemHolder.subtitle = (TextView) view.findViewById(R.id.subtitle);
            drawerItemHolder.tag = itemList.get(position).getTag();

            // ITEM 1
            if (itemList.get(position).type.equals(IMAGE_DRAWER_ITEM)) {

                // set values
                drawerItemHolder.icon.setImageBitmap(itemList.get(position).image1);

                PleaseOnlyShow(lv_image);

                // set item holder to view
                view.setTag(drawerItemHolder);
            }


            // ITEM 2
            if (itemList.get(position).type.equals(ICON_TITLE_SUBTITLE)) {

                // set values
                drawerItemHolder.icon_in_title_subtitle.setImageBitmap(itemList.get(position).image1);
                drawerItemHolder.title.setText(itemList.get(position).text1);
                drawerItemHolder.subtitle.setText(itemList.get(position).text2);

                PleaseOnlyShow(lv_icon_title_subtitle);

                // set item holder to view
                view.setTag(drawerItemHolder);
            }


            // ITEM 3
            if (itemList.get(position).type.equals(FOOTER)) {
                // set values
                drawerItemHolder.icon_in_title_subtitle.setImageBitmap(itemList.get(position).image1);
                drawerItemHolder.title.setText(itemList.get(position).text1);
                drawerItemHolder.subtitle.setText(itemList.get(position).text2);

                PleaseOnlyShow(lv_icon_title_subtitle);

                // set item holder to view
                view.setTag(drawerItemHolder);
            }


        } else {
            // TODO Recyle View To use;
            drawerItemHolder = (DrawerItemHolder) view.getTag();

            lv_image = (LinearLayout) view.findViewById(R.id.lv_image);
            lv_icon_title_subtitle = (LinearLayout) view.findViewById(R.id.lv_icon_title_subtitle);


            // ITEM 1
            if (itemList.get(position).type.equals(IMAGE_DRAWER_ITEM)) {
                // set values
                drawerItemHolder.icon.setImageBitmap(itemList.get(position).image1);
                drawerItemHolder.tag = itemList.get(position).getTag();
                PleaseOnlyShow(lv_image);

            }


            // ITEM 2
            if (itemList.get(position).type.equals(ICON_TITLE_SUBTITLE)) {
                // set values
                drawerItemHolder.icon_in_title_subtitle.setImageBitmap(itemList.get(position).image1);
                drawerItemHolder.title.setText(itemList.get(position).text1);
                drawerItemHolder.subtitle.setText(itemList.get(position).text2);
                drawerItemHolder.tag = itemList.get(position).getTag();
                PleaseOnlyShow(lv_icon_title_subtitle);

            }


        }


        // set animation
       // Animation anim = AnimationUtils.loadAnimation(context,
        //        R.anim.slide_from_left);
       // view.setAnimation(anim);


        return view;
    }*/

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub

        View view = convertView;

        DrawerItemHolder holder;
        if (view != null) {
            holder = (DrawerItemHolder) view.getTag();
        } else {
            holder = new DrawerItemHolder();

            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.drawer_item, null);

            lv_image = (LinearLayout) view.findViewById(R.id.lv_image);
            lv_icon_title_subtitle = (LinearLayout) view.findViewById(R.id.lv_icon_title_subtitle);
            ll_people = (LinearLayout) view.findViewById(R.id.ll_people);
        }


        AdapterInputType item = itemList.get(position);
        view.setTag(holder);

        // ITEM 1
        if (itemList.get(position).type.equals(IMAGE_DRAWER_ITEM)) {

            getImageOnlyItem(holder, item);
            PleaseOnlyShow(lv_image);

        }

        // ITEM 2
        if (itemList.get(position).type.equals(ICON_TITLE_SUBTITLE)) {

            getIconTitleSubtitle(holder, item);
            PleaseOnlyShow(lv_icon_title_subtitle);

        }

        //PERSONNEL ppl
        if (itemList.get(position).type.equals(PERSONNEL_ITEM)) {

            getPeopleItem(holder, item);
            PleaseOnlyShow(ll_people);

        }

        return view;
    }

    public void PleaseOnlyShow(LinearLayout lv) {

        lv_image.setVisibility(LinearLayout.GONE);
        lv_icon_title_subtitle.setVisibility(LinearLayout.GONE);
        ll_people.setVisibility(LinearLayout.GONE);

        lv.setVisibility(LinearLayout.VISIBLE);
    }


    public void getIconTitleSubtitle(DrawerItemHolder holder, AdapterInputType item){

        if(holder.icon == null)
            holder.icon = (ImageView) lv_icon_title_subtitle.findViewById(R.id.icon);

        if(holder.icon_in_title_subtitle == null)
            holder.icon_in_title_subtitle = (ImageView) lv_icon_title_subtitle.findViewById(R.id.icon2);

        if(holder.title == null)
            holder.title = (TextView) lv_icon_title_subtitle.findViewById(R.id.title);

        if(holder.subtitle == null)
            holder.subtitle = (TextView) lv_icon_title_subtitle.findViewById(R.id.subtitle);


        holder.icon_in_title_subtitle.setImageBitmap(item.image1);
        holder.title.setText(item.text1);
        holder.subtitle.setText(item.text2);

        holder.setTag(item.getTag());
    }

    public void getImageOnlyItem(DrawerItemHolder holder, AdapterInputType item){

        if(holder.icon == null)
            holder.icon = (ImageView) lv_image.findViewById(R.id.icon);

        holder.icon.setImageBitmap(item.image1);

        holder.setTag(item.getTag());
    }

    public void getPeopleItem(DrawerItemHolder holder, AdapterInputType item){

        if(holder.peopleImage == null)
            holder.peopleImage = (ImageView) ll_people.findViewById(R.id.imgv_people_imag);

        if(holder.PeopleName == null)
            holder.PeopleName = (TextView) ll_people.findViewById(R.id.txt_people_name);

        if(holder.PeoplePhone == null)
            holder.PeoplePhone = (TextView) ll_people.findViewById(R.id.txt_people_phone);

        if(holder.PeopleGroups == null)
            holder.PeopleGroups = (TextView) ll_people.findViewById(R.id.txt_people_groups);

        holder.PeopleName.setText(item.namePerson);
        holder.PeoplePhone.setText(item.phonePerson);
        holder.PeopleGroups.setText(item.groupPerson);

        holder.setTag(item.getTag());
    }

    public static class DrawerItemHolder {
        public TextView title;
        TextView subtitle;
        ImageView icon;
        ImageView icon_in_title_subtitle;
        private Object tag;

        ImageView peopleImage;
        TextView PeopleName;
        TextView PeoplePhone;
        TextView PeopleGroups;


        public Object getTag() {
            return tag;
        }

        public void setTag(Object tag) {
            this.tag = tag;
        }
    }


}
