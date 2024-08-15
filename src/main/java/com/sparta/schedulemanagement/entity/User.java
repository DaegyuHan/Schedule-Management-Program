package com.sparta.schedulemanagement.entity;

import com.sparta.schedulemanagement.dto.UserDto.RegisterUserReqDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
@NoArgsConstructor
public class User {
    private Long userId;
    private String userName;
    private String email;
    private Timestamp createdDate;
    private Timestamp updatedDate;

    public User(RegisterUserReqDto requestDto) {
        this.userName = requestDto.getUserName();
        this.email = requestDto.getEmail();
        this.createdDate = new Timestamp(System.currentTimeMillis());
        this.updatedDate = new Timestamp(System.currentTimeMillis());
    }
}
