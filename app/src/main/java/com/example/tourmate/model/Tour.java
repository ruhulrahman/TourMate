package com.example.tourmate.model;

public class Tour {
    private int id;
    private String tourTitle,tourLocation, startDate, endDate, tourDesc;

    public Tour() {

    }

    public Tour(int id, String tourTitle, String tourLocation, String startDate, String endDate, String tourDesc) {
        this.id = id;
        this.tourTitle = tourTitle;
        this.tourLocation = tourLocation;
        this.startDate = startDate;
        this.endDate = endDate;
        this.tourDesc = tourDesc;
    }

    public int getId() {
        return id;
    }

    public String getTourTitle() {
        return tourTitle;
    }

    public String getTourLocation() {
        return tourLocation;
    }

    public String getStartDate() {
        return startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public String getTourDesc() {
        return tourDesc;
    }
}
