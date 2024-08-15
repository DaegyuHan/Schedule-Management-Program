package com.sparta.schedulemanagement.repository;

import com.sparta.schedulemanagement.dto.ScheduleDto.InquiryScheduleResDto;
import com.sparta.schedulemanagement.dto.UserDto.InquiryUserResDto;
import com.sparta.schedulemanagement.dto.UserDto.UpdateUserReqDto;
import com.sparta.schedulemanagement.entity.Schedule;
import com.sparta.schedulemanagement.entity.User;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import java.sql.*;
import java.util.List;

public class UserRepository {
    private final JdbcTemplate jdbcTemplate;

    public UserRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    // User 등록
    public User save(User user) {
        // DB 저장
        KeyHolder keyHolder = new GeneratedKeyHolder(); // 기본 키를 반환받기 위한 객체

        String sql = "INSERT INTO user (user_name, email, created_date, updated_date) VALUES (?, ?, ?, ?)";
        jdbcTemplate.update(con -> {
                    PreparedStatement preparedStatement = con.prepareStatement(sql,
                            Statement.RETURN_GENERATED_KEYS);

                    preparedStatement.setString(1, user.getUserName());
                    preparedStatement.setString(2, user.getEmail());
                    preparedStatement.setTimestamp(3, user.getCreatedDate());
                    preparedStatement.setTimestamp(4, user.getUpdatedDate());
                    return preparedStatement;
                },
                keyHolder);

        // DB Insert 후 받아온 기본키 확인
        Long id = keyHolder.getKey().longValue();
        user.setUserId(id);

        return user;
    }

    public List<InquiryUserResDto> findOne(Long id) {
        // DB 조회
        String sql = "SELECT * FROM user WHERE user_id = ?";

        return jdbcTemplate.query(sql, new Object[]{id}, new RowMapper<InquiryUserResDto>() {
            @Override
            public InquiryUserResDto mapRow(ResultSet rs, int rowNum) throws SQLException {
                // SQL 의 결과로 받아온 Memo 데이터들을 MemoResponseDto 타입으로 변환해줄 메서드
                Long userId = rs.getLong("user_id");
                String userName = rs.getString("user_name");
                String email = rs.getString("email");
                Timestamp updatedDate = rs.getTimestamp("updated_date");
                return new InquiryUserResDto(userId, userName, email, updatedDate);
            }
        });
    }

    public void update(Long id, UpdateUserReqDto updateUserReqDto) {
        String sql = "UPDATE user SET user_name = ?, email = ?, updated_date = ? WHERE user_id = ?";
        jdbcTemplate.update(sql,
                updateUserReqDto.getUserName(),
                updateUserReqDto.getEmail(),
                updateUserReqDto.getUpdatedDate(),
                id
                );
    }

    public User findById(Long id) {
        // DB 조회
        String sql = "SELECT * FROM user WHERE user_id = ?";

        return jdbcTemplate.query(sql, resultSet -> {
            if (resultSet.next()) {
                User user = new User();
                user.setUserName(resultSet.getString("user_name"));
                user.setEmail(resultSet.getString("email"));
                return user;
            } else {
                return null;
            }
        }, id);    }

    public void delete(Long id) {
        String sql = "DELETE FROM user WHERE user_id = ?";
        jdbcTemplate.update(sql, id);
    }
}
