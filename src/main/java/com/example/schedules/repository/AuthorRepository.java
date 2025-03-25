package com.example.schedules.repository;

import com.example.schedules.entity.Author;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class AuthorRepository {
    private final JdbcTemplate jdbcTemplate;

    public AuthorRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public Optional<Author> findById(Long id) {
        String sql = "SELECT * FROM author WHERE id = ?";
        return jdbcTemplate.query(sql, rowMapper, id).stream().findFirst();
    }

    private final RowMapper<Author> rowMapper = (rs, rowNum) -> new Author(
            rs.getLong("id"),
            rs.getString("name"),
            rs.getString("email"),
            rs.getTimestamp("created_at").toLocalDateTime(),
            rs.getTimestamp("updated_at").toLocalDateTime()
    );
}
