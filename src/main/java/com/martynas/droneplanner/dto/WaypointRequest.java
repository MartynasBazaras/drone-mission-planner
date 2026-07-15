package com.martynas.droneplanner.dto;

// Contains waypoint data received from API requests
public class WaypointRequest {

    private double latitude;
    private double longitude;
    private double altitude;
    private int orderNumber;

    // Default constructor
    public WaypointRequest() {

    }

    // Get waypoint latitude
    public double getLatitude() {
        return latitude;
    }

    // Update waypoint latitude
    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    // Get waypoint longitude
    public double getLongitude() {
        return longitude;
    }

    // Update waypoint longitude
    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    // Get waypoint altitude
    public double getAltitude() {
        return altitude;
    }

    // Update waypoint altitude
    public void setAltitude(double altitude) {
        this.altitude = altitude;
    }

    // Get waypoint order number
    public int getOrderNumber() {
        return orderNumber;
    }

    // Update waypoint order number
    public void setOrderNumber(int orderNumber) {
        this.orderNumber = orderNumber;
    }
}
