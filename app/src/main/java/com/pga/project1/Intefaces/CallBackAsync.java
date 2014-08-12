package com.pga.project1.Intefaces;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by aliparsa on 8/10/2014.
 */
public interface CallBackAsync<T> {

    public void onSuccess(T json);

    public void onError(String error);



}
