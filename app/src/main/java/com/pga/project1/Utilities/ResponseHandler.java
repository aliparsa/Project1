package com.pga.project1.Utilities;

import org.apache.http.HttpResponse;

/**
 * Created by ashkan on 8/11/2014.
 */
public interface ResponseHandler {

    public void handleResponse(HttpResponse response);
    public void error(ErrorMessage err);
}
