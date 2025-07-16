package com.example.report_service.service;

import com.example.report_service.dto.mission.UserMissionsDto;
import com.example.report_service.dto.mission.WarehouseMissionsDto;

public interface MissionService {
    UserMissionsDto getMissionForSingleUser(int userId, int warehouseId);

    WarehouseMissionsDto getMissionsForSingleWarehouse(int warehouseId);
}
