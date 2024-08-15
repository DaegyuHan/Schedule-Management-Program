package com.sparta.schedulemanagement.dto.ScheduleDto;

import lombok.Getter;

@Getter
public class RegisterScheduleReqDto {
    private Long userId;
    private String contents;
    private String password;
}
