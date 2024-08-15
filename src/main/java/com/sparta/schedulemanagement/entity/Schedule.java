package com.sparta.schedulemanagement.entity;

import com.sparta.schedulemanagement.dto.RegisterScheduleReqDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
@NoArgsConstructor
public class Schedule {
    private Long scheduleId;
    private String userName;
    private String contents;
    private Timestamp createdDate;
    private Timestamp updatedDate;
    private String password;

    public Schedule(RegisterScheduleReqDto requestDto) {
        this.userName = requestDto.getUserName();
        this.contents = requestDto.getContents();
        this.password = requestDto.getPassword();
        this.createdDate = new Timestamp(System.currentTimeMillis());
        this.updatedDate = new Timestamp(System.currentTimeMillis());
    }


}
