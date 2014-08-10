package com.pga.project1.Intefaces;

import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by aliparsa on 8/10/2014.
 */
public interface CallBackLogin {

    public void onSuccess(String token);

    public void onError(String errorMessage);

}
