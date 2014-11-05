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
import android.widget.TextView;

import com.pga.project1.DataModel.Chart;
import com.pga.project1.DataModel.Faliat;
import com.pga.project1.DataModel.Report;
import com.pga.project1.DataModel.Taradod;
import com.pga.project1.R;
import com.pga.project1.Structures.AdapterInputType;
import com.pga.project1.Viewes.Graphview;
import com.pga.project1.Viewes.ImageLoaderView;

import java.util.List;

/**
 * Created by aliparsa on 8/5/2014.
 */
public class ListViewCustomAdapter extends ArrayAdapter<AdapterInputType> {

    public static String CHART_ITEM = "chart item";
    public static String PERSONNEL_ITEM = "personnelItem";
    public static String LOADING_ITEM = "loading item";
    public static String NOITEM_ITEM = "no item item";
    public static String DRAWER_ITEM = "drawer item";
    public static String REPORT_ITEM = "report item";
    public static String TRADOD_ITEM = "taradod item";
    public static String FALIAT_ITEM = "faliat item";

    public final static int SHERKAT = 1;
    public final static int TAMIN_KONANDE = 2;
    public final static int PROJECT = 3;
    public final static int ANBAR = 4;
    public final static int CHART = 5;
    public final static int FAALIYAT = 6;
    public final static int VAZIFE = 7;


    public List<AdapterInputType> itemList;
    Context context;
    public int layoutResID;
    String IMAGE_DRAWER_ITEM = "image";
    String FOOTER = "footer";

    Object tag;


    // main linear layout in view

    LinearLayout ll_chart;
    LinearLayout ll_people;
    LinearLayout ll_noItem;
    LinearLayout ll_loading;
    LinearLayout ll_drawer;
    LinearLayout ll_report;
    LinearLayout ll_faliat;
    LinearLayout ll_taradod;


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
            //view.startAnimation(animation);

