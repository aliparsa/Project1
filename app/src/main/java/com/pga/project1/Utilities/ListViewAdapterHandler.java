package com.pga.project1.Utilities;

import com.pga.project1.Adapters.ListViewCustomAdapter;
import com.pga.project1.Structures.AdapterInputType;

/**
 * Created by ashkan on 8/28/2014.
 */
public class ListViewAdapterHandler {


    public static ListViewCustomAdapter checkAdapterForNoItem(ListViewCustomAdapter adapter){

        if(adapter.itemList.size() == 0){

            AdapterInputType ait = new AdapterInputType(ListViewCustomAdapter.NOITEM_ITEM);
            adapter.itemList.add(ait);
            adapter.notifyDataSetChanged();
        }

        return adapter;
    }
}
