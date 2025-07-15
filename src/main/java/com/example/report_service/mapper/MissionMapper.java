package com.example.report_service.mapper;

import com.example.report_service.data.entity.Mission;
import com.example.report_service.dto.mission.MissionMessageDto;
import com.example.report_service.dto.mission.UserMissionsDto;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface MissionMapper {
    UserMissionsDto toMissionDto(Mission mission);

    Mission toMission(MissionMessageDto mission);
}