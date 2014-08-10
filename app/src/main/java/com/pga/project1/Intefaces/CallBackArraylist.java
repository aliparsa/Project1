package com.pga.project1.Intefaces;

import java.util.ArrayList;

/**
 * Created by aliparsa on 8/9/2014.
 */
public interface CallBackArraylist<T> {

    public void onSuccess(ArrayList<T> result);

    public void onError(String errorMessage);


}
