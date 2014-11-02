package com.pga.project1.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.SpinnerAdapter;
import android.widget.TextView;

import com.pga.project1.DataModel.Work;
import com.pga.project1.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by aliparsa on 11/2/2014.
 */
public class MySpinnerAdapter extends BaseAdapter implements SpinnerAdapter {

    private List<Work> items; // replace MyListItem with your model object type
    private Context context;

    public MySpinnerAdapter(Context aContext, ArrayList<Work> itemlist) {
        context = aContext;
        items = new ArrayList<Work>();
        items.add(null); // add first dummy item - selection of this will be ignored

        for (Work work : itemlist) {
            items.add(work);
        }
    }

    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public Object getItem(int aPosition) {
        return items.get(aPosition);
    }

    @Override
    public long getItemId(int aPosition) {
        return aPosition;
    }

    @Override
    public View getView(int aPosition, View aView, ViewGroup aParent) {
        TextView text;
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        text = (TextView) inflater.inflate(R.layout.spiner_item, null);

        if (aPosition == 0) {
            text.setText("نوع کار"); // text for first dummy item
        } else {
            text.setText(items.get(aPosition).getName());
            // or use whatever model attribute you'd like displayed instead of toString()
        }
        text.setTag(items.get(aPosition));
        return text;
    }
}
