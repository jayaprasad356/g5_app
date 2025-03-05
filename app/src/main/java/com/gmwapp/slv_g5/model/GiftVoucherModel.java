package com.gmwapp.slv_g5.model;

public class GiftVoucherModel {
    String id ,user_id , name, amount, datetime, type;
    public GiftVoucherModel(){

    }

    public GiftVoucherModel(String id, String user_id, String name, String amount, String datetime, String type) {
        this.id = id;
        this.user_id = user_id;
        this.name = name;
        this.amount = amount;
        this.datetime = datetime;
        this.type = type;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getDatetime() {
        return datetime;
    }

    public void setDatetime(String datetime) {
        this.datetime = datetime;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
