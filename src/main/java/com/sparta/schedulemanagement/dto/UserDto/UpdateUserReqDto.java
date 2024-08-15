package com.sparta.schedulemanagement.dto.UserDto;

import lombok.Getter;

import java.sql.Timestamp;

@Getter
public class UpdateUserReqDto {
    private String userName;
    private String email;
    private Timestamp updatedDate;

    public UpdateUserReqDto() {
        this.updatedDate = new Timestamp(System.currentTimeMillis());
    }
}
