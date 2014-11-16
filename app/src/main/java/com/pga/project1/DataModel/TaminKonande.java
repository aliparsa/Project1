package com.pga.project1.DataModel;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.pga.project1.Intefaces.ListViewItemINTERFACE;
import com.pga.project1.R;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by parsa on 2014-11-15.
 */
public class TaminKonande implements ListViewItemINTERFACE {
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    int id;
    String name;
    String owner;

    public TaminKonande(int id, String name, String owner) {
        this.id = id;
        this.name = name;
        this.owner = owner;

    }

    public static ArrayList<TaminKonande> getArrayFromJson(JSONArray jsonArray) {
        ArrayList<TaminKonande> itemlist = new ArrayList<TaminKonande>();


        for (int i = 0; i < jsonArray.length(); i++) {

            try {

                JSONObject jsonObject = (JSONObject) jsonArray.get(i);

                int id = jsonObject.getInt("id");
                String name = jsonObject.getString("name");
                String owner = jsonObject.getString("owner");


                TaminKonande taminKonande = new TaminKonande(id, name, owner);

                itemlist.add(taminKonande);

            } catch (Exception e) {
                continue;
            }

        }


        return itemlist;
    }


    public View getView(Context context, View oldView) {
        if (oldView == null || !(oldView.getTag() instanceof TaminKonande)) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            oldView = inflater.inflate(R.layout.tamin_konade_item, null);
            Holder holder = new Holder();
            oldView.setTag(holder);
            getItem(holder, oldView);
            return oldView;
        } else {
            Holder holder = (Holder) oldView.getTag();
            getItem(holder, oldView);
            return oldView;
        }
    }

    private void getItem(Holder holder, View view) {

        holder.taminKonande = this;

        if (holder.taminKonande_name == null)
            holder.taminKonande_name = (TextView) view.findViewById(R.id.tamin_konande_name);

        holder.taminKonande_name.setText(getName());

        if (holder.taminKonande_owner == null)
            holder.taminKonande_owner = (TextView) view.findViewById(R.id.tamin_konande_owner);

        holder.taminKonande_owner.setText(getOwner());


    }

    public class Holder {
        TextView taminKonande_name;
        TextView taminKonande_owner;


        private TaminKonande taminKonande;

        public TaminKonande getTaminKonande() {
            return taminKonande;
        }

    }
}
