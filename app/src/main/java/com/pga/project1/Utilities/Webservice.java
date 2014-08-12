package com.pga.project1.Utilities;

import android.widget.Toast;

import com.pga.project1.Asyncs.AsyncGetChildById;
import com.pga.project1.Asyncs.AsyncLoad;
import com.pga.project1.Asyncs.AsyncLoginByUserPass;
import com.pga.project1.Intefaces.CallBackArraylist;
import com.pga.project1.Intefaces.CallBackAsync;
import com.pga.project1.Intefaces.CallBackGetChildOfId;
import com.pga.project1.Intefaces.CallBackJSON;
import com.pga.project1.Intefaces.CallBackLogin;
import com.pga.project1.Structures.Chart;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by aliparsa on 8/9/2014.
 */


public class Webservice {
    //this is sparta

    final static public String SERVER_ADDRESS = "http://192.168.0.152:8099";
    final static public String GET_PROJECTS_URL = SERVER_ADDRESS + "/get_projects";
    final static public String LOGIN_URL = SERVER_ADDRESS + "/login";

    public static void getProjects(final CallBackArraylist<Chart> callBack) {

        CallBackJSON callback_json = new CallBackJSON() {
            @Override
            public void onSuccess(JSONArray result) {
                ArrayList<Chart> chartList = Chart.getArrayFromJson(result);
                callBack.onSuccess(chartList);
            }

            @Override
            public void onError(String errorMessage) {

                callBack.onError(errorMessage

                );
            }
        };
        new AsyncLoad(GET_PROJECTS_URL, callback_json).execute();
    }

    public static void Login(String username, String password, final CallBackLogin callbackLogin) {
        new AsyncLoginByUserPass(LOGIN_URL, username, password, new CallBackAsync<JSONObject>() {
            @Override
            public void onSuccess(JSONObject jsonObject) {
                try {
                    if (jsonObject.has("result"))
                        if (jsonObject.get("result").equals("ok")) {
                            callbackLogin.onSuccess(jsonObject.get("token").toString());
                        } else if (jsonObject.get("result").equals("error")) {
                            callbackLogin.onError(jsonObject.get("error_message").toString());

                        }
                } catch (JSONException e) {

                }
            }

            @Override
            public void onError(JSONObject jsonObject) {

            }
        }).execute();

    }

    public static void GetChildOfID(final int id, final CallBackGetChildOfId callBackGetChildOfId) {

        if (DataCache.get(id + "") != null) {

            callBackGetChildOfId.onSuccess(Chart.getArrayFromJson(DataCache.get(id + "")));
        } else {
            new AsyncGetChildById(GET_PROJECTS_URL, id, new CallBackAsync<JSONArray>() {
                @Override
                public void onSuccess(JSONArray json) {

                    DataCache.set(id + "", json);
                    callBackGetChildOfId.onSuccess(Chart.getArrayFromJson(json));
                }

                @Override
                public void onError(JSONArray json) {

                }
            }).execute();
        }
    }

    public ArrayList<Chart> getChildesById(int id) {
        return null;
    }
}
