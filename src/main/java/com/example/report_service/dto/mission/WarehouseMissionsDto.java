package com.example.report_service.dto.mission;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class WarehouseMissionsDto {
    private int warehouseId;

    private int totalMissionCount;

    private List<UserMissionsDto> warehouseMissions;
}
