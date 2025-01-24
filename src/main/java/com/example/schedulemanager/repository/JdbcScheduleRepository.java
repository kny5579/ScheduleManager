package com.example.schedulemanager.repository;

import com.example.schedulemanager.dto.ScheduleResponseDto;
import com.example.schedulemanager.entity.Schedule;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.*;

@Repository
public class JdbcScheduleRepository implements ScheduleRepository{
    private final JdbcTemplate jdbcTemplate;

    public JdbcScheduleRepository(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public ScheduleResponseDto saveSchedule(Schedule schedule) {
        SimpleJdbcInsert jdbcInsert = new SimpleJdbcInsert(jdbcTemplate);
        jdbcInsert.withTableName("schedule").usingGeneratedKeyColumns("id");

        Map<String, Object> parameters = new HashMap<>();
        parameters.put("username",schedule.getUsername());
        parameters.put("password",schedule.getPassword());
        parameters.put("contents",schedule.getContents());
        parameters.put("createdDate", Timestamp.valueOf(schedule.getCreatedDate()));
        parameters.put("updatedDate", Timestamp.valueOf(schedule.getUpdatedDate()));

        Number key = jdbcInsert.executeAndReturnKey(new MapSqlParameterSource(parameters));

        return new ScheduleResponseDto(key.longValue(),schedule.getUsername(),schedule.getContents(),schedule.getCreatedDate(),schedule.getUpdatedDate());

    }

    @Override
    public List<ScheduleResponseDto> findAllSchedule(LocalDateTime updatedDate,String username) {
        String sql ="select * from schedule where 1=1";
        List<Object> parameters = new ArrayList<>();

        // 수정일, 작성자명 기준 조회 조건문
        if(updatedDate!=null) {
            sql += " AND DATE(updated_date) = ?";
            parameters.add(updatedDate);
        }
        if(username != null && !username.isEmpty()) {
            sql += " AND username = ?";
            parameters.add(username);
        }

        sql += " order by updated_date desc";

        return jdbcTemplate.query(sql, parameters.toArray(),scheduleRowMapper());

    }

    @Override
    public Optional<Schedule> findScheduleById(Long id) {
        List<Schedule> result = jdbcTemplate.query("select * from schedule where id = ?", scheduleRowMapperV2(), id);
        return result.stream().findAny();
    }

    private RowMapper<ScheduleResponseDto> scheduleRowMapper() {
        return new RowMapper<ScheduleResponseDto>() {
            @Override
            public ScheduleResponseDto mapRow(ResultSet rs, int rowNum) throws SQLException {
                return new ScheduleResponseDto(
                        rs.getLong("id"),
                        rs.getString("username"),
                        rs.getString("contents"),
                        rs.getTimestamp("created_date").toLocalDateTime(),
                        rs.getTimestamp("updated_date").toLocalDateTime()
                );
            }
        };
    }

    private RowMapper<Schedule> scheduleRowMapperV2() {
        return new RowMapper<Schedule>() {
            @Override
            public Schedule mapRow(ResultSet rs, int rowNum) throws SQLException {
                return new Schedule(
                        rs.getLong("id"),
                        rs.getString("username"),
                        rs.getString("password"),
                        rs.getString("contents"),
                        rs.getTimestamp("created_date").toLocalDateTime(),
                        rs.getTimestamp("updated_date").toLocalDateTime()
                );
            }
        };
    }


}
