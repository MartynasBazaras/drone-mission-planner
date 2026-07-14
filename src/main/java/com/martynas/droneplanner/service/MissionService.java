package com.martynas.droneplanner.service;

import com.martynas.droneplanner.entity.Mission;
import com.martynas.droneplanner.repository.MissionRepository;
import org.springframework.stereotype.Service;

import java.util.List;

// Contains business logic for missions
@Service
public class MissionService {

    private final MissionRepository missionRepository;

    // Constructor injection
    public MissionService(MissionRepository missionRepository) {
        this.missionRepository = missionRepository;
    }

    // Get all missions from database
    public List<Mission> getAllMissions() {
        return missionRepository.findAll();
    }

    // Create and save a new mission
    public Mission createMission(String name, String description) {
        Mission mission = new Mission(name, description);
        return missionRepository.save(mission);
    }
}