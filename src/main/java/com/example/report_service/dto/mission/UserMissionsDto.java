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
public class UserMissionsDto {
    private int userId;

    private Set<Integer> userMissionIds;
}