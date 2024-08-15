package com.sparta.schedulemanagement.dto;

import lombok.Getter;

import java.sql.Timestamp;

@Getter
public class UpdateScheduleResDto {
    private Long scheduleId;
    private String userName;
    private String contents;
    private Timestamp updatedDate;
}
