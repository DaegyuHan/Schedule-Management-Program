package com.sparta.schedulemanagement.dto.UserDto;

import lombok.Getter;

import java.sql.Time;
import java.sql.Timestamp;

@Getter
public class InquiryUserResDto {
    private Long userId;
    private String userName;
    private String email;
    private Timestamp updatedDate;

    public InquiryUserResDto(Long userId, String userName, String email, Timestamp updatedDate) {
        this.userId = userId;
        this.userName = userName;
        this.email = email;
        this.updatedDate = updatedDate;
    }
}
