package com.pga.project1.Utilities;

import com.pga.project1.Structures.ErrorPlaceHolder;

/**
 * Created by ashkan on 8/11/2014.
 */
public class ErrorMessage {


//    public static String NO_CONNECTION_ERROR =  "No Connection";
//    public static String UNSUPPORTED_ENCODING = "UnsupportedEncodingException";

    //-------------------------------------------------UnsupportedEncodingException
    ErrorPlaceHolder err;
    private Exception e;
    private Object errSender;

    public ErrorMessage(ErrorPlaceHolder err) {

        this.err = err;

    }

    public Object getErrSender() {
        return errSender;
    }

    public void setErrSender(Object errSender) {
        this.errSender = errSender;
    }

}
