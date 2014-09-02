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

public class Windows extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View windows = inflater.inflate(R.layout.f1, container, false);
        ((TextView) windows.findViewById(R.id.txt_index)).setText("Windows");
        return windows;
    }
}