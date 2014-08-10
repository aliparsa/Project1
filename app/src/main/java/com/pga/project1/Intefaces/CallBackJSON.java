package com.pga.project1.Intefaces;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by aliparsa on 8/9/2014.
 */
public interface CallBackJSON {

    public void onSuccess(JSONArray result);

    public void onError(String errorMessage);
}