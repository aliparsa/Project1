package com.pga.project1.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.pga.project1.DataModel.Chart;
import com.pga.project1.R;
import com.pga.project1.Structures.AdapterInputType;
import com.pga.project1.Viewes.Graphview;
import com.pga.project1.Viewes.ImageLoaderView;

import java.util.List;

/**
 * Created by aliparsa on 8/5/2014.
 */
public class ListViewCustomAdapter extends ArrayAdapter<AdapterInputType> {

    public static String ICON_TITLE_SUBTITLE = "icon+title+subtitle";
    public static String PERSONNEL_ITEM = "personnelItem";
    public static String LOADING_ITEM = "loading item";
    public static String NOITEM_ITEM = "no item item";
    public List<AdapterInputType> itemList;
    Context context;
    public int layoutResID;
    String IMAGE_DRAWER_ITEM = "image";
    String FOOTER = "footer";

    Object tag;


    // main linear layout in view
    LinearLayout lv_image;
    LinearLayout lv_icon_title_subtitle;
    LinearLayout ll_people;
    LinearLayout ll_noItem;
    LinearLayout ll_loading;


    public ListViewCustomAdapter(Context context, int layoutResourceID,
                                 List<AdapterInputType> itemList) {
        super(context, layoutResourceID, itemList);

        this.context = context;
        this.itemList = itemList;
        this.layoutResID = layoutResourceID;

    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

         View view = convertView;

        DrawerItemHolder holder;
        if (view != null) {
            holder = (DrawerItemHolder) view.getTag();
        } else {
            holder = new DrawerItemHolder();


            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.drawer_item, null);

            Animation animation = AnimationUtils.loadAnimation(context, R.anim.activity_fade_in_animation);
            animation.setStartOffset(20 * (position + 1));
            view.startAnimation(animation);

            lv_image = (LinearLayout) view.findViewById(R.id.lv_image);
            lv_icon_title_subtitle = (LinearLayout) view.findViewById(R.id.lv_icon_title_subtitle);
            ll_people = (LinearLayout) view.findViewById(R.id.ll_people);
            ll_noItem = (LinearLayout) view.findViewById(R.id.ll_noItem);
            ll_loading= (LinearLayout) view.findViewById(R.id.ll_loading);
        }


        AdapterInputType item = itemList.get(position);
        view.setTag(holder);

        // ITEM 1
        if (itemList.get(position).type.equals(IMAGE_DRAWER_ITEM)) {

            getImageOnlyItem(holder, item);
            OnlyShow(lv_image);

        }

        // ITEM 2
        if (itemList.get(position).type.equals(ICON_TITLE_SUBTITLE)) {

            getIconTitleSubtitle(holder, item);
            OnlyShow(lv_icon_title_subtitle);

        }

        //PERSONNEL ppl
        if (itemList.get(position).type.equals(PERSONNEL_ITEM)) {

            getPeopleItem(holder, item);
            OnlyShow(ll_people);

        }

        //PERSONNEL ppl
        if (itemList.get(position).type.equals(NOITEM_ITEM)) {

            OnlyShow(ll_noItem);

        }

        //PERSONNEL ppl
        if (itemList.get(position).type.equals(LOADING_ITEM)) {

            OnlyShow(ll_loading);

        }


