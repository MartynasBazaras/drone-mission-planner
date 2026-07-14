package com.martynas.droneplanner.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

// Represents a drone mission
@Entity
public class Mission {

    // Primary key
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Mission name
    private String name;

    // Creation date
    private LocalDateTime createdAt;

    // Mission description
    private String description;

    // Default constructor (required by JPA)
    public Mission() {
    }

    // Constructor with mission details
    public Mission(String name, String description) {
        this.name = name;
        this.createdAt = LocalDateTime.now();
        this.description = description;
    }

    // Get mission ID
    public Long getId() {
        return id;
    }

    // Get mission name
    public String getName() {
        return name;
    }

    // Get creation date
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    // Update mission name
    public void setName(String name) {
        this.name = name;
    }

    // Get Mission description
    public String getDescription() {
        return description;
    }

    // Update mission description
    public void setDescription(String description) {
        this.description = description;
    }
}