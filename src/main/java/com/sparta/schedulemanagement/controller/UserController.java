package com.sparta.schedulemanagement.controller;

import com.sparta.schedulemanagement.dto.ScheduleDto.DeleteScheduleReqDto;
import com.sparta.schedulemanagement.dto.UserDto.InquiryUserResDto;
import com.sparta.schedulemanagement.dto.UserDto.RegisterUserReqDto;
import com.sparta.schedulemanagement.dto.UserDto.RegisterUserResDto;
import com.sparta.schedulemanagement.dto.UserDto.UpdateUserReqDto;
import com.sparta.schedulemanagement.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class UserController {
    private UserService userService;


    private JdbcTemplate jdbcTemplate;

    public UserController(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        userService = new UserService(jdbcTemplate);
    }

    @PostMapping("/user")
    public RegisterUserResDto createUser(@RequestBody RegisterUserReqDto requestDto) {
        return userService.createUser(requestDto);
    }

    @GetMapping("/user/{id}")
    public List<InquiryUserResDto> inquryUser(@PathVariable Long id) {
        return userService.inquiryUser(id);
    }

    @PutMapping("/user")
    public UpdateUserReqDto updateUser(@RequestParam Long id,@RequestBody UpdateUserReqDto updateUserReqDto) {
        return userService.updateUser(id, updateUserReqDto);
    }

    @PostMapping("/user/{id}")
    public Long deleteUser(@PathVariable Long id) {
        return userService.deleteUser(id);
    }
}
