package com.pga.project1.DataModel;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.pga.project1.Intefaces.ListViewItemINTERFACE;
import com.pga.project1.R;
import com.pga.project1.Utilities.PersianCalendar;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

/**
 * Created by parsa on 2014-11-15.
 */
public class AnbarTransaction implements ListViewItemINTERFACE{
    int id;
    int type;
    int product_id;
    Product product;
    int anbar_id;
    Anbar anbar;
    int taminKonande_id;
    TaminKonande taminKonande;
    int to_anbar_id;
    Anbar toAnbar;
    int from_anbar_id;
    Anbar fromAnbar;
    int amount;
    String date;
    String description;
    private int persianDate;
    private int sent;
    private int has_error;


    public AnbarTransaction(int id, int type, int product_id, int anbar_id, int taminKonande_id, int to_anbar_id, int from_anbar_id, int amount, String date, String description, int sent, int has_error) {
        this.id = id;
        this.type = type;
        this.product_id = product_id;
        this.anbar_id = anbar_id;
        this.taminKonande_id = taminKonande_id;
        this.to_anbar_id = to_anbar_id;
        this.from_anbar_id = from_anbar_id;
        this.amount = amount;
        this.date = date;
        this.description = description;
        this.sent = sent;
        this.has_error = has_error;
    }

    public AnbarTransaction(int type, int product_id, int anbar_id, int taminKonande_id, int to_anbar_id, int from_anbar_id, int amount, String date, String description, int sent, int has_error) {

        this.type = type;
        this.product_id = product_id;
        this.anbar_id = anbar_id;
        this.taminKonande_id = taminKonande_id;
        this.to_anbar_id = to_anbar_id;
        this.from_anbar_id = from_anbar_id;
        this.amount = amount;
        this.date = date;
        this.description = description;
        this.sent = sent;
        this.has_error = has_error;
    }

    public TaminKonande getTaminKonande() {
        return taminKonande;
    }

    public void setTaminKonande(TaminKonande taminKonande) {
        this.taminKonande = taminKonande;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Anbar getAnbar() {
        return anbar;
    }

    public void setAnbar(Anbar anbar) {
        this.anbar = anbar;
    }

    public Anbar getToAnbar() {
        return toAnbar;
    }

    public void setToAnbar(Anbar toAnbar) {
        this.toAnbar = toAnbar;
    }

    public Anbar getFromAnbar() {
        return fromAnbar;
    }

    public void setFromAnbar(Anbar fromAnbar) {
        this.fromAnbar = fromAnbar;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getProduct_id() {
        return product_id;
    }

    public void setProduct_id(int product_id) {
        this.product_id = product_id;
    }

    public int getAnbar_id() {
        return anbar_id;
    }

    public void setAnbar_id(int anbar_id) {
        this.anbar_id = anbar_id;
    }

    public int getTaminKonande_id() {
        return taminKonande_id;
    }

    public void setTaminKonande_id(int taminKonande_id) {
        this.taminKonande_id = taminKonande_id;
    }

    public int getTo_anbar_id() {
        return to_anbar_id;
    }

    public void setTo_anbar_id(int to_anbar_id) {
        this.to_anbar_id = to_anbar_id;
    }

    public int getFrom_anbar_id() {
        return from_anbar_id;
    }

    public void setFrom_anbar_id(int from_anbar_id) {
        this.from_anbar_id = from_anbar_id;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    public View getView(Context context, View oldView) {
        if (oldView == null || !(oldView.getTag() instanceof AnbarTransaction)) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            oldView = inflater.inflate(R.layout.anbar_transaction_item, null);
            Holder anbarHolder = new Holder();
            oldView.setTag(anbarHolder);
            getItem(anbarHolder, oldView);
            return oldView;
        } else {
            Holder anbarHolder = (Holder) oldView.getTag();
            getItem(anbarHolder, oldView);
            return oldView;
        }
    }

    private void getItem(Holder holder, View view) {

        holder.anbarTransaction = this;

        if (holder.kala_name == null)
            holder.kala_name = (TextView) view.findViewById(R.id.kala_name);

        if (holder.amount == null)
            holder.amount = (TextView) view.findViewById(R.id.anbar_transaction_amount);

        if (holder.in_out == null)
            holder.in_out = (TextView) view.findViewById(R.id.anbar_transaction_inout);

        if (holder.date == null)
            holder.date = (TextView) view.findViewById(R.id.anbar_transaction_date);

        if (holder.toanbar == null)
            holder.toanbar = (TextView) view.findViewById(R.id.anbar_transaction_toanbar);

        if (holder.taradodFlag == null)
            holder.taradodFlag = (TextView) view.findViewById(R.id.taradod_flag);


        holder.kala_name.setText(product.getName() + " ");
        holder.amount.setText(+getAmount() + "");

        if (getType() == 1 || getType() == 3) {
            holder.in_out.setText("ورود");
            holder.in_out.setTextColor(Color.parseColor("#FF31C401"));
        } else {
            holder.in_out.setText("خروج");
            holder.in_out.setTextColor(Color.RED);
        }

        if (getType() == 1)
            holder.toanbar.setText(" از " + taminKonande.getName());
        else if (getType() == 2)
            holder.toanbar.setText(" به  " + toAnbar.getName());
        else if (getType() == 3)
            holder.toanbar.setText(" از   " + fromAnbar.getName());

        holder.date.setText(this.getPersianDate());


        if (getSent() == 0) {
            holder.taradodFlag.setText("");
        }
        if (getSent() == 1) {
            holder.taradodFlag.setTextColor(Color.GREEN);
            holder.taradodFlag.setText("✓");
        }
        if (getHas_error() == 1) {
            holder.taradodFlag.setText("x");
            holder.taradodFlag.setTextColor(Color.RED);
        }

    }

    public String getPersianDate() {
        PersianCalendar pc = new PersianCalendar(getDate());
        return pc.getIranianDateTime();
    }

    public static JSONArray convertArrayToJson(List<AnbarTransaction> anbarTransactions) {

        JSONArray transArray = new JSONArray();

        for (AnbarTransaction trans : anbarTransactions) {
            try {
                JSONObject json = new JSONObject();

                json.put("type", trans.getType());
                json.put("product_id", trans.getProduct_id());
                json.put("chart_id", trans.getAnbar_id());
                json.put("provider", trans.getTaminKonande_id());
                json.put("to_chart", trans.getTo_anbar_id());
                json.put("from_chart", trans.getFrom_anbar_id());
                json.put("amount", trans.getAmount());
                json.put("date", trans.getDate());
                json.put("description", trans.getDescription());

                transArray.put(json);
            } catch (JSONException e) {
                e.printStackTrace();
            }

        }

        return transArray;
    }

    public int getSent() {
        return sent;
    }

    public void setSent(int sent) {
        this.sent = sent;
    }

    public int getHas_error() {
        return has_error;
    }

    public void setHas_error(int has_error) {
        this.has_error = has_error;
    }


    public class Holder {
        TextView kala_name;
        TextView amount;
        TextView in_out;
        TextView date;
        TextView toanbar;
        TextView taradodFlag;



        private AnbarTransaction anbarTransaction;

        public AnbarTransaction getAnbarTransaction() {
            return anbarTransaction;
        }

    }
}
