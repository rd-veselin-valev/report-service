package com.example.report_service.service.impl;

import com.example.report_service.data.repository.MissionRepository;
import com.example.report_service.service.MissionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MissionServiceImpl implements MissionService {
    private final MissionRepository missionRepository;
}
