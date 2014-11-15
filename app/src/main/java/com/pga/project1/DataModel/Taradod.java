package com.pga.project1.DataModel;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.pga.project1.R;
import com.pga.project1.Structures.AdapterInputType;
import com.pga.project1.Utilities.PersianCalendar;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;
import java.util.Objects;
import java.util.zip.Inflater;

/**
 * Created by ashkan on 2014-11-02.
 */
public class Taradod {

    private int id;
    private String personnelID;
    private String inOut;
    private int sent;
    private String date;
    private String projectID;
    private String has_error;


    private Personnel personnel;

    /*TABLE_PERSONNEL + "("
            + KEY_ID + " INTEGER PRIMARY KEY,"
            + KEY_CODE + " TEXT,"
            + KEY_IN_OUT + " TEXT,"
            + KEY_SENT + " TEXT,"
            + KEY_DATE + " TEXT"*/


    public Taradod(int id, String personnelId, String inOut, int sent, String date, String projectID, String has_error) {
        this.id = id;
        this.inOut = inOut;
        this.sent = sent;
        this.date = date;
        this.personnelID = personnelId;
        this.projectID = projectID;
        this.has_error = has_error;
    }

    public Taradod(String personnelId, String inOut, int sent, String date, String projectID) {

        this.inOut = inOut;
        this.sent = sent;
        this.projectID = projectID;
        this.date = date;
        this.personnelID = personnelId;


    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public String getInOut() {
        return inOut;
    }

    public void setInOut(String inOut) {
        this.inOut = inOut;
    }

    public int getSent() {
        return sent;
    }

    public void setSent(int sent) {
        this.sent = sent;
    }


    public static JSONArray convertArrayToJson(List<Taradod> taradods) {

        JSONArray taradodsJson = new JSONArray();

        for (Taradod tar : taradods) {
            try {
                JSONObject json = new JSONObject();

                json.put("uid", tar.getId());
                json.put("personnel_id", tar.getPersonnelID());
                json.put("chart_id", tar.getProjectID());
                json.put("in_out", tar.inOut.equals("in") ? "1" : "0");
                json.put("date", tar.getDate());


                taradodsJson.put(json);
            } catch (JSONException e) {
                e.printStackTrace();
            }

        }

        return taradodsJson;
    }

    public String getDate() {
        return date;
    }

    public String getPersianDate() {
        PersianCalendar pc = new PersianCalendar(getDate());
        return pc.getIranianDateTime();
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Personnel getPersonnel() {
        return personnel;
    }

    public void setPersonnel(Personnel personnel) {
        this.personnel = personnel;
    }

    public String getProjectID() {
        return projectID;
    }

    public void setProjectID(String projectID) {
        this.projectID = projectID;
    }

    public String getPersonnelID() {
        return personnelID;
    }

    public void setPersonnelID(String personnelID) {
        this.personnelID = personnelID;
    }

    public String getHas_error() {
        return has_error;
    }

    public void setHas_error(String has_error) {
        this.has_error = has_error;
    }

    public View getView(Context context, View oldView) {
        if (oldView == null || !(oldView.getTag() instanceof Taradod)) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            oldView = inflater.inflate(R.layout.taradod_item, null);
            TaradodHolder taradodHolder = new TaradodHolder();
            oldView.setTag(taradodHolder);
            getTaradodItem(taradodHolder, oldView);
            return oldView;
        } else {
            TaradodHolder taradodHolder = (TaradodHolder) oldView.getTag();
            getTaradodItem(taradodHolder, oldView);
            return oldView;
        }
    }

    private void getTaradodItem(TaradodHolder holder, View view) {

        holder.taradod = this;

        if (holder.taradodFullName == null)
            holder.taradodFullName = (TextView) view.findViewById(R.id.taradod_fullname);

        if (holder.taradodDate == null)
            holder.taradodDate = (TextView) view.findViewById(R.id.taradod_date);

        if (holder.taradodInOut == null)
            holder.taradodInOut = (TextView) view.findViewById(R.id.taradod_inout);

        if (holder.taradodFlag == null)
            holder.taradodFlag = (TextView) view.findViewById(R.id.taradod_flag);


        holder.taradodFullName.setText(getPersonnel().getFullName());
        holder.taradodDate.setText(getPersianDate());
        holder.taradodInOut.setText(getInOut().equals("in") ? "ورود" : "خروج");

        if (getInOut().equals("in"))
            holder.taradodInOut.setTextColor(Color.GREEN);
        else
            holder.taradodInOut.setTextColor(Color.RED);


        if (getSent() == 0) {
            holder.taradodFlag.setText("");
        }
        if (getSent() == 1) {
            holder.taradodFlag.setTextColor(Color.GREEN);
            holder.taradodFlag.setText("✓");

        }

        if (getHas_error().equals("1")) {
            holder.taradodFlag.setText("x");
            holder.taradodFlag.setTextColor(Color.RED);
        }

    }


    public class TaradodHolder {
        TextView taradodFullName;
        TextView taradodDate;
        TextView taradodInOut;
        TextView taradodFlag;
        Taradod taradod;
    }
}
