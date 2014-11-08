package com.pga.project1.Helpers;

import android.content.Context;
import android.widget.ArrayAdapter;

import com.pga.project1.DataModel.Faliat;
import com.pga.project1.DataModel.ServerResponse;
import com.pga.project1.DataModel.Taradod;
import com.pga.project1.Intefaces.CallBack;
import com.pga.project1.Utilities.Webservice;

import java.util.ArrayList;

/**
 * Created by aliparsa on 11/8/2014.
 */
public class FastProjectSyncHelper {


    public static void SyncTaradod(Context context) {
        DatabaseHelper db = new DatabaseHelper(context);
        ArrayList<Taradod> taradods = db.getAllUnsentTaradod();
        if (taradods.size() > 0)
            Webservice.sendTaradod(context, taradods, new CallBack<ServerResponse>() {
                @Override
                public void onSuccess(ServerResponse result) {
                    // TODO MAKE RECORDS SEND FLAG TRUE
                }

                @Override
                public void onError(String errorMessage) {

                }
            });
    }

    public static void SyncFaliat(Context context) {
        DatabaseHelper db = new DatabaseHelper(context);
        ArrayList<Faliat> faliats = db.getAllUnsentFaliat();
        if (faliats.size() > 0)
            Webservice.sendFaliat(context, faliats, new CallBack<ServerResponse>() {
                @Override
                public void onSuccess(ServerResponse result) {
                    // TODO MAKE RECORDS SEND FLAG TRUE
                }

                @Override
                public void onError(String errorMessage) {

                }
            });
    }
}
