package com.example.report_service.service.impl;

import com.example.report_service.dto.user.UserDto;
import com.example.report_service.service.UserService;
import com.example.report_service.util.message.InfoMessages;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final RestClient restClient;

    @Override
    public UserDto getUserDetails(int id) {
        log.info(InfoMessages.CALLING_INVENTORY_SERVICE, id);
        return restClient
                .get()
                .uri("/api/users/{id}", id)
                .retrieve()
                .body(UserDto.class);
    }
}
