package com.pga.project1.Asyncs;

import android.os.AsyncTask;

import com.pga.project1.Intefaces.CallBackJSON;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
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

    private CallBackJSON callBackJson;

    public AsyncLoad(CallBackJSON callBackJson){

        this.callBackJson = callBackJson;
    }

    JSONArray jsonArray;
    public static DefaultHttpClient httpClient;

    @Override
    protected String doInBackground(String... strings) {
        try {

            HttpClient httpclient = new DefaultHttpClient();
            HttpPost httppost = new HttpPost(strings[0]);

            List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(1);

            nameValuePairs.add(new BasicNameValuePair("tag", ""));


            httppost.setEntity(new UrlEncodedFormEntity(
                    nameValuePairs));

            HttpResponse response = httpclient.execute(httppost);

            if (response != null) {
                //TODO
                String json = EntityUtils.toString(response.getEntity());
                jsonArray = new JSONArray(json);

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
        callBackJson.onSuccess(jsonArray);
    }
}
