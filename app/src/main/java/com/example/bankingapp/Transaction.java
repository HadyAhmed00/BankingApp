package com.example.bankingapp;

public class Transaction {


    User src;
    User dst;
    float amount;
    boolean status;
    String data;

    public Transaction(User src, User dst, float amount, boolean status) {
        this.src = src;
        this.dst = dst;
        this.amount = amount;
        this.status = status;
    }

    public User getSrc() {
        return src;
    }

    public User getDst() {
        return dst;
    }

    public float getAmount() {
        return amount;
    }

    public boolean isStatus() {
        return status;
    }

    public void setSrc(User src) {
        this.src = src;
    }

    public void setDst(User dst) {
        this.dst = dst;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
