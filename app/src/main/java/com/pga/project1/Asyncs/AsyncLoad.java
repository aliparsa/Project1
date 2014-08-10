package com.pga.project1.Asyncs;

import android.os.AsyncTask;

import com.pga.project1.Intefaces.CallBackAsync;
import com.pga.project1.Intefaces.CallBackJSON;
import com.pga.project1.Intefaces.CallBackLogin;

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

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by aliparsa on 8/9/2014.
 */
public class AsyncLoad extends AsyncTask<String, String, String> {

    // Global variable
    String type;
    String url;

    // variables for login
    private String password;
    private String username;
    private CallBackLogin callBackLogin;

    // variables for get projects
    private CallBackJSON callBackJson;


    public AsyncLoad(String url, CallBackJSON callBackJson) {
        this.callBackJson = callBackJson;
        this.url = url;
        this.type = "get_projects";
    }

    public AsyncLoad(String url, String username, String password, CallBackAsync callBackAsync) {
        this.callBackJson = callBackJson;
        this.username = username;
        this.password = password;
        this.url = url;
        this.type = "login";
    }

    JSONArray jsonArray;
    public static DefaultHttpClient httpClient;

    @Override
    protected String doInBackground(String... strings) {
        try {

            if (type.equals("get_projects")) {
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
            }


            if (type.equals("login")) {
                HttpClient httpclient = new DefaultHttpClient();
                HttpPost httppost = new HttpPost(url);
                // HttpGet httpGet = new HttpGet(strings[0]);
                List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(2);
                nameValuePairs.add(new BasicNameValuePair("username", username));
                nameValuePairs.add(new BasicNameValuePair("password", password));
                httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
                HttpResponse response = httpclient.execute(httppost);
                if (response != null) {
                    //TODO
                    String json = EntityUtils.toString(response.getEntity());
                    jsonArray = new JSONArray(json);
                }
            }





        } catch (UnsupportedEncodingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (ClientProtocolException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }


        return null;
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        // TODO Handle error

        callBackJson.onSuccess(jsonArray);
    }
}