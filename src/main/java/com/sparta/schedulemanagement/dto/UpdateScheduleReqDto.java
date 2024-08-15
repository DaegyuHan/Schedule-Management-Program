package com.sparta.schedulemanagement.dto;

import lombok.Getter;

import java.sql.Timestamp;

@Getter
public class UpdateScheduleReqDto {
    private String userName;
    private String contents;
    private String password;
    private Timestamp updatedDate;

    public UpdateScheduleReqDto() {
        this.updatedDate = new Timestamp(System.currentTimeMillis());
    }
}

