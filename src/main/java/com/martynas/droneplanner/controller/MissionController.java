package com.martynas.droneplanner.controller;

import com.martynas.droneplanner.entity.Mission;
import com.martynas.droneplanner.service.MissionService;
import org.springframework.web.bind.annotation.*;

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
    public Mission createMission(@RequestBody Mission mission) {
        return missionService.createMission(
                mission.getName(),
                mission.getDescription()
        );
    }

    // Delete mission
    @DeleteMapping("/missions/{missionId}")
    public void deleteMission(@PathVariable Long missionId) {
        missionService.deleteMission(missionId);
    }
}