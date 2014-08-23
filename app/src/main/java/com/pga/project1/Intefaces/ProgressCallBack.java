package com.pga.project1.Intefaces;

import com.pga.project1.Utilities.ErrorMessage;

/**
 * Created by aliparsa on 8/10/2014.
 */

public interface ProgressCallBack<T> {

    public void onSuccess(T result);

    public void onError(ErrorMessage err);

    public void onProgress(int done, int total, T result);


}
