package com.pga.project1.Utilities;

import android.content.Context;

import com.pga.project1.Adapters.ListViewCustomAdapter;
import com.pga.project1.Adapters.ListViewObjectAdapter;
import com.pga.project1.Structures.AdapterInputType;

import java.util.ArrayList;

/**
 * Created by ashkan on 8/28/2014.
 */
public class ListViewAdapterHandler {


    public static ListViewCustomAdapter checkAdapterForNoItem(ListViewCustomAdapter adapter) {

        if (adapter.itemList.size() == 0) {

            AdapterInputType ait = new AdapterInputType(ListViewCustomAdapter.NOITEM_ITEM);
            adapter.itemList.add(ait);
            adapter.notifyDataSetChanged();
        }

        return adapter;
    }


    public static ListViewCustomAdapter getLoadingAdapter(Context context) {

        ArrayList<AdapterInputType> loadingList = new ArrayList<AdapterInputType>();


        AdapterInputType ait = new AdapterInputType(ListViewCustomAdapter.LOADING_ITEM);
        loadingList.add(ait);


        return new ListViewCustomAdapter(context, 0, loadingList);
    }
}
