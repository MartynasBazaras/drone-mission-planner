package com.martynas.droneplanner.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

// Contains mission data received from API requests
public class MissionRequest {

    // Mission name
    @NotBlank(message = "Mission name is required")
    @Size(max = 100, message = "Mission name must not exceed 100 characters")
    private String name;

    // Mission description
    @NotBlank(message = "Mission description is required")
    @Size(max = 500, message = "Mission description must not exceed 500 characters")
    private String description;

    // Default constructor
    public MissionRequest() {
    }

    // Get mission name
    public String getName() {
        return name;
    }

    // Update mission name
    public void setName(String name) {
        this.name = name;
    }

    // Get mission description
    public String getDescription() {
        return description;
    }

    // Update mission description
    public void setDescription(String description) {
        this.description = description;
    }
}