            ll_chart = (LinearLayout) view.findViewById(R.id.ll_chart);
            ll_people = (LinearLayout) view.findViewById(R.id.ll_people);
            ll_noItem = (LinearLayout) view.findViewById(R.id.ll_noItem);
            ll_loading = (LinearLayout) view.findViewById(R.id.ll_loading);
            ll_drawer = (LinearLayout) view.findViewById(R.id.ll_drawer_item);
            ll_report = (LinearLayout) view.findViewById(R.id.ll_report);
            ll_faliat = (LinearLayout) view.findViewById(R.id.ll_faliat);
            ll_taradod = (LinearLayout) view.findViewById(R.id.ll_taradod);

        }


        AdapterInputType item = itemList.get(position);
        view.setTag(holder);


        // Chart item
        if (itemList.get(position).type.equals(CHART_ITEM)) {

            getChartItem(holder, item);
            OnlyShow(ll_chart);
        }

        //PERSONNEL ppl
        if (itemList.get(position).type.equals(PERSONNEL_ITEM)) {

            getPeopleItem(holder, item);
            OnlyShow(ll_people);
        }

        //No Item Item
        if (itemList.get(position).type.equals(NOITEM_ITEM)) {

            OnlyShow(ll_noItem);
        }

        //Loading item
        if (itemList.get(position).type.equals(LOADING_ITEM)) {

            OnlyShow(ll_loading);
        }

        //Drawer item
        if (itemList.get(position).type.equals(DRAWER_ITEM)) {

            OnlyShow(ll_drawer);
            getDrawerItem(holder, item);

        }

        //Report item
        if (itemList.get(position).type.equals(REPORT_ITEM)) {

            OnlyShow(ll_report);
            getReportItem(holder, item);

        }

        //Report item
        if (itemList.get(position).type.equals(FALIAT_ITEM)) {

            OnlyShow(ll_faliat);
            getFaliatItem(holder, item);

        }

        //Report item
        if (itemList.get(position).type.equals(TRADOD_ITEM)) {

            OnlyShow(ll_taradod);
            getTaradodItem(holder, item);

        }

        return view;
    }




    public void OnlyShow(LinearLayout lv) {

        if (ll_chart != null) ll_chart.setVisibility(LinearLayout.GONE);
        if (ll_people != null) ll_people.setVisibility(LinearLayout.GONE);
        if (ll_noItem != null) ll_noItem.setVisibility(LinearLayout.GONE);
        if (ll_loading != null) ll_loading.setVisibility(LinearLayout.GONE);
        if (ll_drawer != null) ll_drawer.setVisibility(LinearLayout.GONE);
        if (ll_report != null) ll_report.setVisibility(LinearLayout.GONE);
        if (ll_faliat != null) ll_faliat.setVisibility(LinearLayout.GONE);
        if (ll_taradod != null) ll_taradod.setVisibility(LinearLayout.GONE);

        lv.setVisibility(LinearLayout.VISIBLE);
    }

    public void getChartItem(DrawerItemHolder holder, AdapterInputType item) {

        if (holder.chartTitle == null)
            holder.chartTitle = (TextView) ll_chart.findViewById(R.id.chart_title);

        if (holder.chartSubtitle == null)
            holder.chartSubtitle = (TextView) ll_chart.findViewById(R.id.chart_subtitle);

        if (holder.chartDate == null)
            holder.chartDate = (TextView) ll_chart.findViewById(R.id.chart_date);

        if (holder.chartGraph == null)
            holder.chartGraph = (Graphview) ll_chart.findViewById(R.id.chart_graph);

        if (holder.chartIcon == null)
            holder.chartIcon = (ImageLoaderView) ll_chart.findViewById(R.id.chart_icon);


        holder.chartTitle.setText(item.title);
        // holder.chartSubtitle.setText(item.subTitle);


        if (item.getTag() != null && item.getTag() instanceof Chart) {

            Chart chart = (Chart) item.getTag();

            holder.chartGraph.showAnimation = item.isFirstTimeItemShowed;


            switch (chart.getType_id()) {
                case VAZIFE:
                    holder.chartGraph.setPercent(chart.getAuto_percent());
                    holder.chartIcon.setBitmapResource(R.drawable.ic_vazife);
                    holder.chartTitle.setText(chart.getPersonnel().getFullName());
                    holder.chartDate.setText(chart.getStart_date());
                    holder.chartIcon.setBitmapResource(R.drawable.ic_vazife);
                    holder.chartSubtitle.setText(chart.getName());
                    break;

                case FAALIYAT:
                    holder.chartTitle.setText(chart.getName());
                    holder.chartIcon.setBitmapResource(R.drawable.ic_faaliyat4);
                    holder.chartDate.setText(chart.getStart_date());
                    break;

                case PROJECT:
                    holder.chartTitle.setText(chart.getName());
                    holder.chartGraph.setPercent(chart.getHand_percent());
                    holder.chartIcon.setBitmapResource(R.drawable.ic_project2);
                    holder.chartDate.setText(chart.getStart_date());
                    break;

                case CHART:
                    holder.chartTitle.setText(chart.getName());
                    holder.chartGraph.setPercent(chart.getHand_percent());
                    holder.chartIcon.setBitmapResource(R.drawable.ic_chart);

                    holder.chartDate.setText(chart.getStart_date());
                    break;
            }
        }
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


        //holder.peopleImageLoader.setBitmapResource(R.drawable.user_photo);
        holder.peopleImageLoader.setUrl(item.imagePerson);
        holder.peopleImageLoader.startLoading();

        holder.PeopleName.setText(item.namePerson);
        holder.PeoplePhone.setText(item.phonePerson);
        holder.PeopleGroups.setText(item.groupPerson);

        holder.setTag(item.getTag());
    }

    public void getReportItem(DrawerItemHolder holder, AdapterInputType item) {


        if (holder.reportTitle == null)
            holder.reportTitle = (TextView) ll_report.findViewById(R.id.ll_report_title);

        if (holder.reportSubtitle == null)
            holder.reportSubtitle = (TextView) ll_report.findViewById(R.id.ll_report_subtitle);

        if (holder.reportDate == null)
            holder.reportDate = (TextView) ll_report.findViewById(R.id.report_date);

        if (holder.reportGraphview == null)
            holder.reportGraphview = (Graphview) ll_report.findViewById(R.id.ll_report_graphview);

        if (holder.reportIcon == null)
            holder.reportIcon = (ImageLoaderView) ll_report.findViewById(R.id.report_icon);

        if (holder.reportAttachmentIcon == null)
            holder.reportAttachmentIcon = (ImageView) ll_report.findViewById(R.id.report_attachmentIcon);


        if (item.getTag() != null && item.getTag() instanceof Report) {

            Report report = (Report) item.getTag();


            holder.reportTitle.setText(" عملکرد ");
            holder.reportDate.setText(report.getDate());

            if (report.getReport().length() > 10)
                holder.reportSubtitle.setText(report.getReport().substring(0, 10) + "...");
            else
                holder.reportSubtitle.setText(report.getReport());

            holder.reportGraphview.showAnimation = item.isFirstTimeItemShowed;
            holder.reportGraphview.setPercent(report.getPercent());
            item.isFirstTimeItemShowed = false;

            holder.reportIcon.setBitmapResource(R.drawable.ic_report2);

            if (report.getImageUrls().size() > 0)
                holder.reportAttachmentIcon.setVisibility(View.VISIBLE);
            else
                holder.reportAttachmentIcon.setVisibility(View.INVISIBLE);

        }


        holder.setTag(item.getTag());
    }

    private void getDrawerItem(DrawerItemHolder holder, AdapterInputType item) {


        if (holder.drawerTitle == null)
            holder.drawerTitle = (TextView) ll_drawer.findViewById(R.id.txt_drawerItem_title);

        if (holder.drawerIcon == null)
            holder.drawerIcon = (ImageView) ll_drawer.findViewById(R.id.imgv_drawerItem_icon);


        holder.drawerTitle.setText(item.getDrawerTitle());
        holder.drawerIcon.setImageResource(item.getDrawerIconResource());
    }

    private void getFaliatItem(DrawerItemHolder holder, AdapterInputType item) {

        if (holder.faliatFullName == null)
            holder.faliatFullName = (TextView) ll_faliat.findViewById(R.id.faliat_fullname);

        if (holder.faliatAmount == null)
            holder.faliatAmount = (TextView) ll_faliat.findViewById(R.id.faliat_amount);

        if (holder.faliatWork == null)
            holder.faliatWork = (TextView) ll_faliat.findViewById(R.id.faliat_work);

        if (holder.faliatDate == null)
            holder.faliatDate = (TextView) ll_faliat.findViewById(R.id.faliat_date);

        Faliat faliat = ((Faliat) item.getTag());

        holder.faliatFullName.setText(faliat.getPersonnel().getFullName());
        holder.faliatAmount.setText(faliat.getAmount());
        holder.faliatWork.setText(faliat.getWork().getName());
        holder.faliatDate.setText(faliat.getDate());
    }

    private void getTaradodItem(DrawerItemHolder holder, AdapterInputType item) {

        if (holder.taradodFullName == null)
            holder.taradodFullName = (TextView) ll_taradod.findViewById(R.id.taradod_fullname);

        if (holder.taradodDate == null)
            holder.taradodDate = (TextView) ll_taradod.findViewById(R.id.taradod_date);

        if (holder.taradodInOut == null)
            holder.taradodInOut = (TextView) ll_taradod.findViewById(R.id.taradod_inout);

        Taradod taradod = ((Taradod) item.getTag());

        holder.taradodFullName.setText(taradod.getPersonnel().getFullName());
        holder.taradodDate.setText(taradod.getDate());
        holder.taradodInOut.setText(taradod.getInOut().equals("in") ? "ورود" : "خروج");
    }

    public static class DrawerItemHolder {


        // Global -------------------------
        private Object tag;


        // Chart Item----------------------
        public TextView chartTitle;
        TextView chartSubtitle;
        ImageLoaderView chartIcon;
        Graphview chartGraph;
        ImageLoaderView img;
        TextView chartDate;

        // People item -----------------
        ImageView peopleImage;
        ImageLoaderView peopleImageLoader;
        TextView PeopleName;
        TextView PeoplePhone;
        TextView PeopleGroups;


        // Navigation Drawer Item --------
        TextView drawerTitle;
        ImageView drawerIcon;
        public TextView index;


        // Report Item ------------
        TextView reportTitle;
        TextView reportSubtitle;
        Graphview reportGraphview;
        ImageLoaderView reportIcon;
        TextView reportDate;
        ImageView reportAttachmentIcon;
        ///------------------------

        //Faliat Item --------------
        TextView faliatFullName;
        TextView faliatAmount;
        TextView faliatWork;
        TextView faliatDate;


        //Taradod
        TextView taradodFullName;
        TextView taradodDate;
        TextView taradodInOut;

        public Object getTag() {
            return tag;
        }

        public void setTag(Object tag) {
            this.tag = tag;
        }
    }

}
