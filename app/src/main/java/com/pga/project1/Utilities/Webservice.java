package com.pga.project1.Utilities;

import android.content.Context;
import android.util.Log;

import com.pga.project1.DataModel.Chart;
import com.pga.project1.DataModel.Feature;
import com.pga.project1.DataModel.Personnel;
import com.pga.project1.DataModel.Report;
import com.pga.project1.DataModel.ServerResponse;
import com.pga.project1.Intefaces.CallBack;
import com.pga.project1.Intefaces.ProgressCallBack;
import com.pga.project1.Intefaces.ResponseHandler;
import com.pga.project1.Structures.ErrorPlaceHolder;

import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by aliparsa on 8/9/2014.
 */


public class Webservice {
    //this is sparta

    final static public String SERVER_ADDRESS = "http://192.168.1.66:8099/pm";
    final static public String SERVER_ADDRESS_UPLOAD = "http://192.168.0.79:3434/index.php/w-upload";

    //-----------------------------------------------------------------------------
    public static void getProjects(Context context, final CallBack<ArrayList<Chart>> callBack) {

        HttpHelper helper = new HttpHelper(context, SERVER_ADDRESS, false, 0);

        BasicNameValuePair[] arr = {
                new BasicNameValuePair("tag", "get_projects")
        };
        helper.postHttp(arr, new ResponseHandler() {
            @Override
            public void handleResponse(String response) {

                try {

                    JSONArray jsonArray = new JSONArray(response);

                    ArrayList<Chart> chartList = Chart.getArrayFromJson(jsonArray);
                    callBack.onSuccess(chartList);

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void error(ErrorMessage err) {
                Log.d("ali", "Error");
            }
        });
    }

    //-----------------------------------------------------------------------------
    public static void GetChildOfID(Context context, final int id, final CallBack callBack) {

        HttpHelper helper = new HttpHelper(context, SERVER_ADDRESS, false, 0);

        BasicNameValuePair[] arr = {
                new BasicNameValuePair("tag", "get_projects")
        };
        helper.postHttp(arr, new ResponseHandler() {
            @Override
            public void handleResponse(String response) {

                try {


                    JSONArray jsonArray = new JSONArray(response);

                    ArrayList<Chart> chartList = Chart.getArrayFromJson(jsonArray);
                    callBack.onSuccess(chartList);

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void error(ErrorMessage err) {

            }
        });

    }

    //------------------------------------------------------------------------
    public static void Login(Context context, String username, String password, final CallBack callback) {


        HttpHelper helper = new HttpHelper(context, SERVER_ADDRESS, false, 0);

        BasicNameValuePair[] arr = {
                new BasicNameValuePair("tag", "login"),
                new BasicNameValuePair("username", username),
                new BasicNameValuePair("password", password)
        };

        helper.postHttp(arr, new ResponseHandler() {
            @Override
            public void handleResponse(String response) {

                try {


                    JSONObject jsonObject = new JSONObject(response);

                    if (jsonObject.has("result"))
                        if (jsonObject.get("result").equals("ok")) {
                            callback.onSuccess(jsonObject.get("token").toString());
                        } else if (jsonObject.get("result").equals("error")) {

                            callback.onError(new ErrorMessage(ErrorPlaceHolder.err2));
                        }


//                    ArrayList<Chart> chartList = Chart.getArrayFromJson(jsonArray);
//                    callback.onSuccess(chartList);

                } catch (JSONException e) {
                    e.printStackTrace();
                } catch (Exception e) {
                    e.printStackTrace();
                }


            }

            @Override
            public void error(ErrorMessage err) {

            }
        });

    }

    //-------------------------------------------------------------------------------
    public static void getFeatureById(Context context, int id, final CallBack callback) {

        HttpHelper helper = new HttpHelper(context, SERVER_ADDRESS, false, 0);

        BasicNameValuePair[] arr = {
                new BasicNameValuePair("tag", "get_feature"),
                new BasicNameValuePair("id", id + "")
        };

        helper.postHttp(arr, new ResponseHandler() {
            @Override
            public void handleResponse(String response) {

                try {


                    JSONArray jsonArray = new JSONArray(response);

                    ArrayList<Feature> featureList = Feature.getArrayFromJson(jsonArray);
                    callback.onSuccess(featureList);

                } catch (JSONException e) {
                    e.printStackTrace();
                } catch (Exception e) {
                    e.printStackTrace();
                }


            }

            @Override
            public void error(ErrorMessage err) {

            }
        });
    }

    //-------------------------------------------------------------------------------
    public static void getTaskListByWorkId(Context context, final int id, final CallBack callBack) {

        HttpHelper helper = new HttpHelper(context, SERVER_ADDRESS, false, 0);

        BasicNameValuePair[] arr = {
                new BasicNameValuePair("tag", "get_task_list"),
                new BasicNameValuePair("id", id + "")
        };
        helper.postHttp(arr, new ResponseHandler() {
            @Override
            public void handleResponse(String response) {

                try {

                    JSONArray jsonArray = new JSONArray(response);

                    ArrayList<Chart> chartList = Chart.getArrayFromJson(jsonArray);
                    callBack.onSuccess(chartList);

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void error(ErrorMessage err) {

            }
        });

    }

    //-------------------------------------------------------------------------------
    public static void searchPersonnel(Context context, String str, final CallBack<ArrayList<Personnel>> callBack) {

        HttpHelper helper = new HttpHelper(context, SERVER_ADDRESS, false, 0);

        BasicNameValuePair[] arr = {
                new BasicNameValuePair("tag", "search_personnel"),
                new BasicNameValuePair("query", str)
        };
        helper.postHttp(arr, new ResponseHandler() {
            @Override
            public void handleResponse(String response) {

                try {


                    JSONArray jsonArray = new JSONArray(response);
                    ArrayList<Personnel> perList = Personnel.getArrayFromJson(jsonArray);


                    callBack.onSuccess(perList);

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void error(ErrorMessage err) {

            }
        });
    }

    //-------------------------------------------------------------------------------
    public static void getReportListByWorkId(Context context, int id, final CallBack callBack) {
        HttpHelper helper = new HttpHelper(context, SERVER_ADDRESS, false, 0);

        BasicNameValuePair[] arr = {
                new BasicNameValuePair("tag", "get_report_list"),
                new BasicNameValuePair("id", id + "")
        };
        helper.postHttp(arr, new ResponseHandler() {
            @Override
            public void handleResponse(String response) {

                try {

                    JSONArray jsonArray = new JSONArray(response);

                    ArrayList<Report> reportList = Report.getArrayFromJson(jsonArray);

                    callBack.onSuccess(reportList);

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void error(ErrorMessage err) {
                Log.e("ali", " webservice / getReportListByWorkId ");

            }
        });

    }

    //-------------------------------------------------------------------------------
    public static void addPersonnelToWork(Context context, int personnelId, int workId, final CallBack callBack) {
        HttpHelper helper = new HttpHelper(context, SERVER_ADDRESS, false, 0);

        BasicNameValuePair[] arr = {
                new BasicNameValuePair("tag", "add_personnel_to_work"),
                new BasicNameValuePair("personnel_id", personnelId + ""),
                new BasicNameValuePair("work_id", workId + "")
        };
        helper.postHttp(arr, new ResponseHandler() {
            @Override
            public void handleResponse(String response) {

                try {

                    JSONObject jsonObject = new JSONObject(response);

                    ServerResponse sr = ServerResponse.getServerResponse(jsonObject);

                    callBack.onSuccess(sr);

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void error(ErrorMessage err) {
                Log.e("ali", " webservice / addPersonnelToWork ");
            }
        });

    }

    //-------------------------------------------------------------------------------
    public static void saveWorkReport(Context context, Report report, String[] imagePaths, final ProgressCallBack callBack) {
        HttpHelper helper = new HttpHelper(context, SERVER_ADDRESS, false, 0);


        int i = 0;
        if (imagePaths != null && imagePaths.length > 0) {

            String imgPath = imagePaths[i];

            uploadFile(context, imgPath, new CallBack() {
                @Override
                public void onSuccess(Object result) {
                    //i++;
                }

                @Override
                public void onError(ErrorMessage err) {

                }
            });
        }

        BasicNameValuePair[] arr = {
                new BasicNameValuePair("tag", "save_work_report"),
                new BasicNameValuePair("chart_id", report.getChart().getId() + ""),
                new BasicNameValuePair("report_text", report.getReport() + ""),
                new BasicNameValuePair("report_percent", report.getPercent() + ""),
                new BasicNameValuePair("report_date", report.getDate() + "")

        };

        helper.postHttp(arr, new ResponseHandler() {
            @Override
            public void handleResponse(String response) {

                try {

                    JSONObject jsonObject = new JSONObject(response);

                    ServerResponse sr = ServerResponse.getServerResponse(jsonObject);

                    callBack.onSuccess(sr);

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void error(ErrorMessage err) {
                Log.e("ali", " webservice / saveWorkReport ");
                callBack.onError(new ErrorMessage(ErrorPlaceHolder.err2));
            }
        });

    }

    //-------------------------------------------------------------------------------
    public static void uploadFile(Context context, String filePath, final CallBack callBack) {
        HttpHelper helper = new HttpHelper(context, SERVER_ADDRESS_UPLOAD, false, 0);

        helper.upload(filePath, new ResponseHandler() {
            @Override
            public void handleResponse(String response) {

                try {

                    JSONObject jsonObject = new JSONObject(response);

                    ServerResponse sr = ServerResponse.getServerResponse(jsonObject);

                    callBack.onSuccess(sr);

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void error(ErrorMessage err) {
                Log.e("ali", " webservice / saveWorkReport ");
                callBack.onError(new ErrorMessage(ErrorPlaceHolder.err2));
            }
        });

    }

}