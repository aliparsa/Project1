package com.pga.project1;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

/**
 * Created by aliparsa on 8/5/2014.
 */
public class CustomNavigationDrawerAdapter extends ArrayAdapter<AdapterInputType> {

    Context context;
    public List<AdapterInputType> itemList;
    int layoutResID;

    String IMAGE_DRAWER_ITEM = "image";
    String ICON_TITLE_SUBTITLE = "icon+title+subtitle";
    String FOOTER = "footer";


    // main linear layout in view
    LinearLayout lv_image;
    LinearLayout lv_icon_title_subtitle;


    public CustomNavigationDrawerAdapter(Context context, int layoutResourceID,
                                         List<AdapterInputType> itemList) {
        super(context, layoutResourceID, itemList);

        this.context = context;
        this.itemList = itemList;
        this.layoutResID = layoutResourceID;

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub

        View view = convertView;

        DrawerItemHolder drawerItemHolder = new DrawerItemHolder();
        //View view = convertView;

        if (view == null) {
            // TODO New View Created

            // create view
            LayoutInflater inflater = (LayoutInflater) context.getSystemService
                    (Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.drawer_item, null);

            lv_image = (LinearLayout) view.findViewById(R.id.lv_image);
            lv_icon_title_subtitle = (LinearLayout) view.findViewById(R.id.lv_icon_title_subtitle);

            // ITEM 1
            if(itemList.get(position).type.equals(IMAGE_DRAWER_ITEM)){

                // define item holder
                drawerItemHolder.icon= (ImageView) view.findViewById(R.id.icon);
                drawerItemHolder.icon_in_title_subtitle= (ImageView) view.findViewById(R.id.icon2);
                drawerItemHolder.title = (TextView) view.findViewById(R.id.title);
                drawerItemHolder.subtitle = (TextView) view.findViewById(R.id.subtitle);

                // set values
                drawerItemHolder.icon.setImageBitmap(itemList.get(position).icon);

                PleaseOnlyShow(lv_image);

                // set item holder to view
                view.setTag(drawerItemHolder);

            }


            // ITEM 2
            if (itemList.get(position).type.equals(ICON_TITLE_SUBTITLE)) {

                // define item holder
                drawerItemHolder.icon = (ImageView) view.findViewById(R.id.icon);
                drawerItemHolder.icon_in_title_subtitle = (ImageView) view.findViewById(R.id.icon2);
                drawerItemHolder.title = (TextView) view.findViewById(R.id.title);
                drawerItemHolder.subtitle = (TextView) view.findViewById(R.id.subtitle);

                // set values
                drawerItemHolder.icon_in_title_subtitle.setImageBitmap(itemList.get(position).icon);
                drawerItemHolder.title.setText(itemList.get(position).title);
                drawerItemHolder.subtitle.setText(itemList.get(position).subtitle);


                PleaseOnlyShow(lv_icon_title_subtitle);

                // set item holder to view
                view.setTag(drawerItemHolder);
            }


                // ITEM 3
                if (itemList.get(position).type.equals(FOOTER)) {

                    // define item holder
                    drawerItemHolder.icon = (ImageView) view.findViewById(R.id.icon);
                    drawerItemHolder.icon_in_title_subtitle = (ImageView) view.findViewById(R.id.icon2);
                    drawerItemHolder.title = (TextView) view.findViewById(R.id.title);
                    drawerItemHolder.subtitle = (TextView) view.findViewById(R.id.subtitle);

                    // set values
                    drawerItemHolder.icon_in_title_subtitle.setImageBitmap(itemList.get(position).icon);
                    drawerItemHolder.title.setText(itemList.get(position).title);
                    drawerItemHolder.subtitle.setText(itemList.get(position).subtitle);


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
            if(itemList.get(position).type.equals(IMAGE_DRAWER_ITEM)){
                // set values
                drawerItemHolder.icon.setImageBitmap(itemList.get(position).icon);
                PleaseOnlyShow(lv_image);

            }


            // ITEM 2
            if (itemList.get(position).type.equals(ICON_TITLE_SUBTITLE)){
                // set values
                drawerItemHolder.icon_in_title_subtitle.setImageBitmap(itemList.get(position).icon);
                drawerItemHolder.title.setText(itemList.get(position).title);
                drawerItemHolder.subtitle.setText(itemList.get(position).subtitle);
                PleaseOnlyShow(lv_icon_title_subtitle);

            }


        }


        return view;
    }

    private static class DrawerItemHolder {
        TextView title;
        TextView subtitle;
        ImageView icon;
        ImageView icon_in_title_subtitle;

    }

    public void  PleaseOnlyShow(LinearLayout lv){

        lv_image.setVisibility(LinearLayout.GONE);
        lv_icon_title_subtitle.setVisibility(LinearLayout.GONE);
        lv.setVisibility(LinearLayout.VISIBLE);

    }


}
