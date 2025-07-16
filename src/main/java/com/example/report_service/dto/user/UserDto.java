package com.example.report_service.dto.user;

import com.example.report_service.data.enums.UserRole;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
    private int id;

    private String username;

    private String firstName;

    private String lastName;

    private UserRole role;

    private int warehouseId;
}
