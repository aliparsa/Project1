package com.pga.project1.Utilities;

import android.content.AsyncTaskLoader;
import android.content.Context;

import com.pga.project1.Intefaces.ResponseHandler;
import com.pga.project1.Structures.ErrorPlaceHolder;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * Created by ashkan on 8/11/2014.
 */
public class HttpHelper {

    private static PriorityQueue<HttpHelper> httpUrls = new PriorityQueue<HttpHelper>();
    private static Map<String, String> responseCache = new HashMap<String, String>();

    private String url;
    private boolean cache;
    private int priority;
    private Context context;

    public HttpHelper(Context context, String url, boolean cache, int priority) {

        this.url = url;
        this.cache = cache;
        this.priority = priority;
        this.context = context;
    }

    public void postHttp(final BasicNameValuePair[] params, final ResponseHandler handler) {

        try {

            if (cache && !responseCache.containsKey(url)) {
                handler.handleResponse(responseCache.get(url));
                return;
            }

            final HttpHelper self = this;

            new AsyncTaskLoader<String>(context) {

                @Override
                public String loadInBackground() {

                    try {
                        HttpClient httpclient = new DefaultHttpClient();
                        HttpPost httppost = new HttpPost(self.url);

                        if (params != null) {
                            List<BasicNameValuePair> basicNameValuePairs = new ArrayList<BasicNameValuePair>(params.length);
                            for (int i = 0; i < params.length; i++) {
                                BasicNameValuePair param = params[i];
                                basicNameValuePairs.add(param);
                            }

                            httppost.setEntity(new UrlEncodedFormEntity(basicNameValuePairs));
                        }

                        HttpResponse response = httpclient.execute(httppost);

//                        if(cache)
//                            cacheResponse(url, response);

                        return EntityUtils.toString(response.getEntity(), HTTP.UTF_8);

                    } catch (UnsupportedEncodingException ue) {
                        ue.printStackTrace();
                        // handler.error(ErrorMessage.NO_CONNECTION_ERROR);
                        handler.error(new ErrorMessage(ErrorPlaceHolder.UnsupportedEncodingException));

                    } catch (ClientProtocolException e) {
                        e.printStackTrace();
                        //handler.error(ErrorMessage.NO_CONNECTION_ERROR);
                        handler.error(new ErrorMessage(ErrorPlaceHolder.UnsupportedEncodingException));

                    } catch (IOException e) {
                        e.printStackTrace();
                        //handler.error(ErrorMessage.NO_CONNECTION_ERROR);
                        handler.error(new ErrorMessage(ErrorPlaceHolder.UnsupportedEncodingException));

                    }

                    return null;
                }

                @Override
                public void deliverResult(String data) {

                    if (data != null) {

                        handler.handleResponse(data);

                        super.deliverResult(data);

                    } else {

                        handler.error(new ErrorMessage(ErrorPlaceHolder.UnsupportedEncodingException));
                    }
                }
            }.forceLoad();

        } catch (Exception e) {
            e.printStackTrace();

            handler.error(new ErrorMessage(ErrorPlaceHolder.UnsupportedEncodingException));
        }
    }

    public void getHttp(final ResponseHandler handler) {

        try {


            if (cache && !responseCache.containsKey(url)) {
                handler.handleResponse(responseCache.get(url));
                return;
            }

            new AsyncTaskLoader<String>(context) {

                @Override
                public String loadInBackground() {

                    try {

                        HttpClient httpclient = new DefaultHttpClient();
                        HttpGet httpGet = new HttpGet(url);

                        HttpResponse response = httpclient.execute(httpGet);


//                        if(cache)
//                            cacheResponse(url, response);

                        return EntityUtils.toString(response.getEntity(), HTTP.UTF_8);

                    } catch (UnsupportedEncodingException ue) {
                        ue.printStackTrace();
                        handler.error(new ErrorMessage(ErrorPlaceHolder.UnsupportedEncodingException));
                    } catch (ClientProtocolException e) {
                        e.printStackTrace();
                        handler.error(new ErrorMessage(ErrorPlaceHolder.UnsupportedEncodingException));
                    } catch (IOException e) {
                        e.printStackTrace();
                        handler.error(new ErrorMessage(ErrorPlaceHolder.UnsupportedEncodingException));
                    }

                    return null;
                }

                @Override
                public void deliverResult(String data) {

                    if (data != null) {

                        handler.handleResponse(data);

                        super.deliverResult(data);
                    } else {

                        handler.error(new ErrorMessage(ErrorPlaceHolder.UnsupportedEncodingException));
                    }
                }
            }.forceLoad();

        } catch (Exception e) {
            e.printStackTrace();

            handler.error(new ErrorMessage(ErrorPlaceHolder.UnsupportedEncodingException));
        }
    }

    public void getPostSerial(final BasicNameValuePair[] params, final ResponseHandler handler) {

        try {

            if (cache && !responseCache.containsKey(url)) {
                handler.handleResponse(responseCache.get(url));
                return;
            }

            // httpUrls.
            //sortUrls();

            if (httpUrls.size() > 0) {

                //HttpHelper helper = httpUrls.po
            }


        } catch (Exception e) {


        }
    }

    private void sortUrls() {
/*
        Collections.sort(httpUrls, new Comparator<HttpHelper>() {
            @Override
            public int compare(HttpHelper s, HttpHelper s2) {
                if(s.priority > s2.priority){
                    return 1;
                }else if(s.priority == s2.priority){
                    return 1;
                }else{
                    return -1;
                }
            }
        });
        */
    }

    private void cacheResponse(String url, String response) {

        if (!responseCache.containsKey(url))
            responseCache.put(url, response);
    }
}
