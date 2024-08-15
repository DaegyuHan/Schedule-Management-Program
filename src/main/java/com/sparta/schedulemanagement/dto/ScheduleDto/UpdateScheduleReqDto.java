package com.sparta.schedulemanagement.dto.ScheduleDto;

import lombok.Getter;

import java.sql.Timestamp;

@Getter
public class UpdateScheduleReqDto {
    private Long userId;
    private String contents;
    private String password;
    private Timestamp updatedDate;

    public UpdateScheduleReqDto() {
        this.updatedDate = new Timestamp(System.currentTimeMillis());
    }
}

