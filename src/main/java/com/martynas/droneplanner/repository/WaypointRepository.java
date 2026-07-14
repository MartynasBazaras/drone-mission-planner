package com.martynas.droneplanner.repository;

import com.martynas.droneplanner.entity.Waypoint;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

// Handles database operations for waypoints
public interface WaypointRepository extends JpaRepository<Waypoint, Long> {

    // Find waypoints by mission and sort by route order
    List<Waypoint> findByMissionIdOrderByOrderNumberAsc(Long missionId);

    // Delete all waypoints by mission ID
    void deleteByMissionId(Long missionId);
}