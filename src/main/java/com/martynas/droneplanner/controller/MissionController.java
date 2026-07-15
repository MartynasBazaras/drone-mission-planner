package com.martynas.droneplanner.controller;

import com.martynas.droneplanner.entity.Mission;
import com.martynas.droneplanner.service.MissionService;
import com.martynas.droneplanner.dto.MissionRequest;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;

// Handles HTTP requests for missions
@RestController
public class MissionController {

    private final MissionService missionService;

    // Constructor injection
    public MissionController(MissionService missionService) {
        this.missionService = missionService;
    }

    // Return all missions
    @GetMapping("/missions")
    public List<Mission> getAllMissions() {
        return missionService.getAllMissions();
    }

    // Create a new mission
    @PostMapping("/missions")
    public Mission createMission(@Valid @RequestBody MissionRequest request) {
        return missionService.createMission(
                request.getName(),
                request.getDescription()
        );
    }

    // Delete mission
    @DeleteMapping("/missions/{missionId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteMission(@PathVariable Long missionId) {
        missionService.deleteMission(missionId);
    }

    // Update an existing mission
    @PutMapping("/missions/{missionId}")
    public Mission updateMission(
            @PathVariable Long missionId,
            @Valid @RequestBody MissionRequest request) {
        return missionService.updateMission(
                missionId,
                request.getName(),
                request.getDescription()
        );
    }
}