package com.pga.project1.Intefaces;

import com.pga.project1.Structures.Chart;

import org.json.JSONArray;

import java.util.ArrayList;

/**
 * Created by aliparsa on 8/10/2014.
 */
public interface CallBackGetChildOfId {

    public void onSuccess(ArrayList<Chart> itemList);

    public void onError(String errorMessage);

}
