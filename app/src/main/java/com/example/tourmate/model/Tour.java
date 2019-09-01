package com.example.tourmate.model;

public class Tour {
    private int id;
    private String tourTitle, TourDate, TourLocation, TourDesc;

    public Tour() {

    }

    public Tour(int id, String tourTitle, String tourDate, String tourLocation, String tourDesc) {
        this.id = id;
        this.tourTitle = tourTitle;
        TourDate = tourDate;
        TourLocation = tourLocation;
        TourDesc = tourDesc;
    }

    public int getId() {
        return id;
    }

    public String getTourTitle() {
        return tourTitle;
    }

    public String getTourDate() {
        return TourDate;
    }

    public String getTourLocation() {
        return TourLocation;
    }

    public String getTourDesc() {
        return TourDesc;
    }
}
