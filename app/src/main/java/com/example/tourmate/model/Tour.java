package com.example.tourmate.model;

public class Tour {

    private String tripName;
    private String tripDescrip;
    private String fromDate;
    private String toDate;

    public Tour() {
    }

    public Tour(String tripName, String tripDescrip, String fromDate, String toDate) {
        this.tripName = tripName;
        this.tripDescrip = tripDescrip;
        this.fromDate = fromDate;
        this.toDate = toDate;
    }

    public String getTripName() {
        return tripName;
    }

    public String getTripDescrip() {
        return tripDescrip;
    }

    public String getFromDate() {
        return fromDate;
    }

    public String getToDate() {
        return toDate;
    }
}
