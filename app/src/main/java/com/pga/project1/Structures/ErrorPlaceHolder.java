package com.pga.project1.Structures;

/**
 * Created by aliparsa on 8/12/2014.
 */
public class ErrorPlaceHolder {
    public static ErrorPlaceHolder UnsupportedEncodingException = new ErrorPlaceHolder("UnsupportedEncodingException", 1);
    public static ErrorPlaceHolder err2 = new ErrorPlaceHolder("err1", 1);
    public static ErrorPlaceHolder err3 = new ErrorPlaceHolder("err1", 1);
    public static ErrorPlaceHolder err4 = new ErrorPlaceHolder("err1", 1);
    public static ErrorPlaceHolder err5 = new ErrorPlaceHolder("err1", 1);
    public static ErrorPlaceHolder err6 = new ErrorPlaceHolder("err1", 1);
    public static ErrorPlaceHolder err7 = new ErrorPlaceHolder("err1", 1);

    private String errMessage;
    private int errNumber;

    public ErrorPlaceHolder(String errMessage, int errNumber) {
        this.errMessage = errMessage;
        this.errNumber = errNumber;
    }

}
