package com.pga.project1.Viewes;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;

import com.pga.project1.R;

/**
 * Created by ashkan on 8/18/2014.
 */
public class ViewDateTimePickerPersian extends LinearLayout {

    int year;
    int month;
    int day;

    int hour;
    int minute;


    public ViewDateTimePickerPersian(Context context, AttributeSet attrs) {
        super(context);

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.view_name_value, this, true);



        invalidate();
        requestLayout();
    }

}
