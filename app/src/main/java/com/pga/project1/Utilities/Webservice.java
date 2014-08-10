package com.pga.project1.Utilities;

<<<<<<< HEAD
import com.pga.project1.Intefaces.CallBack;
import com.pga.project1.Structures.Chart;

/**
 * Created by ashkan on 8/10/2014.
 */
public class Webservice {
    public void getProjects(CallBack<Chart> callBack) {
    }
=======
import com.pga.project1.Asyncs.AsyncLoad;
import com.pga.project1.Intefaces.CallBack;
import com.pga.project1.Intefaces.CallBackJSON;
import com.pga.project1.Structures.Chart;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by aliparsa on 8/9/2014.
 */


public class Webservice {
    //this is sparta

    final public String SERVER_ADDRESS = "http://192.168.0.152:8099";
    final public String GET_PROJECTS_URL = SERVER_ADDRESS+"/get_projects.json";

    public void getProjects(final CallBack<Chart> callBack){
        CallBackJSON  callback_json = new CallBackJSON() {
            @Override
            public void onSuccess(JSONArray result) {
                ArrayList<Chart> chartList =  Chart.getArrayFromJson(result);
                callBack.onSuccess(chartList);
            }

            @Override
            public void onError(String errorMessage) {

                callBack.onError(errorMessage

                );
            }
        } ;
        new AsyncLoad(callback_json).execute(GET_PROJECTS_URL);
    }

    public ArrayList<Chart> getChildesById(int id){
        return null;
    }

>>>>>>> origin/master
}
