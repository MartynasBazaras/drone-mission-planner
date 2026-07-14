package com.martynas.droneplanner.service;

import com.martynas.droneplanner.entity.Mission;
import com.martynas.droneplanner.repository.MissionRepository;
import com.martynas.droneplanner.repository.WaypointRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

// Contains business logic for missions
@Service
public class MissionService {

    private final MissionRepository missionRepository;
    private final WaypointRepository waypointRepository;

    // Constructor injection
    public MissionService(
            MissionRepository missionRepository,
            WaypointRepository waypointRepository
    ) {
        this.missionRepository = missionRepository;
        this.waypointRepository = waypointRepository;
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

    // Delete mission and its waypoints
    @Transactional
    public void deleteMission(Long missionId) {
        missionRepository.findById(missionId)
                .orElseThrow();

        waypointRepository.deleteByMissionId(missionId);
        missionRepository.deleteById(missionId);
    }
}