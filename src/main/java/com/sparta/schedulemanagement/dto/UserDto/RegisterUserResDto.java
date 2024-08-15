package com.sparta.schedulemanagement.dto.UserDto;

import com.sparta.schedulemanagement.entity.User;
import lombok.Getter;

import java.sql.Timestamp;

@Getter
public class RegisterUserResDto {
    private Long userId;
    private String userName;
    private String email;
    private Timestamp createdDate;

    public RegisterUserResDto(User user) {
        this.userId = user.getUserId();
        this.userName = user.getUserName();
        this.email = user.getEmail();
        this.createdDate = user.getCreatedDate();
    }
}
