package com.pga.project1.Utilities;

/**
 * Created by ashkan on 8/11/2014.
 */
public class ErrorMessage implements Comparable<ErrorMessage> {


    public static ErrorMessage NO_CONNECTION_ERROR = new ErrorMessage("No Connection", 0);


    //-------------------------------------------------

    private String error;
    private int code;

    public ErrorMessage(String error, int code){

        this.setError(error);
        this.setCode(code);
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    @Override
    public int compareTo(ErrorMessage errorMessage) {
        if(this.code == errorMessage.code){
            return 0;
        }else {
            return 1;
        }
    }
}
