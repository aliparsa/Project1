package com.pga.project1.test;

/**
 * Created by aliparsa on 9/1/2014.
 */

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.pga.project1.R;

public class Ios extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View ios = inflater.inflate(R.layout.f1, container, false);
        ((TextView) ios.findViewById(R.id.txt_index)).setText("iOS");
        return ios;
    }
}