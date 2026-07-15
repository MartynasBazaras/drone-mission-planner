package com.martynas.droneplanner.controller;

import com.martynas.droneplanner.entity.Waypoint;
import com.martynas.droneplanner.service.WaypointService;
import com.martynas.droneplanner.dto.WaypointRequest;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// Handles HTTP requests for waypoints
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
                                   @RequestBody WaypointRequest request) {
        return waypointService.createWaypoint(
                request.getLatitude(),
                request.getLongitude(),
                request.getAltitude(),
                request.getOrderNumber(),
                missionId
        );
    }

    // Return all waypoints for mission
    @GetMapping("/missions/{missionId}/waypoints")
    public List<Waypoint> getWaypointsByMission(
            @PathVariable Long missionId
    ) {
        return waypointService.getWaypointsByMission(missionId);
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
            @RequestBody WaypointRequest request
    ) {
        return waypointService.updateWaypoint(
                waypointId,
                request.getLatitude(),
                request.getLongitude(),
                request.getAltitude(),
                request.getOrderNumber()
        );
    }
}
