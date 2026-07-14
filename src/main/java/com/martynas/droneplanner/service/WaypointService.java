package com.martynas.droneplanner.service;

import com.martynas.droneplanner.entity.Waypoint;
import com.martynas.droneplanner.entity.Mission;
import com.martynas.droneplanner.repository.MissionRepository;
import com.martynas.droneplanner.repository.WaypointRepository;
import org.springframework.stereotype.Service;

import java.util.List;

// Contains business logic for missions
@Service
public class WaypointService {

    private final WaypointRepository waypointRepository;
    private final MissionRepository missionRepository;

    // Constructor injection
    public WaypointService(WaypointRepository waypointRepository,
                           MissionRepository missionRepository) {
        this.waypointRepository = waypointRepository;
        this.missionRepository = missionRepository;
    }

    // Create and save a new waypoint
    public Waypoint createWaypoint(double latitude,
                                   double longitude,
                                   double altitude,
                                   int orderNumber,
                                   long missionId) {

        Mission mission = missionRepository.findById(missionId)
                .orElseThrow();

        Waypoint waypoint = new Waypoint(
                latitude,
                longitude,
                altitude,
                orderNumber,
                mission
        );
        
        return waypointRepository.save(waypoint);
    }

    // Get all mission waypoints
    public List<Waypoint> getwaypointsByMission(Long missionId) {
        return waypointRepository.
                findByMissionIdOrderByOrderNumberAsc(missionId);
    }

    // Delete waypoint
    public void deleteWaypoint(Long waypointId) {
        waypointRepository.findById(waypointId)
                .orElseThrow();

        waypointRepository.deleteById(waypointId);
    }
}