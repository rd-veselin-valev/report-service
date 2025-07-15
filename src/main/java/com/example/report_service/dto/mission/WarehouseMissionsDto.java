package com.example.report_service.dto.mission;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class WarehouseMissionsDto {
    private int warehouseId;
    
    private Set<UserMissionsDto> warehouseMissions;
}
