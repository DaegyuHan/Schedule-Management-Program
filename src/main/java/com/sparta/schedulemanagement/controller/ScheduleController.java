package com.sparta.schedulemanagement.controller;

import com.sparta.schedulemanagement.dto.*;
import com.sparta.schedulemanagement.service.ScheduleService;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ScheduleController {
    private ScheduleService scheduleService;


    private JdbcTemplate jdbcTemplate;

    public ScheduleController(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        scheduleService = new ScheduleService(jdbcTemplate);
    }

    @PostMapping("/schedule")
    public RegisterScheduleResDto createSchedule(@RequestBody RegisterScheduleReqDto requestDto) {
        return scheduleService.createSchedule(requestDto);
    }

    @GetMapping("/schedule/{id}")
    public List<InquiryScheduleResDto> createSchedule(@PathVariable Long id) {
        return scheduleService.inquirySchedule(id);
    }

    @GetMapping("/schedule")
    public List<InquiryScheduleResDto> getSchedule(@RequestParam(required = false) String name,
                                                   @RequestParam(required = false) String date) {
        return scheduleService.inquirySchedule(name, date);
    }

    @PutMapping("/schedule")
    public Long updateSchedule(@RequestParam Long id, @RequestBody UpdateScheduleReqDto updateScheduleReqDto) {
        return scheduleService.updateSchedule(id,updateScheduleReqDto);
    }

    @PostMapping("/schedule/{id}")
    public Long deleteSchedule(@PathVariable Long id, @RequestBody DeleteScheduleReqDto deleteScheduleReqDto) {
        return scheduleService.deleteSchedule(id ,deleteScheduleReqDto);
    }
}
