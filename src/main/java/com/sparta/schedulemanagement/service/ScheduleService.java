package com.sparta.schedulemanagement.service;

import com.sparta.schedulemanagement.dto.ScheduleDto.*;
import com.sparta.schedulemanagement.entity.Schedule;
import com.sparta.schedulemanagement.repository.ScheduleRepository;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class ScheduleService {
    private final JdbcTemplate jdbcTemplate;

    public ScheduleService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    public RegisterScheduleResDto createSchedule(RegisterScheduleReqDto requestDto) {
        // RequestDto -> Entity
        Schedule schedule = new Schedule(requestDto);

        // DB 저장
        ScheduleRepository scheduleRepository = new ScheduleRepository(jdbcTemplate);
        Schedule saveSchedule = scheduleRepository.save(schedule);

        // Entity -> ResponseDto
        RegisterScheduleResDto registerScheduleResDto = new RegisterScheduleResDto(schedule);
        return registerScheduleResDto;
    }

    public List<InquiryScheduleResDto> inquirySchedule(Long id) {
        ScheduleRepository scheduleRepository = new ScheduleRepository(jdbcTemplate);
        return scheduleRepository.findOne(id);
    }

    public List<InquiryScheduleResDto> inquirySchedule(Long id, String date, int pageNum, int pageSize) {
        ScheduleRepository scheduleRepository = new ScheduleRepository(jdbcTemplate);
        return scheduleRepository.findByIdOrDate(id, date, pageNum, pageSize);
    }

    public UpdateScheduleReqDto updateSchedule(Long id, UpdateScheduleReqDto updateScheduleReqDto) {
        ScheduleRepository scheduleRepository = new ScheduleRepository(jdbcTemplate);
        // 해당 스케줄이 DB 에 존재하는지 확인
        Schedule schedule = scheduleRepository.findById(id);
        if(schedule != null) {
            // schedule 수정
            scheduleRepository.update(id,updateScheduleReqDto);
            return updateScheduleReqDto;
        } else {
            throw new IllegalArgumentException("선택한 일정이 존재하지 않습니다.");
        }
    }

    public Long deleteSchedule(Long id, DeleteScheduleReqDto deleteScheduleReqDto) {
        ScheduleRepository scheduleRepository = new ScheduleRepository(jdbcTemplate);
        // 해당 메모가 DB에 존재하는지 확인
        Schedule schedule = scheduleRepository.findById(id);
        if(schedule != null) {
            // schedule 삭제
            if (schedule.getPassword().equals(deleteScheduleReqDto.getPassword())) {
                scheduleRepository.delete(id);
                return id;
            } else {
                throw new IllegalArgumentException("비밀번호를 확인해주세요.");
            }
        } else {
            throw new IllegalArgumentException("선택한 메모는 존재하지 않습니다.");
        }
    }
}
