package com.pga.project1.Intefaces;

import com.pga.project1.Utilities.ErrorMessage;

/**
 * Created by ashkan on 8/11/2014.
 */
public interface ResponseHandler {

    public void handleResponse(String response);

    public void error(ErrorMessage err);
}
