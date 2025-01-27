package com.example.schedulemanager.repository.author;

import com.example.schedulemanager.dto.author.AuthorResponseDto;
import com.example.schedulemanager.entity.Author;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.Optional;

@Repository
public class JdbcAuthorRepository implements AuthorRepository {
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

    @Override
    public Optional<Author> findAuthorById(Long id) {
        String sql = "SELECT * FROM author WHERE id = ?";
        return jdbcTemplate.query(sql, authorRowMapper(), id).stream().findAny();
    }

    @Override
    public AuthorResponseDto updateAuthor(Long id, String username, String email, LocalDateTime updatedDate) {
        String sql = "UPDATE author SET username = ?, email = ?, updated_date = ? WHERE id = ?";
        jdbcTemplate.update(sql, username, email, Timestamp.valueOf(updatedDate), id);
        return findAuthorById(id)
                .map(AuthorResponseDto::new)
                .orElseThrow(() -> new IllegalArgumentException("id 불일치: " + id));
    }


    private RowMapper<Author> authorRowMapper() {
        return (rs, rowNum) -> new Author(
                rs.getLong("id"),
                rs.getString("username"),
                rs.getString("email"),
                rs.getTimestamp("created_date").toLocalDateTime(),
                rs.getTimestamp("updated_date").toLocalDateTime()
        );
    }
}
