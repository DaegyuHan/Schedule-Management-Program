package com.sparta.schedulemanagement.dto.ScheduleDto;

import lombok.Getter;

import java.sql.Timestamp;

@Getter
public class InquiryScheduleResDto {
    private Long scheduleId;
    private Long userId;
    private String userName;
    private String contents;
    private Timestamp updatedDate;

    public InquiryScheduleResDto(Long scheduleId, Long userId, String userName, String contents, Timestamp updatedDate) {
        this.scheduleId = scheduleId;
        this.userId = userId;
        this.userName = userName;
        this.contents = contents;
        this.updatedDate = updatedDate;
    }
}
