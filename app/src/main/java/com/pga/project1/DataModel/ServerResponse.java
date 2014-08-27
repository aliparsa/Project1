package com.pga.project1.DataModel;

import com.pga.project1.Utilities.HttpStatusCode;

/**
 * Created by aliparsa on 8/18/2014.
 */
public class ServerResponse {

    public ServerResponse(String header, String result, HttpStatusCode statusCode) {
        this.header = header;
        this.result = result;
        this.statusCode = statusCode;
    }

    private String header;
    private String result;
    private HttpStatusCode statusCode;


    /*public static ServerResponse getServerResponse(JSONObject jsonObject) {
        ServerResponse sr = null;
        try {
            sr = new ServerResponse("", jsonObject.getString("result"), jsonObject.getInt("result_code"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sr;

    }*/

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
        return statusCode.getCode();
    }

}
