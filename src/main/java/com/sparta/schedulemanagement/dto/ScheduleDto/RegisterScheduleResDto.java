package com.sparta.schedulemanagement.dto.ScheduleDto;

import com.sparta.schedulemanagement.entity.Schedule;
import lombok.Getter;

import java.sql.Timestamp;

@Getter
public class RegisterScheduleResDto {
    private Long scheduleId;
    private String userName;
    private String contents;
    private Timestamp createdDate;

    public RegisterScheduleResDto(Schedule schedule) {
        this.scheduleId = schedule.getScheduleId();
        this.userName = schedule.getUserName();
        this.contents = schedule.getContents();
        this.createdDate = schedule.getCreatedDate();
    }
}
