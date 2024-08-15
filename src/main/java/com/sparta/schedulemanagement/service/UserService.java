package com.sparta.schedulemanagement.service;

import com.sparta.schedulemanagement.dto.ScheduleDto.RegisterScheduleReqDto;
import com.sparta.schedulemanagement.dto.ScheduleDto.RegisterScheduleResDto;
import com.sparta.schedulemanagement.dto.UserDto.InquiryUserResDto;
import com.sparta.schedulemanagement.dto.UserDto.RegisterUserReqDto;
import com.sparta.schedulemanagement.dto.UserDto.RegisterUserResDto;
import com.sparta.schedulemanagement.dto.UserDto.UpdateUserReqDto;
import com.sparta.schedulemanagement.entity.Schedule;
import com.sparta.schedulemanagement.entity.User;
import com.sparta.schedulemanagement.repository.ScheduleRepository;
import com.sparta.schedulemanagement.repository.UserRepository;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class UserService {
    private final JdbcTemplate jdbcTemplate;

    public UserService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public RegisterUserResDto createUser(RegisterUserReqDto requestDto) {
        // RequestDto -> Entity
        User user = new User(requestDto);

        // DB 저장
        UserRepository userRepository = new UserRepository(jdbcTemplate);
        User saveUser = userRepository.save(user);

        // Entity -> ResponseDto
        RegisterUserResDto registerUserResDto = new RegisterUserResDto(user);
        return registerUserResDto;
    }

    public List<InquiryUserResDto> inquiryUser(Long id) {
        UserRepository userRepository = new UserRepository(jdbcTemplate);
        return userRepository.findOne(id);    }

    public UpdateUserReqDto updateUser(Long id, UpdateUserReqDto updateUserReqDto) {
        UserRepository userRepository = new UserRepository(jdbcTemplate);
        // 해당 스케줄이 DB 에 존재하는지 확인
        User user = userRepository.findById(id);
        if(user != null) {
            // user 수정
            userRepository.update(id, updateUserReqDto);
            return updateUserReqDto;
        } else {
            throw new IllegalArgumentException("선택한 일정이 존재하지 않습니다.");
        }
    }

    public Long deleteUser(Long id) {
        UserRepository userRepository = new UserRepository(jdbcTemplate);
        // 해당 메모가 DB에 존재하는지 확인
        User user = userRepository.findById(id);
        if (user != null) {
            // user 삭제
                userRepository.delete(id);
                return id;

        } else {
            throw new IllegalArgumentException("선택한 일정이 존재하지 않습니다.");
        }
    }
}
