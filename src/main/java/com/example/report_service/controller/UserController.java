package com.example.report_service.controller;

import com.example.report_service.dto.user.UserDto;
import com.example.report_service.service.UserService;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
public class UserController {
    private final UserService userService;

    @GetMapping("/{id}")
    public ResponseEntity<UserDto> getUsersDetails(@PathVariable @Positive int id) {
        return ResponseEntity.ok(userService.getUserDetails(id));
    }
}
