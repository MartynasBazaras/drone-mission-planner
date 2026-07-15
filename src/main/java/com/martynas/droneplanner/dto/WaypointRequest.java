package com.martynas.droneplanner.dto;

import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;

// Contains waypoint data received from API requests
public class WaypointRequest {

    // Waypoint latitude
    @NotNull(message = "Latitude is required")
    @DecimalMin(value = "-90.0", message = "Latitude must be at least -90")
    @DecimalMax(value = "90.0", message = "Latitude must not exceed 90")
    private Double latitude;

    // Waypoint longitude
    @NotNull(message = "Longitude is required")
    @DecimalMin(value = "-180.0", message = "Longitude must be at least -180")
    @DecimalMax(value = "180.0", message = "Longitude must not exceed 180")
    private Double longitude;

    // Flight altitude in meters
    @NotNull(message = "Altitude is required")
    @PositiveOrZero(message = "Altitude must be zero or greater")
    private Double altitude;

    // Point position in the route
    @NotNull(message = "Order number is required")
    @Positive(message = "Order number must be greater than zero")
    private Integer orderNumber;

    // Default constructor
    public WaypointRequest() {
    }

    // Get waypoint latitude
    public Double getLatitude() {
        return latitude;
    }

    // Update waypoint latitude
    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    // Get waypoint longitude
    public Double getLongitude() {
        return longitude;
    }

    // Update waypoint longitude
    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    // Get waypoint altitude
    public Double getAltitude() {
        return altitude;
    }

    // Update waypoint altitude
    public void setAltitude(Double altitude) {
        this.altitude = altitude;
    }

    // Get waypoint order number
    public Integer getOrderNumber() {
        return orderNumber;
    }

    // Update waypoint order number
    public void setOrderNumber(Integer orderNumber) {
        this.orderNumber = orderNumber;
    }
}