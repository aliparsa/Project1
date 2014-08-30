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


    //-----------------------------------------------------------------------------
    public static void getProjects(Context context, final CallBack<ArrayList<Chart>> callBack) {

        HttpHelper helper = new HttpHelper(context, SHAYAN_SERVER_ADDRESS, false, 0);
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

    // DONE
    //-----------------------------------------------------------------------------
    public static void GetChildOfID(Context context, final int id, final CallBack callBack) {

        HttpHelper helper = new HttpHelper(context, SHAYAN_SERVER_ADDRESS, false, 0);
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

    // DONE
    //------------------------------------------------------------------------
    public static void Login(Context context, String username, String password, final CallBack callback) {


        HttpHelper helper = new HttpHelper(context, SHAYAN_SERVER_ADDRESS, false, 0);
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

    // DONE
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

    // DONE
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

    // DONE with error
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

    // DONE
    //-------------------------------------------------------------------------------
    public static void addPersonnelToWork(Context context, int personnelId, int workId, Task task, final CallBack<ServerResponse> callBack) {
        HttpHelper helper = new HttpHelper(context, SHAYAN_SERVER_ADDRESS, false, 0);

        BasicNameValuePair[] arr = {
                new BasicNameValuePair("tag", "add_personnel_to_work"),
                new BasicNameValuePair("personnel_id", personnelId + ""),
                new BasicNameValuePair("chart_id", workId + ""),
                new BasicNameValuePair("name", task.getName()),
                new BasicNameValuePair("budget", task.getPrice()),
                new BasicNameValuePair("start_date", task.getStart_date()),
                new BasicNameValuePair("end_date", task.getEnd_date()),
                new BasicNameValuePair("total_work", task.getKolKar()),
                new BasicNameValuePair("work_unit", task.getVahedKar()),
                new BasicNameValuePair("description", task.getTozihat())

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

    // DONE
    //-------------------------------------------------------------------------------
    public static void saveWorkReport(Context context, final Report report, final String[] imagePaths, final ProgressCallBack callBack) {
        final HttpHelper helper = new HttpHelper(context, SHAYAN_SERVER_ADDRESS, false, 0);

        if (imagePaths != null) {

            int imageCount = 0;
            for (int i = 0; i < imagePaths.length; i++) {

                if (imagePaths[i] != null)
                    imageCount++;
            }

            final String now = new Date().toString();
            for (int i = 0; i < imagePaths.length; i++) {

                final String imagePath = imagePaths[i];
                final int finalImageCount = imageCount;

                if (imagePath != null) {

                    uploadFile(context, imagePath, new CallBackUpload() {

                        @Override
                        public void onSuccess(Object result, String tag) {

                            int uploaded = uploadHelper(tag, (String) result);

                            callBack.onProgress(uploaded, finalImageCount, result);

                            if (uploaded == finalImageCount) {
                                sendMainRequest(tag);
                            }
                        }

                        void sendMainRequest(String tag) {

                            ArrayList<String> imagesOfTag = uploadCountMap.get(tag);
                            String imagesJson = "";
                            for (String imageAddress : imagesOfTag) {

                                imagesJson += imageAddress + ";";
                            }

                            BasicNameValuePair[] arr = {
                                    new BasicNameValuePair("tag", "save_report"),
                                    new BasicNameValuePair("id", report.getChart().getId() + ""),
                                    new BasicNameValuePair("report", report.getReport() + ""),
                                    new BasicNameValuePair("percent_work", report.getPercent() + ""),
                                    new BasicNameValuePair("date", report.getDate() + ""),
                                    new BasicNameValuePair("images", imagesJson)
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
                                    callBack.onError("error 1418");
                                }
                            });
                        }

                        @Override
                        public void onError(String errorMessage) {
                            Log.e("ali", " webservice / saveWorkReport ");
                            callBack.onError("error 1419");
                        }

                    }, now);
                }
            }
        } else {
            BasicNameValuePair[] arr = {
                    new BasicNameValuePair("tag", "save_report"),
                    new BasicNameValuePair("id", report.getChart().getId() + ""),
                    new BasicNameValuePair("report", report.getReport() + ""),
                    new BasicNameValuePair("percent_work", report.getPercent() + ""),
                    new BasicNameValuePair("date", report.getDate() + ""),
                    new BasicNameValuePair("images", "")
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
                    callBack.onError("error 1422");
                }
            });
        }

    }

    //-------------------------------------------------------------------------------
    static Map<String, ArrayList<String>> uploadCountMap = new HashMap<String, ArrayList<String>>();

    private static int uploadHelper(String tag, String imageName) {

        ArrayList<String> list;

        if(uploadCountMap.containsKey(tag)){
            list = uploadCountMap.get(tag);

            list.add(imageName);

        }else{
            list = new ArrayList<String>();
            list.add(imageName);

            uploadCountMap.put(tag, list);
        }

        return list.size();
    }

    //-------------------------------------------------------------------------------
    public static void getWorkUnitList(Context context, final CallBack<ArrayList<WorkUnit>> callBack) {
        HttpHelper helper = new HttpHelper(context, SHAYAN_SERVER_ADDRESS, false, 0);

        BasicNameValuePair[] arr = {
                new BasicNameValuePair("tag", "work_units")
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

    // DONE
    //------------------------------------------------------------------------------
    public static void removeTask(Context context, int task_id, final CallBack<ServerResponse> callBack) {
        HttpHelper helper = new HttpHelper(context, SHAYAN_SERVER_ADDRESS, false, 0);

        BasicNameValuePair[] arr = {
                new BasicNameValuePair("tag", "remove_task"),
                new BasicNameValuePair("id", task_id + ""),
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

    // DONE
    //-------------------------------------------------------------------------------
    public static void uploadFile(Context context, String filePath, final CallBackUpload callBack, final String tag) {
        HttpHelper helper = new HttpHelper(context, SHAYAN_SERVER_ADDRESS, false, 0);

        helper.upload(filePath, new ResponseHandler() {
            @Override
            public void handleResponse(ServerResponse response) {

                try {

                    JSONObject jsonObject = new JSONObject(response.getResult());
                    //TODO get image in server name

                    String result = JsonHelper.getStringS(jsonObject, "result", "");
                    String imageName = "";
                    if (result.equals("ok")) {

                        imageName = JsonHelper.getStringS(jsonObject, "image_address", "");
                    } else {
                        callBack.onError("notSaved");
                    }

                    callBack.onSuccess(imageName, tag);

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