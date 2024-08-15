package com.sparta.schedulemanagement.dto.ScheduleDto;

import lombok.Getter;

import java.sql.Timestamp;

@Getter
public class InquiryScheduleResDto {
    private Long scheduleId;
    private Long userId;
    private String contents;
    private Timestamp updatedDate;

    public InquiryScheduleResDto(Long scheduleId, Long userId, String contents, Timestamp updatedDate) {
        this.scheduleId = scheduleId;
        this.userId = userId;
        this.contents = contents;
        this.updatedDate = updatedDate;
    }
}
