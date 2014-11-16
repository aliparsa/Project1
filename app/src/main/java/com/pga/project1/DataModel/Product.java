package com.pga.project1.DataModel;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.pga.project1.R;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by parsa on 2014-11-15.
 */
public class Product {
    int id;
    String name;

    public Product(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public static ArrayList<Product> getArrayFromJson(JSONArray jsonArray) {
        ArrayList<Product> itemlist = new ArrayList<Product>();


        for (int i = 0; i < jsonArray.length(); i++) {

            try {
                //if ((JSONObject) jsonArray.get(i)==null) continue;

                JSONObject jsonObject = (JSONObject) jsonArray.get(i);

                int id = jsonObject.getInt("id");
                String name = jsonObject.getString("name");


                Product product = new Product(id, name);

                itemlist.add(product);

            } catch (Exception e) {
                continue;
            }

        }


        return itemlist;
    }

    public View getView(Context context, View oldView) {
        if (oldView == null || !(oldView.getTag() instanceof Product)) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            oldView = inflater.inflate(R.layout.product_item, null);
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

        holder.product = this;

        if (holder.product_name == null)
            holder.product_name = (TextView) view.findViewById(R.id.product_name);

        holder.product_name.setText(getName());


    }

    public class Holder {
        TextView product_name;


        private Product product;

        public Product getProduct() {
            return product;
        }

    }
}
