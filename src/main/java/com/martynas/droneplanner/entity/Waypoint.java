package com.martynas.droneplanner.entity;

import jakarta.persistence.*;

import javax.naming.Name;

// Represents one point in a drone mission
@Entity
public class Waypoint {
    // Primary key
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Geographic latitude
    private double latitude;

    // Geographic longitude
    private double longitude;

    // Flight altitude in meters
    private double altitude;

    // Point position in the route
    private int orderNumber;

    // Mission that owns this waypoint
    @ManyToOne
    @JoinColumn(name = "mission_id", nullable = false)
    private Mission mission;

    // Default constructor required by JPA
    public Waypoint() {
    }

    // Constructor with waypoint details
    public Waypoint(
            double latitude,
            double longitude,
            double altitude,
            int orderNumber,
            Mission mission
    ) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.altitude = altitude;
        this.orderNumber = orderNumber;
        this.mission = mission;
    }

    // Get waypoint ID
    public Long getId() {
        return id;
    }

    // Get latitude
    public double getLatitude() {
        return latitude;
    }

    // Update latitude
    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    // Get longitude
    public double getLongitude() {
        return longitude;
    }

    // Update longitude
    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    // Get altitude
    public double getAltitude() {
        return altitude;
    }

    // Update altitude
    public void setAltitude(double altitude) {
        this.altitude = altitude;
    }

    // Get route order
    public int getOrderNumber() {
        return orderNumber;
    }

    // Update route order
    public void setOrderNumber(int orderNumber) {
        this.orderNumber = orderNumber;
    }

    // Get parent mission
    public Mission getMission() {
        return mission;
    }

    // Update parent mission
    public void setMission(Mission mission) {
        this.mission = mission;
    }
}
