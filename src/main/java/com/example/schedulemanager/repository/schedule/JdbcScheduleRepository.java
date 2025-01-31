package com.example.schedulemanager.repository.schedule;

import com.example.schedulemanager.dto.Schedule.ScheduleResponseDto;
import com.example.schedulemanager.entity.Schedule;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
public class JdbcScheduleRepository implements ScheduleRepository {
    private final JdbcTemplate jdbcTemplate;

    public JdbcScheduleRepository(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public ScheduleResponseDto saveSchedule(Schedule schedule) {
        SimpleJdbcInsert jdbcInsert = new SimpleJdbcInsert(jdbcTemplate);
        jdbcInsert.withTableName("schedule").usingGeneratedKeyColumns("id");

        Map<String, Object> parameters = Map.of(
                "username", schedule.getUsername(),
                "password", schedule.getPassword(),
                "contents", schedule.getContents(),
                "createdDate", Timestamp.valueOf(schedule.getCreatedDate()),
                "updatedDate", Timestamp.valueOf(schedule.getUpdatedDate()),
                "author_id", schedule.getAuthorId()
        );

        Number key = jdbcInsert.executeAndReturnKey(new MapSqlParameterSource(parameters));

        return new ScheduleResponseDto(
                key.longValue(),
                schedule.getUsername(),
                schedule.getContents(),
                schedule.getCreatedDate(),
                schedule.getUpdatedDate());
    }

    @Override
    public List<ScheduleResponseDto> findAllSchedule(LocalDateTime updatedDate, Long authorId) {
        String sql = "SELECT * FROM schedule WHERE 1=1";
        List<Object> parameters = new ArrayList<>();

        // 수정일, 작성자명 기준 조회 조건문
        if (updatedDate != null) {
            sql += " AND DATE(updated_date) = ?";
            parameters.add(updatedDate);
        }
        if (authorId != null && authorId > 0) {
            sql += " AND author_id = ?";
            parameters.add(authorId);
        }

        sql += " ORDER BY updated_date DESC";

        return jdbcTemplate.query(sql, parameters.toArray(), scheduleResponseRowMapper());
    }

    @Override
    public Optional<Schedule> findScheduleById(Long id) {
        String sql = "SELECT * FROM schedule WHERE id = ?";
        return jdbcTemplate.query(sql, scheduleRowMapper(), id).stream().findAny();
    }

    @Override
    public ScheduleResponseDto updateSchedule(Long id, String username, String contents, LocalDateTime updatedDate) {
        String sql = "UPDATE schedule SET username = ?, contents = ?, updated_date = ? WHERE id = ?";
        jdbcTemplate.update(sql, username, contents, Timestamp.valueOf(updatedDate), id);
        return findScheduleById(id)
                .map(ScheduleResponseDto::new)
                .orElseThrow(() -> new IllegalArgumentException("id 불일치: " + id));
    }

    @Override
    public int deleteSchedule(Long id) {
        String sql = "DELETE FROM schedule WHERE id = ?";
        return jdbcTemplate.update(sql, id);
    }

    @Override
    public Long getTotalScheduleCnt() {
        String sql = "SELECT COUNT(*) FROM schedule";
        return jdbcTemplate.queryForObject(sql, Long.class);
    }

    @Override
    public List<ScheduleResponseDto> findSchedulesByPage(int pageNum, int pageSize) {
        String sql = "SELECT s.id, s.contents, s.created_date, s.updated_date, a.username AS author_name " +
                "FROM schedule s " +
                "JOIN author a ON s.author_id = a.id " +
                "ORDER BY s.updated_date DESC " +
                "LIMIT ? OFFSET ?";

        int offset = (pageNum - 1) * pageSize;

        return jdbcTemplate.query(sql, new Object[]{pageSize, offset}, (rs, rowNum) -> {
            return new ScheduleResponseDto(
                    rs.getLong("id"),
                    rs.getString("author_name"),
                    rs.getString("contents"),
                    rs.getTimestamp("created_date").toLocalDateTime(),
                    rs.getTimestamp("updated_date").toLocalDateTime()
            );
        });
    }

    private RowMapper<ScheduleResponseDto> scheduleResponseRowMapper() {
        return (rs, rowNum) -> new ScheduleResponseDto(
                rs.getLong("id"),
                rs.getString("username"),
                rs.getString("contents"),
                rs.getTimestamp("created_date").toLocalDateTime(),
                rs.getTimestamp("updated_date").toLocalDateTime()
        );
    }

    private RowMapper<Schedule> scheduleRowMapper() {
        return (rs, rowNum) -> new Schedule(
                rs.getLong("id"),
                rs.getLong("author_id"),
                rs.getString("username"),
                rs.getString("password"),
                rs.getString("contents"),
                rs.getTimestamp("created_date").toLocalDateTime(),
                rs.getTimestamp("updated_date").toLocalDateTime()
        );
    }
}
