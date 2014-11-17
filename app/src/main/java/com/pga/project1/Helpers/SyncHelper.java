package com.pga.project1.Helpers;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import com.pga.project1.Activities.AnbarActivity;
import com.pga.project1.Activities.FastProjectManagmentActivity;
import com.pga.project1.Activities.LoginActivity;
import com.pga.project1.DataModel.Anbar;
import com.pga.project1.DataModel.AnbarTransaction;
import com.pga.project1.DataModel.Chart;
import com.pga.project1.DataModel.Faliat;
import com.pga.project1.DataModel.TaminKonande;
import com.pga.project1.DataModel.Product;
import com.pga.project1.DataModel.ServerResponse;
import com.pga.project1.DataModel.Taradod;
import com.pga.project1.DataModel.Work;
import com.pga.project1.Intefaces.CallBack;
import com.pga.project1.Intefaces.CallBackFunction;
import com.pga.project1.R;
import com.pga.project1.Utilities.Account;
import com.pga.project1.Utilities.HandleError;
import com.pga.project1.Utilities.Webservice;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by aliparsa on 11/8/2014.
 */
public class SyncHelper {


    public static void syncTaradod(final Context context) {
        DatabaseHelper db = new DatabaseHelper(context);
        final ArrayList<Taradod> taradods = db.getAllUnsentTaradod();
        if (taradods.size() > 0)
            Webservice.sendTaradod(context, taradods, new CallBack<ServerResponse>() {
                @Override
                public void onSuccess(ServerResponse result) {
                    try {
                        // TODO MAKE RECORDS SEND FLAG TRUE
                        JSONObject jsonObject = new JSONObject(result.getResult());
                        DatabaseHelper db = new DatabaseHelper(context);
                        db.markAsSentTaradod(taradods);

                        if (jsonObject.has("uids")) {
                            JSONArray jsonArray = jsonObject.getJSONArray("uids");
                            db.makeHasErrorTrueTaradod(jsonArray);
                        }

                        // TODO only mark Record don't have problem

                        if (context instanceof FastProjectManagmentActivity)
                            ((FastProjectManagmentActivity) context).TabAdapter.f1.loadTaradod();

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void onError(String errorMessage) {


                }
            });
    }

    public static void syncFaliat(final Context context) {
        DatabaseHelper db = new DatabaseHelper(context);
        final ArrayList<Faliat> faliats = db.getAllUnsentFaliat();
        if (faliats.size() > 0)
            Webservice.sendFaliat(context, faliats, new CallBack<ServerResponse>() {
                @Override
                public void onSuccess(ServerResponse result) {
                    // TODO MAKE RECORDS SEND FLAG TRUE
                    DatabaseHelper db = new DatabaseHelper(context);
                    db.markAsSentFaliat(faliats);

                    if (context instanceof FastProjectManagmentActivity)
                        ((FastProjectManagmentActivity) context).TabAdapter.f2.loadFaaliat();

                }

                @Override
                public void onError(String errorMessage) {

                }
            });
    }

    public static void syncWork(final Context context) {
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

    public static void syncProject(final Context context, final CallBack callback) {
        final DatabaseHelper db = new DatabaseHelper(context);

        Webservice.getProjects(context, new CallBack<ArrayList<Chart>>() {
            @Override
            public void onSuccess(ArrayList<Chart> result) {
                db.emptyProjectsTable();
                for (Chart chart : result) {
                    db.insertProject(chart);
                }

                callback.onSuccess(null);
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

                callback.onError(errorMessage);
            }
        });
    }

    public static void syncAnbar(final Context context, final CallBack callBack) {
        final DatabaseHelper db = new DatabaseHelper(context);

        Webservice.getAnbar(context, new CallBack<ArrayList<Anbar>>() {
            @Override
            public void onSuccess(ArrayList<Anbar> result) {
                db.emptyAnbarTable();
                for (Anbar anbar : result) {
                    db.insertAnbar(anbar);
                }


                callBack.onSuccess(null);
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

                    callBack.onError(errorMessage);
                }
            }
        });


    }

    public static void syncItemsProvider(final Context context, final CallBack callback) {
        final DatabaseHelper db = new DatabaseHelper(context);

        Webservice.getProvider(context, new CallBack<ArrayList<TaminKonande>>() {
            @Override
            public void onSuccess(ArrayList<TaminKonande> result) {
                db.emptyItemsProviderTable();
                for (TaminKonande taminKonande : result) {
                    db.insertItemsProvider(taminKonande);
                }

                callback.onSuccess(null);
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

    public static void syncProduct(final Context context, final CallBack callBack) {
        final DatabaseHelper db = new DatabaseHelper(context);

        Webservice.getProduct(context, new CallBack<ArrayList<Product>>() {
            @Override
            public void onSuccess(ArrayList<Product> result) {
                db.emptyProductTable();
                for (Product product : result) {
                    db.insertProduct(product);
                }
                callBack.onSuccess(null);
            }

            @Override
            public void onError(String errorMessage) {
                HandleError.HandleError(context, errorMessage, new CallBackFunction() {
                    @Override
                    public void execute() {

                    }
                });

                callBack.onError(errorMessage);
            }
        });


    }

    public static void SyncTaradodAndFaliat(final Context context) {
        DatabaseHelper db = new DatabaseHelper(context);

        if (db.getAllUnsentTaradod().size() == 0 && db.getAllUnsentFaliat().size() == 0)
            Toast.makeText(context, "همه داده ها ارسال شده است", Toast.LENGTH_LONG).show();
        else {
            syncFaliat(context);
            syncTaradod(context);

        }


    }

    public static void syncAnbarTransaction(final Context context, final CallBack callBack) {
        DatabaseHelper db = new DatabaseHelper(context);
        final ArrayList<AnbarTransaction> anbarTransactions = db.getAllAnbarTransactions();
        if (anbarTransactions.size() > 0)

            Webservice.sendAnbarTransaction(context, anbarTransactions, new CallBack<ServerResponse>() {
                @Override
                public void onSuccess(ServerResponse result) {
                    try {
                        // TODO MAKE RECORDS SEND FLAG TRUE
                        JSONObject jsonObject = new JSONObject(result.getResult());
                        DatabaseHelper db = new DatabaseHelper(context);
                        db.markAsSentAnbarTransaction(anbarTransactions);

                        if (jsonObject.has("uids")) {
                            JSONArray jsonArray = jsonObject.getJSONArray("uids");
                            db.makeHasErrorTrueAnbarTransaction(jsonArray);
                        }

                        callBack.onSuccess(null);

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void onError(String errorMessage) {
                    callBack.onError(errorMessage);

                }
            });
    }
}
