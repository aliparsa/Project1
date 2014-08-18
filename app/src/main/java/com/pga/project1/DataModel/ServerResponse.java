package com.pga.project1.DataModel;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * Created by aliparsa on 8/18/2014.
 */
public class ServerResponse {

    public ServerResponse(String header, String result, int result_code) {
        this.header = header;
        this.result = result;
        this.result_code = result_code;
    }

    private String header;
    private String result;
    private int result_code;


    public static ServerResponse getServerResponse(JSONObject jsonObject) {
        ServerResponse sr = null;
        try {
            sr = new ServerResponse("", jsonObject.getString("result"), jsonObject.getInt("result_code"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sr;

    }

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public int getResult_code() {
        return result_code;
    }

    public void setResult_code(int result_code) {
        this.result_code = result_code;
    }
}
