package com.pga.project1.Helpers;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.widget.ArrayAdapter;

import com.pga.project1.Activities.LoginActivity;
import com.pga.project1.DataModel.Chart;
import com.pga.project1.DataModel.Faliat;
import com.pga.project1.DataModel.ServerResponse;
import com.pga.project1.DataModel.Taradod;
import com.pga.project1.DataModel.Work;
import com.pga.project1.Intefaces.CallBack;
import com.pga.project1.R;
import com.pga.project1.Utilities.Account;
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

    public static void SyncWork(final Context context) {
        final DatabaseHelper db = new DatabaseHelper(context);
        Webservice.getWorks(context, new CallBack<ArrayList<Work>>() {
            @Override
            public void onSuccess(ArrayList<Work> result) {

                if (result.size() > 0) {
                    db.emptyWorkTable();
                    for (Work work : result) {
                        db.insertWork(work);
                    }
                }
            }

            @Override
            public void onError(String errorMessage) {
                if (errorMessage.equals("UNAUTHORIZED")) {

                    // clear token
                    Account.getInstant(context).clearToken();

                    // pass user to login page
                    Intent intent = new Intent(context, LoginActivity.class);
                    intent.putExtra("reason", "UNAUTHORIZED");
                    context.startActivity(intent);
                    ((Activity) context).overridePendingTransition(R.anim.activity_fade_in_animation, R.anim.activity_fade_out_animation);
                }
            }
        });
    }

    public static void SyncProject(final Context context) {
        final DatabaseHelper db = new DatabaseHelper(context);

        Webservice.getProjects(context, new CallBack<ArrayList<Chart>>() {
            @Override
            public void onSuccess(ArrayList<Chart> result) {
                db.emptyProjectsTable();
                for (Chart chart : result) {
                    db.insertProject(chart);
                }
            }

            @Override
            public void onError(String errorMessage) {
                if (errorMessage.equals("UNAUTHORIZED")) {

                    // clear token
                    Account.getInstant(context).clearToken();

                    // pass user to login page
                    Intent intent = new Intent(context, LoginActivity.class);
                    intent.putExtra("reason", "UNAUTHORIZED");
                    context.startActivity(intent);
                    ((Activity) context).overridePendingTransition(R.anim.activity_fade_in_animation, R.anim.activity_fade_out_animation);
                }
            }
        });
    }
}
