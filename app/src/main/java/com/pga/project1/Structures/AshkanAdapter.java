package com.pga.project1.Structures;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import java.util.ArrayList;

/**
 * Created by ashkan on 8/10/2014.
 */
public class AshkanAdapter extends ArrayAdapter<ListViewRowItem> {

    public AshkanAdapter(Context context, int resource, ArrayList<ListViewRowItem> listItem) {
        super(context, resource, listItem);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        return this.getItem(position).getView(this.getContext(), convertView);
    }
}
