package com.example.tourmate.model;

public class Expense {
    private int id;
    private Double amount;
    private String payment, date, time, desc, costType;
    private int tourId;

    public Expense() {

    }

    public Expense(int id, Double amount, String payment, String date, String time, String desc, String costType, int tourId) {
        this.id = id;
        this.amount = amount;
        this.payment = payment;
        this.date = date;
        this.time = time;
        this.desc = desc;
        this.costType = costType;
        this.tourId = tourId;
    }

    public int getId() {
        return id;
    }

    public Double getAmount() {
        return amount;
    }

    public String getPayment() {
        return payment;
    }

    public String getDate() {
        return date;
    }

    public String getTime() {
        return time;
    }

    public String getDesc() {
        return desc;
    }

    public String getCostType() {
        return costType;
    }

    public int getTourId() {
        return tourId;
    }
}
