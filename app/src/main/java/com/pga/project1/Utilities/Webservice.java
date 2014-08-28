package com.pga.project1.Utilities;

import android.content.Context;
import android.util.Log;

import com.pga.project1.DataModel.Chart;
import com.pga.project1.DataModel.Personnel;
import com.pga.project1.DataModel.Report;
import com.pga.project1.DataModel.ServerResponse;
import com.pga.project1.DataModel.Task;
import com.pga.project1.DataModel.WorkUnit;
import com.pga.project1.Intefaces.CallBack;
import com.pga.project1.Intefaces.CallBackUpload;
import com.pga.project1.Intefaces.ProgressCallBack;
import com.pga.project1.Intefaces.ResponseHandler;
import com.pga.project1.Structures.ErrorPlaceHolder;

import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by aliparsa on 8/9/2014.
 */


public class Webservice {
    //this is sparta

    final static public String SERVER_ADDRESS = "http://192.168.1.66:8099/pm";
    final static public String SHAYAN_SERVER_ADDRESS = "http://192.168.0.79:3434/index.php/webservice?";
    final static public String SERVER_ADDRESS_UPLOAD = "http://192.168.0.79:3434/index.php/w-upload";


    //-----------------------------------------------------------------------------
    public static void getProjects(Context context, final CallBack<ArrayList<Chart>> callBack) {

        HttpHelper helper = new HttpHelper(context, SERVER_ADDRESS, false, 0);
        //HttpHelper helper = new HttpHelper(context, SERVER_ADDRESS, false, 0);


        BasicNameValuePair[] arr = {
                new BasicNameValuePair("tag", "get_projects")
        };
        helper.postHttp(arr, new ResponseHandler() {
            @Override
            public void handleResponse(ServerResponse response) {

                try {


                    switch (response.getStatusCode()) {
                        case SC_UNAUTHORIZED: {
                            callBack.onError("UNAUTHORIZED");
                            break;
                        }
                        case SC_OK: {
                            JSONArray jsonArray = new JSONArray(response.getResult());
                            ArrayList<Chart> chartList = Chart.getArrayFromJson(jsonArray);
                            callBack.onSuccess(chartList);
                            break;
                        }


                    }


                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void error(String err) {

                Log.d("ali", "Error");
                callBack.onError(err);
            }
        });
    }

    //-----------------------------------------------------------------------------
    public static void GetChildOfID(Context context, final int id, final CallBack callBack) {

        HttpHelper helper = new HttpHelper(context, SERVER_ADDRESS, false, 0);
        //HttpHelper helper = new HttpHelper(context, SERVER_ADDRESS, false, 0);

        BasicNameValuePair[] arr = {
                new BasicNameValuePair("tag", "get_child_of_id"),
                new BasicNameValuePair("id", id + "")
        };
        helper.postHttp(arr, new ResponseHandler() {
            @Override
            public void handleResponse(ServerResponse response) {

                try {


                    switch (response.getStatusCode()) {
                        case SC_UNAUTHORIZED: {
                            callBack.onError("UNAUTHORIZED");
                            break;
                        }
                        case SC_OK: {
                            JSONArray jsonArray = new JSONArray(response.getResult());
                            ArrayList<Chart> chartList = Chart.getArrayFromJson(jsonArray);
                            callBack.onSuccess(chartList);
                            break;
                        }


                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void error(String err) {

            }
        });

    }

    //------------------------------------------------------------------------
    public static void Login(Context context, String username, String password, final CallBack callback) {


        HttpHelper helper = new HttpHelper(context, SERVER_ADDRESS, false, 0);
        //HttpHelper helper = new HttpHelper(context, SERVER_ADDRESS, false, 0);

        BasicNameValuePair[] arr = {
                new BasicNameValuePair("tag", "login"),
                new BasicNameValuePair("username", "admin"),
                new BasicNameValuePair("password", "admin")
        };

        helper.postHttp(arr, new ResponseHandler() {
            @Override
            public void handleResponse(ServerResponse response) {

                try {


                    JSONObject jsonObject = new JSONObject(response.getResult());

                    if (jsonObject.has("result")) {
                        if (jsonObject.get("result").equals("ok")) {
                            callback.onSuccess(jsonObject.get("token").toString());
                        } else if (jsonObject.get("result").equals("error")) {

                            callback.onError("err7");
                        }
                    } else {
                        callback.onError("err8");
                    }


//                    ArrayList<Chart> chartList = Chart.getArrayFromJson(jsonArray);
//                    callback.onSuccess(chartList);

                } catch (JSONException e) {
                    e.printStackTrace();

                    callback.onError("err9");

                } catch (Exception e) {
                    e.printStackTrace();

                    callback.onError("err10");
                }


            }

            @Override
            public void error(String err) {
                callback.onError("err11");
            }
        });

    }
    //-------------------------------------------------------------------------------
//    public static void getFeatureById(Context context, int id, final CallBack callback) {
//
//// HttpHelper helper = new HttpHelper(context, SHAYAN_SERVER_ADDRESS, false, 0);
//        HttpHelper helper = new HttpHelper(context, SERVER_ADDRESS, false, 0);
//
//        BasicNameValuePair[] arr = {
//                new BasicNameValuePair("tag", "get_chart_feature"),
//                new BasicNameValuePair("id", id + "")
//        };
//
//        helper.postHttp(arr, new ResponseHandler() {
//            @Override
//            public void handleResponse(ServerResponse response) {
//
//                try {
//
//
//                    JSONArray jsonArray = new JSONArray(response.getResult());
//
//                    ArrayList<Feature> featureList = Feature.getArrayFromJson(jsonArray);
//                    callback.onSuccess(featureList);
//
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//
//
//            }
//
//            @Override
//            public void error(ErrorMessage err) {
//
//            }
//        });
//    }

    //-------------------------------------------------------------------------------
    public static void getTaskListByWorkId(Context context, final int id, final CallBack callBack) {

        HttpHelper helper = new HttpHelper(context, SHAYAN_SERVER_ADDRESS, false, 0);

        BasicNameValuePair[] arr = {
                new BasicNameValuePair("tag", "get_task_of_id"),
                new BasicNameValuePair("id", id + "")
        };
        helper.postHttp(arr, new ResponseHandler() {
            @Override
            public void handleResponse(ServerResponse response) {

                try {

                    JSONArray jsonArray = new JSONArray(response.getResult());

                    ArrayList<Chart> chartList = Chart.getArrayFromJson(jsonArray);
                    callBack.onSuccess(chartList);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void error(String err) {

            }
        });

    }

    //-------------------------------------------------------------------------------
    public static void searchPersonnel(Context context, String str, final CallBack<ArrayList<Personnel>> callBack) {

        HttpHelper helper = new HttpHelper(context, SHAYAN_SERVER_ADDRESS, false, 0);

        BasicNameValuePair[] arr = {
                new BasicNameValuePair("tag", "search_personnel"),
                new BasicNameValuePair("query", str)
        };
        helper.postHttp(arr, new ResponseHandler() {
            @Override
            public void handleResponse(ServerResponse response) {

                try {


                    JSONArray jsonArray = new JSONArray(response.getResult());
                    ArrayList<Personnel> perList = Personnel.getArrayFromJson(jsonArray);


                    callBack.onSuccess(perList);

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void error(String err) {

            }
        });
    }

    // TODO WOROG ANSWER FROM SERVER
    //-------------------------------------------------------------------------------
    public static void getReportListByWorkId(Context context, int id, final CallBack callBack) {
        HttpHelper helper = new HttpHelper(context, SHAYAN_SERVER_ADDRESS, false, 0);

        BasicNameValuePair[] arr = {
                new BasicNameValuePair("tag", "get_report_of_id"),
                new BasicNameValuePair("id", id + "")
        };
        helper.postHttp(arr, new ResponseHandler() {
            @Override
            public void handleResponse(ServerResponse response) {

                try {

                    JSONArray jsonArray = new JSONArray(response.getResult());

                    ArrayList<Report> reportList = Report.getArrayFromJson(jsonArray);

                    callBack.onSuccess(reportList);

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void error(String err) {
                Log.e("ali", " webservice / getReportListByWorkId ");

            }
        });

    }

    //-------------------------------------------------------------------------------
    public static void addPersonnelToWork(Context context, int personnelId, int workId, Task task, final CallBack<ServerResponse> callBack) {
        HttpHelper helper = new HttpHelper(context, SERVER_ADDRESS, false, 0);

        BasicNameValuePair[] arr = {
                new BasicNameValuePair("tag", "add_personnel_to_work"),
                new BasicNameValuePair("personnel_id", personnelId + ""),
                new BasicNameValuePair("work_id", workId + ""),
                new BasicNameValuePair("name", task.getName()),
                new BasicNameValuePair("price", task.getPrice()),
                new BasicNameValuePair("start_date", task.getStart_date()),
                new BasicNameValuePair("end_date", task.getEnd_date()),
                new BasicNameValuePair("kol_kar", task.getKolKar()),
                new BasicNameValuePair("vahed_kar", task.getVahedKar()),
                new BasicNameValuePair("tozihat", task.getTozihat())


        };
        helper.postHttp(arr, new ResponseHandler() {
            @Override
            public void handleResponse(ServerResponse response) {

                try {

                    JSONObject jsonObject = new JSONObject(response.getResult());

                    callBack.onSuccess(response);

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void error(String err) {
                Log.e("ali", " webservice / addPersonnelToWork ");
            }
        });

    }

    //-------------------------------------------------------------------------------
    public static void saveWorkReport(Context context, final Report report, final String[] imagePaths, final ProgressCallBack callBack) {
        final HttpHelper helper = new HttpHelper(context, SERVER_ADDRESS, false, 0);


        final String now = new Date().toString();
        for (int i = 0; i < imagePaths.length; i++) {

            final String imagePath = imagePaths[i];

            uploadFile(context, imagePath, new CallBackUpload() {

                @Override
                public void onSuccess(Object result, String tag) {

                    int uploaded = uploadHelper(tag);

                    if(uploaded == imagePath.length())
                    {
                        sendMainRequest();
                    }
                }

                void sendMainRequest(){
                    BasicNameValuePair[] arr = {
                            new BasicNameValuePair("tag", "save_work_report"),
                            new BasicNameValuePair("chart_id", report.getChart().getId() + ""),
                            new BasicNameValuePair("report_text", report.getReport() + ""),
                            new BasicNameValuePair("report_percent", report.getPercent() + ""),
                            new BasicNameValuePair("report_date", report.getDate() + "")

                    };

                    helper.postHttp(arr, new ResponseHandler() {
                        @Override
                        public void handleResponse(ServerResponse response) {

                            try {

                                JSONObject jsonObject = new JSONObject(response.getResult());

                                callBack.onSuccess(response);

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }

                        }

                        @Override
                        public void error(String err) {
                            Log.e("ali", " webservice / saveWorkReport ");
                            callBack.onError(new ErrorMessage(ErrorPlaceHolder.err2));
                        }
                    });
                }

                @Override
                public void onError(String errorMessage) {
                    Log.e("ali", " webservice / saveWorkReport ");
                    callBack.onError(new ErrorMessage(ErrorPlaceHolder.err2));
                }

            }, now);
        }

    }

    static Map<String, Integer> uploadCountMap = new HashMap<String, Integer>();

    private static int uploadHelper(String tag) {

        int count = 1;

        if(uploadCountMap.containsKey(tag)){
            count = uploadCountMap.get(tag);
            uploadCountMap.remove(tag);
            count++;
            uploadCountMap.put(tag, count);
        }else{
            uploadCountMap.put(tag, count);
        }

        return count;
    }

    //-------------------------------------------------------------------------------
    public static void getWorkUnitList(Context context, final CallBack<ArrayList<WorkUnit>> callBack) {
        HttpHelper helper = new HttpHelper(context, SERVER_ADDRESS, false, 0);

        BasicNameValuePair[] arr = {
                new BasicNameValuePair("tag", "work_unit")
        };

        helper.postHttp(arr, new ResponseHandler() {
            @Override
            public void handleResponse(ServerResponse response) {

                try {

                    JSONArray jsonArray = new JSONArray(response.getResult());

                    ArrayList<WorkUnit> reportList = WorkUnit.getArrayFromJson(jsonArray);

                    callBack.onSuccess(reportList);

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void error(String err) {
                Log.e("ali", " webservice / getReportListByWorkId ");

            }
        });

    }

    //------------------------------------------------------------------------------
    public static void removeTask(Context context, int task_id, final CallBack<ServerResponse> callBack) {
        HttpHelper helper = new HttpHelper(context, SERVER_ADDRESS, false, 0);

        BasicNameValuePair[] arr = {
                new BasicNameValuePair("tag", "remove_task"),
                new BasicNameValuePair("task_id", task_id + ""),
        };
        helper.postHttp(arr, new ResponseHandler() {
            @Override
            public void handleResponse(ServerResponse response) {

                try {

                    JSONObject jsonObject = new JSONObject(response.getResult());

                    callBack.onSuccess(response);

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void error(String err) {
                Log.e("ali", " webservice / addPersonnelToWork ");
            }
        });

    }

    //-------------------------------------------------------------------------------
    public static void uploadFile(Context context, String filePath, final CallBackUpload callBack, final String tag) {
        HttpHelper helper = new HttpHelper(context, SERVER_ADDRESS_UPLOAD, false, 0);

        helper.upload(filePath, new ResponseHandler() {
            @Override
            public void handleResponse(ServerResponse response) {

                try {

                    JSONObject jsonObject = new JSONObject(response.getResult());

                    callBack.onSuccess(response, tag);

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void error(String err) {
                Log.e("ali", " webservice / saveWorkReport ");
                callBack.onError("err12");
            }
        });

    }

}