        return view;
    }

    public void OnlyShow(LinearLayout lv) {

        lv_image.setVisibility(LinearLayout.GONE);
        lv_icon_title_subtitle.setVisibility(LinearLayout.GONE);
        ll_people.setVisibility(LinearLayout.GONE);
        ll_noItem.setVisibility(LinearLayout.GONE);
        ll_loading.setVisibility(LinearLayout.GONE);

        lv.setVisibility(LinearLayout.VISIBLE);
    }

    public void getIconTitleSubtitle(DrawerItemHolder holder, AdapterInputType item) {

        if (holder.icon == null)
            holder.icon = (ImageView) lv_icon_title_subtitle.findViewById(R.id.icon);

        if (holder.icon_in_title_subtitle == null)
            holder.icon_in_title_subtitle = (ImageView) lv_icon_title_subtitle.findViewById(R.id.icon2);

        if (holder.title == null)
            holder.title = (TextView) lv_icon_title_subtitle.findViewById(R.id.title);

        if (holder.subtitle == null)
            holder.subtitle = (TextView) lv_icon_title_subtitle.findViewById(R.id.subtitle);

        if (holder.progressBar == null)
            holder.progressBar = (ProgressBar) lv_icon_title_subtitle.findViewById(R.id.progressBar2);

        if (holder.percent == null)
            holder.percent = (TextView) lv_icon_title_subtitle.findViewById(R.id.percent_textview);

        if (holder.graphview == null)
            holder.graphview = (Graphview) lv_icon_title_subtitle.findViewById(R.id.graph_view);

        if (holder.img == null)
            holder.img = (ImageLoaderView) lv_icon_title_subtitle.findViewById(R.id.imgview_item);


        holder.icon_in_title_subtitle.setImageBitmap(item.image1);
        holder.title.setText(item.title);
        holder.subtitle.setText(item.subTitle);


        if (item.getTag() != null && item.getTag() instanceof Chart) {

            holder.progressBar.setProgress((((Chart) item.getTag()).getHand_percent()));
            holder.percent.setText((((Chart) item.getTag()).getHand_percent()) + " % ");
            holder.graphview.showAnimation = item.isFirstTimeItemShowed;
            holder.graphview.setPercent(((Chart) item.getTag()).getHand_percent());
            item.isFirstTimeItemShowed = false;

            holder.img.setUrl(((Chart) item.getTag()).getImage());
            holder.img.startLoading();


        }
        //
//

        holder.setTag(item.getTag());
    }

    public void getImageOnlyItem(DrawerItemHolder holder, AdapterInputType item) {

        if (holder.icon == null)
            holder.icon = (ImageView) lv_image.findViewById(R.id.icon);

        holder.icon.setImageBitmap(item.image1);

        holder.setTag(item.getTag());
    }

    public void getPeopleItem(DrawerItemHolder holder, AdapterInputType item) {

        if (holder.peopleImage == null)
            holder.peopleImage = (ImageView) ll_people.findViewById(R.id.imgv_people_imag);

        if (holder.peopleImageLoader == null)
            holder.peopleImageLoader = (ImageLoaderView) ll_people.findViewById(R.id.imgl_people_imag);

        if (holder.PeopleName == null)
            holder.PeopleName = (TextView) ll_people.findViewById(R.id.txt_people_name);

        if (holder.PeoplePhone == null)
            holder.PeoplePhone = (TextView) ll_people.findViewById(R.id.txt_people_phone);

        if (holder.PeopleGroups == null)
            holder.PeopleGroups = (TextView) ll_people.findViewById(R.id.txt_people_groups);


        holder.peopleImageLoader.setUrl(item.imagePerson);
        holder.peopleImageLoader.startLoading();

        holder.PeopleName.setText(item.namePerson);
        holder.PeoplePhone.setText(item.phonePerson);
        holder.PeopleGroups.setText(item.groupPerson);

        holder.setTag(item.getTag());
    }

    public static class DrawerItemHolder {

        //----------------------
        public TextView title;
        TextView subtitle;
        ImageView icon;
        ImageView icon_in_title_subtitle;
        private Object tag;
        ProgressBar progressBar;
        TextView percent;
        Graphview graphview;
        ImageLoaderView img;

        //---------------------
        ImageView peopleImage;
        ImageLoaderView peopleImageLoader;
        TextView PeopleName;
        TextView PeoplePhone;
        TextView PeopleGroups;
        //---------------------

        public Object getTag() {
            return tag;
        }

        public void setTag(Object tag) {
            this.tag = tag;
        }
    }


}
