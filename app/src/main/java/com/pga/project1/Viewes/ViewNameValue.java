package com.pga.project1.Viewes;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.pga.project1.R;

/**
 * Created by aliparsa on 8/13/2014.
 */
public class ViewNameValue extends RelativeLayout {

    private TextView name;
    private TextView value;

    public ViewNameValue(Context context, AttributeSet attrs) {
        super(context, attrs);

        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.ViewNameValue);
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.view_name_value, this, true);

        String strname = a.getString(R.styleable.ViewNameValue_name);
        String strvalue = a.getString(R.styleable.ViewNameValue_value);

        name = (TextView) findViewById(R.id.txt_view_name_value_name);
        value = (TextView) findViewById(R.id.txt_view_name_value_value);

        name.setText(strname);
        value.setText(strvalue);

        invalidate();
        requestLayout();
    }


    public ViewNameValue(Context context) {
        super(context);

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.view_name_value, this, true);

        name = (TextView) findViewById(R.id.txt_view_name_value_name);
        value = (TextView) findViewById(R.id.txt_view_name_value_value);


        invalidate();
        requestLayout();
    }


    public ViewNameValue(Context context, String strname, String strvalue) {
        super(context);

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.view_name_value, this, true);

        name = (TextView) findViewById(R.id.txt_view_name_value_name);
        value = (TextView) findViewById(R.id.txt_view_name_value_value);

        setNameValue(strname, strvalue);
    }

    public void setName(String strname) {
        name.setText(strname);

        invalidate();
        requestLayout();

    }


    public void setValue(String strvalue) {
        value.setText(strvalue);

        invalidate();
        requestLayout();
    }


    public void setNameValue(String strname, String strvalue) {
        name.setText(strname);
        value.setText(strvalue);

        invalidate();
        requestLayout();
    }

}
