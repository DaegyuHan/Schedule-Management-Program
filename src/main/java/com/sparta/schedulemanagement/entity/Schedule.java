package com.sparta.schedulemanagement.entity;

import com.sparta.schedulemanagement.dto.ScheduleDto.RegisterScheduleReqDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
@NoArgsConstructor
public class Schedule {
    private Long scheduleId;
    private Long userId;
    private String contents;
    private Timestamp createdDate;
    private Timestamp updatedDate;
    private String password;

    public Schedule(RegisterScheduleReqDto requestDto) {
        this.userId = requestDto.getUserId();
        this.contents = requestDto.getContents();
        this.password = requestDto.getPassword();
        this.createdDate = new Timestamp(System.currentTimeMillis());
        this.updatedDate = new Timestamp(System.currentTimeMillis());
    }


}
