package com.sparta.schedulemanagement.dto.ScheduleDto;

import lombok.Getter;

import java.sql.Timestamp;

@Getter
public class InquiryScheduleResDto {
    private Long scheduleId;
    private String userName;
    private String contents;
    private Timestamp updatedDate;

    public InquiryScheduleResDto(Long scheduleId, String username, String contents, Timestamp updatedDate) {
        this.scheduleId = scheduleId;
        this.userName = username;
        this.contents = contents;
        this.updatedDate = updatedDate;
    }
}
