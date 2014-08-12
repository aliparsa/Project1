package com.pga.project1.Intefaces;

import com.pga.project1.Utilities.ErrorMessage;

/**
 * Created by aliparsa on 8/10/2014.
 */

public interface CallBack<T> {

    public void onSuccess(T json);

    public void onError(ErrorMessage err);


}
