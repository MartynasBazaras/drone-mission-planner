package com.martynas.droneplanner.repository;

import com.martynas.droneplanner.entity.Mission;
import org.springframework.data.jpa.repository.JpaRepository;

// Handles database operations for Mission
public interface MissionRepository
        extends JpaRepository<Mission, Long> {
}