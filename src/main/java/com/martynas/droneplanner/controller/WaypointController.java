package com.martynas.droneplanner.controller;

import com.martynas.droneplanner.entity.Waypoint;
import com.martynas.droneplanner.service.WaypointService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

// Handles HTTP requests for missions
@RestController
public class WaypointController {

    private final WaypointService waypointService;

    // Constructor injection
    public WaypointController(WaypointService waypointService) {
        this.waypointService = waypointService;
    }

    // Create new waypoint
    @PostMapping("/missions/{missionId}/waypoints")
    public Waypoint createWaypoint(@PathVariable long missionId,
                                   @RequestBody Waypoint waypoint) {
        return waypointService.createWaypoint(
                waypoint.getLatitude(),
                waypoint.getLongitude(),
                waypoint.getAltitude(),
                waypoint.getOrderNumber(),
                missionId
        );
    }

    // Return all waypoints for mission
    @GetMapping("/missions/{missionId}/waypoints")
    public List<Waypoint> getwaypointsByMission(
            @PathVariable Long missionId
    ) {
        return waypointService.getwaypointsByMission(missionId);
    }

    // Delete waypoint
    @DeleteMapping("/waypoints/{waypointId}")
    public void deleteWaypoint(@PathVariable Long waypointId) {
        waypointService.deleteWaypoint(waypointId);
    }

    // Update an existing waypoint
    @PutMapping("/waypoints/{waypointId}")
    public Waypoint updateWaypoint(
            @PathVariable Long waypointId,
            @RequestBody Waypoint waypoint
    ) {
        return waypointService.updateWaypoint(
                waypointId,
                waypoint.getLatitude(),
                waypoint.getLongitude(),
                waypoint.getAltitude(),
                waypoint.getOrderNumber()
        );
    }
}
