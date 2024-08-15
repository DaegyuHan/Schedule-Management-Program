package com.sparta.schedulemanagement.dto.UserDto;

import lombok.Getter;

import java.sql.Timestamp;

@Getter
public class UpdateUserResDto {
    private Long userId;
    private String userName;
    private String email;
    private Timestamp updatedDate;
}
