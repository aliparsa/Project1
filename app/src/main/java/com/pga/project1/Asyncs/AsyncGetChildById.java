package com.pga.project1.Asyncs;

import android.os.AsyncTask;
import android.util.Log;

import com.pga.project1.Intefaces.CallBack;
import com.pga.project1.Structures.ErrorPlaceHolder;
import com.pga.project1.Utilities.ErrorMessage;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

/**
 * Created by aliparsa on 8/9/2014.
 */
public class AsyncGetChildById extends AsyncTask<String, String, Integer> {

    public static DefaultHttpClient httpClient;
    // Global variable
    int id;
    String url;
    CallBack callBack;
    JSONArray jsonArray;
    // variables for login
    private String password;
    private String username;

    public AsyncGetChildById(String url, int id, CallBack callBack) {
        this.callBack = callBack;
        this.url = url;
        this.id = id;
    }

    @Override
    protected Integer doInBackground(String... strings) {
        try {


            HttpClient httpclient = new DefaultHttpClient();
            // HttpPost httppost = new HttpPost(strings[0]);
            HttpGet httpGet = new HttpGet(url);
            // List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(1);
            // nameValuePairs.add(new BasicNameValuePair("tag", ""));
            //httppost.setEntity(new UrlEncodedFormEntity( nameValuePairs));
            HttpResponse response = httpclient.execute(httpGet);
            if (response != null) {
                //TODO
                String json = EntityUtils.toString(response.getEntity());
                jsonArray = new JSONArray(json);
            }


        } catch (UnsupportedEncodingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            Log.e("ali", " 11 >> " + e.getMessage());
            return 11;

        } catch (ClientProtocolException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            Log.e("ali", " 12 >> " + e.getMessage());
            return 12;

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            Log.e("ali", " 13 >> " + e.getMessage());
            return 13;

        } catch (JSONException e) {
            e.printStackTrace();
            Log.e("ali", " 14 >> " + e.getMessage());
            return 14;
        }


        return 0;
    }

    @Override
    protected void onPostExecute(Integer result) {
        super.onPostExecute(result);
        try {
            switch (result) {
                case 0:
                    callBack.onSuccess(jsonArray);
                    break;

                case 11:
                    callBack.onError(new ErrorMessage(ErrorPlaceHolder.UnsupportedEncodingException));
                    break;

                case 12:
                    // callBack.onError("AsyncGetChildById > ClientProtocolException");
                    callBack.onError(new ErrorMessage(ErrorPlaceHolder.UnsupportedEncodingException));
                    break;

                case 13:
                    // callBack.onError("AsyncGetChildById > IOException");
                    callBack.onError(new ErrorMessage(ErrorPlaceHolder.UnsupportedEncodingException));
                    break;

                case 14:
                    //callBack.onError("AsyncGetChildById > JSONException");
                    callBack.onError(new ErrorMessage(ErrorPlaceHolder.UnsupportedEncodingException));
                    break;


            }


        } catch (Exception e) {
            Log.e("ali", " 1 >> " + e.getMessage());
        }
    }
}
