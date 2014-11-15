package com.pga.project1.DataModel;

/**
 * Created by parsa on 2014-11-15.
 */
public class AnbarTransaction {
    int id;
    int type;
    int product_id;
    int anbar_id;
    int provider_id;
    int to_anbar_id;
    int from_anbar_id;
    int amount;
    String date;
    String description;

    public AnbarTransaction(int id, int type, int product_id, int anbar_id, int provider_id, int to_anbar_id, int from_anbar_id, int amount, String date, String description) {
        this.id = id;
        this.type = type;
        this.product_id = product_id;
        this.anbar_id = anbar_id;
        this.provider_id = provider_id;
        this.to_anbar_id = to_anbar_id;
        this.from_anbar_id = from_anbar_id;
        this.amount = amount;
        this.date = date;
        this.description = description;
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

    public int getProvider_id() {
        return provider_id;
    }

    public void setProvider_id(int provider_id) {
        this.provider_id = provider_id;
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
}
