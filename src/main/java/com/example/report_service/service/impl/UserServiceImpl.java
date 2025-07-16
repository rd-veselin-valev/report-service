package com.example.report_service.service.impl;

import com.example.report_service.dto.user.UserDto;
import com.example.report_service.service.UserService;
import com.example.report_service.util.message.InfoMessages;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@Slf4j
public class UserServiceImpl implements UserService {
    private final RestTemplate restTemplate;

    public UserServiceImpl(RestTemplateBuilder builder) {
        this.restTemplate = builder.build();
    }

    @Override
    public UserDto getUserDetails(int id) {
        log.info(InfoMessages.CALLING_INVENTORY_SERVICE, id);
        ResponseEntity<UserDto> response = restTemplate.getForEntity("http://inventory-service:8080/api/users/1", UserDto.class);
        return response.getBody();
    }
}
