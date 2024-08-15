package com.sparta.schedulemanagement.dto.ScheduleDto;

import lombok.Getter;

import java.sql.Timestamp;

@Getter
public class UpdateScheduleResDto {
    private Long scheduleId;
    private Long userId;
    private String contents;
    private Timestamp updatedDate;
}
