package com.sparta.schedulemanagement.repository;

import com.sparta.schedulemanagement.dto.ScheduleDto.InquiryScheduleResDto;
import com.sparta.schedulemanagement.dto.ScheduleDto.UpdateScheduleReqDto;
import com.sparta.schedulemanagement.entity.Schedule;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ScheduleRepository {

    private final JdbcTemplate jdbcTemplate;

    public ScheduleRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    // Schedule 등록
    public Schedule save(Schedule schedule) {
        // DB 저장
        KeyHolder keyHolder = new GeneratedKeyHolder(); // 기본 키를 반환받기 위한 객체

        String sql = "INSERT INTO schedule (user_name, contents, password, created_date, updated_date) VALUES (?, ?, ?, ?, ?)";
        jdbcTemplate.update(con -> {
                    PreparedStatement preparedStatement = con.prepareStatement(sql,
                            Statement.RETURN_GENERATED_KEYS);

                    preparedStatement.setString(1, schedule.getUserName());
                    preparedStatement.setString(2, schedule.getContents());
                    preparedStatement.setString(3, schedule.getPassword());
                    preparedStatement.setTimestamp(4, schedule.getCreatedDate());
                    preparedStatement.setTimestamp(5, schedule.getUpdatedDate());
                    return preparedStatement;
                },
                keyHolder);

        // DB Insert 후 받아온 기본키 확인
        Long id = keyHolder.getKey().longValue();
        schedule.setScheduleId(id);

        return schedule;
    }

    // Schedule 조회
    public List<InquiryScheduleResDto> findOne(Long id) {
        // DB 조회
        String sql = "SELECT * FROM schedule WHERE schedule_id = ?";

        return jdbcTemplate.query(sql, new Object[]{id}, new RowMapper<InquiryScheduleResDto>() {
            @Override
            public InquiryScheduleResDto mapRow(ResultSet rs, int rowNum) throws SQLException {
                // SQL 의 결과로 받아온 Memo 데이터들을 MemoResponseDto 타입으로 변환해줄 메서드
                Long scheduleId = rs.getLong("schedule_id");
                String username = rs.getString("user_name");
                String contents = rs.getString("contents");
                Timestamp updatedDate = rs.getTimestamp("updated_date");
                return new InquiryScheduleResDto(scheduleId, username, contents, updatedDate);
            }
        });
    }

    // Schedule 목록 조회
    public List<InquiryScheduleResDto> findByNameOrDate(String name, String date) {
        String sql = "SELECT schedule_id, user_name, contents, updated_date FROM schedule WHERE 1=1";

        List<Object> params = new ArrayList<>();

        // name이 있을 경우 쿼리에 추가
        if (name != null && !name.isEmpty()) {
            sql += " AND user_name = ?";
            params.add(name);
        }

        // date가 있을 경우 쿼리에 추가
        if (date != null && !date.isEmpty()) {
            sql += " AND DATE(updated_date) = ?";
            params.add(date);
        }

        sql += " ORDER BY updated_date DESC";

        return jdbcTemplate.query(sql, params.toArray(), new RowMapper<InquiryScheduleResDto>() {
            @Override
            public InquiryScheduleResDto mapRow(ResultSet rs, int rowNum) throws SQLException {
                Long scheduleId = rs.getLong("schedule_id");
                String userName = rs.getString("user_name");
                String contents = rs.getString("contents");
                Timestamp updatedDate = rs.getTimestamp("updated_date");
                return new InquiryScheduleResDto(scheduleId, userName, contents, updatedDate);
            }
        });
    }

    // Schedule 수정
    public void update(Long id, UpdateScheduleReqDto updateScheduleReqDto) {
        String sql = "UPDATE schedule SET user_name = ?, contents = ?, updated_date = ? WHERE schedule_id = ? AND password = ?";
        int updating = jdbcTemplate.update(sql,
                updateScheduleReqDto.getUserName(),
                updateScheduleReqDto.getContents(),
                updateScheduleReqDto.getUpdatedDate(),
                id,
                updateScheduleReqDto.getPassword());

        if (updating == 0) {
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }
    }

    // Schedule 삭제
    public void delete(Long id) {
        String sql = "DELETE FROM schedule WHERE schedule_id = ?";
        jdbcTemplate.update(sql, id);
    }

    public Schedule findById(Long id) {
        // DB 조회
        String sql = "SELECT * FROM schedule WHERE schedule_id = ?";

        return jdbcTemplate.query(sql, resultSet -> {
            if (resultSet.next()) {
                Schedule schedule = new Schedule();
                schedule.setUserName(resultSet.getString("user_name"));
                schedule.setContents(resultSet.getString("contents"));
                schedule.setPassword(resultSet.getString("password"));
                return schedule;
            } else {
                return null;
            }
        }, id);
    }


}


