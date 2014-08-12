package com.pga.project1.Asyncs;

import android.os.AsyncTask;
import android.util.Log;

import com.pga.project1.Intefaces.CallBackAsync;
import com.pga.project1.Utilities.Account;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by aliparsa on 8/9/2014.
 */
public class AsyncGetChildById extends AsyncTask<String, String, Integer> {

    // Global variable
    int id;
    String url;

    // variables for login
    private String password;
    private String username;
    CallBackAsync callBackAsync;


    public AsyncGetChildById(String url, int id, CallBackAsync callBackAsync) {
        this.callBackAsync = callBackAsync;
        this.url = url;
        this.id = id;
    }

    JSONArray jsonArray;

    public static DefaultHttpClient httpClient;


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
                    callBackAsync.onSuccess(jsonArray);
                    break;

                case 11:
                    callBackAsync.onError("AsyncGetChildById > UnsupportedEncodingException");
                    break;

                case 12:
                    callBackAsync.onError("AsyncGetChildById > ClientProtocolException");
                    break;

                case 13:
                    callBackAsync.onError("AsyncGetChildById > IOException");
                    break;

                case 14:
                    callBackAsync.onError("AsyncGetChildById > JSONException");
                    break;


            }


        } catch (Exception e) {
            Log.e("ali", " 1 >> " + e.getMessage());
        }
    }
}
