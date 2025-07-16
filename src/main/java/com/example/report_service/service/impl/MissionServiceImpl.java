package com.example.report_service.service.impl;

import com.example.report_service.data.repository.MissionRepository;
import com.example.report_service.dto.mission.MissionRecord;
import com.example.report_service.dto.mission.UserMissionsDto;
import com.example.report_service.dto.mission.WarehouseMissionsDto;
import com.example.report_service.service.MissionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MissionServiceImpl implements MissionService {
    private final MissionRepository missionRepository;

    @Override
    public UserMissionsDto getMissionForSingleUser(int userId, int warehouseId) {
        var missions = missionRepository.findMissionIds(userId, warehouseId);

        return UserMissionsDto
                .builder()
                .userId(userId)
                .missionCount(missions.size())
                .userMissionIds(missions)
                .build();
    }

    @Override
    public WarehouseMissionsDto getMissionsForSingleWarehouse(int warehouseId) {
        var missions = missionRepository.findByWarehouseId(warehouseId);
        var missionsByUser = missions.stream()
                .collect(Collectors.groupingBy(MissionRecord::userId))
                .entrySet().stream()
                .map(missionPair -> UserMissionsDto
                        .builder()
                        .userId(missionPair.getKey())
                        .missionCount(missionPair.getValue().size())
                        .userMissionIds(missionPair.getValue().stream().map(MissionRecord::id).toList())
                        .build())
                .toList();

        return WarehouseMissionsDto
                .builder()
                .warehouseId(warehouseId)
                .totalMissionCount(missions.size())
                .warehouseMissions(missionsByUser)
                .build();
    }
}
