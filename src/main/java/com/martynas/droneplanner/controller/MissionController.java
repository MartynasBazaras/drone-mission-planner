package com.martynas.droneplanner.controller;

import com.martynas.droneplanner.entity.Mission;
import com.martynas.droneplanner.service.MissionService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

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
}