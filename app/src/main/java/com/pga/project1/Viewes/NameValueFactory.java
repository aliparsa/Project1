package com.pga.project1.Viewes;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.pga.project1.R;

/**
 * Created by aliparsa on 8/13/2014.
 */
public class NameValueFactory {

    private TextView txt_name;
    private TextView txt_value;
    private RelativeLayout rl;

    public NameValueFactory(Context context, String name, String value) {

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        rl = (RelativeLayout) inflater.inflate(R.layout.view_name_value, null);

        txt_name = (TextView) rl.findViewById(R.id.txt_view_name_value_name);
        txt_value = (TextView) rl.findViewById(R.id.txt_view_name_value_value);

        txt_name.setText(name);
        txt_value.setText(value);
    }


    public static View getNameValueView(Context context, String name, String value) {

        return null;
    }


}
