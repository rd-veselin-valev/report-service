package com.example.report_service.controller;

import com.example.report_service.dto.mission.UserMissionsDto;
import com.example.report_service.dto.mission.WarehouseMissionsDto;
import com.example.report_service.service.MissionService;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/missions")
public class MissionController {
    private final MissionService missionService;

    @GetMapping("{warehouseId}/user/{userId}")
    public ResponseEntity<UserMissionsDto> getMissionForSingleUser(@PathVariable @Positive int userId, @PathVariable @Positive int warehouseId) {
        return ResponseEntity.ok(missionService.getMissionForSingleUser(userId, warehouseId));
    }

    @GetMapping("/warehouse/{warehouseId}")
    public ResponseEntity<WarehouseMissionsDto> getMissionsForSingleWarehouse(@PathVariable @Positive int warehouseId) {
        return ResponseEntity.ok(missionService.getMissionsForSingleWarehouse(warehouseId));
    }
}
