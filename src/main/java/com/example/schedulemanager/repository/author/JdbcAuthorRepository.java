package com.example.schedulemanager.repository.author;

import com.example.schedulemanager.dto.Schedule.ScheduleResponseDto;
import com.example.schedulemanager.dto.author.AuthorResponseDto;
import com.example.schedulemanager.entity.Author;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Timestamp;
import java.util.Map;

@Repository
public class JdbcAuthorRepository implements AuthorRepository{
    private final JdbcTemplate jdbcTemplate;

    public JdbcAuthorRepository(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public AuthorResponseDto saveAuthor(Author author) {
        SimpleJdbcInsert jdbcInsert = new SimpleJdbcInsert(jdbcTemplate);
        jdbcInsert.withTableName("author").usingGeneratedKeyColumns("id");
        Map<String, Object> parameters = Map.of(
                "username", author.getUsername(),
                "email", author.getEmail(),
                "createdDate", Timestamp.valueOf(author.getCreatedDate()),
                "updatedDate", Timestamp.valueOf(author.getUpdatedDate())
        );

        Number key = jdbcInsert.executeAndReturnKey(new MapSqlParameterSource(parameters));

        return new AuthorResponseDto(
                key.longValue(),
                author.getUsername(),
                author.getEmail(),
                author.getCreatedDate(),
                author.getUpdatedDate());
    }
}
