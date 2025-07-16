package com.example.report_service.service;

import com.example.report_service.dto.user.UserDto;

public interface UserService {
    UserDto getUserDetails(int id);
}
