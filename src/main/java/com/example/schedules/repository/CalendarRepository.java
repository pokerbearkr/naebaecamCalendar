package com.example.schedules.repository;

import com.example.schedules.entity.Calendar;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
public class CalendarRepository {
    private final JdbcTemplate jdbcTemplate;

    public CalendarRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public Long save(Calendar calendar) {
        String sql = "INSERT INTO calendar (title, start_date, end_date, description, locate, password, author_id, created_at, updated_at) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, NOW(), NOW())";

        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, calendar.getTitle());
            ps.setTimestamp(2, Timestamp.valueOf(calendar.getStartDate()));
            ps.setTimestamp(3, Timestamp.valueOf(calendar.getEndDate()));
            ps.setString(4, calendar.getDescription());
            ps.setString(5, calendar.getLocate());
            ps.setString(6, calendar.getPassword());
            ps.setLong(7, calendar.getAuthorId());
            return ps;
        }, keyHolder);

        Map<String, Object> keys = keyHolder.getKeys();
        return (Long) keys.get("ID");
    }

    public List<Calendar> findAll() {
        String sql = "SELECT * FROM calendar ORDER BY created_at DESC";
        return jdbcTemplate.query(sql, rowMapper);
    }

    public Optional<Calendar> findById(Long id) {
        String sql = "SELECT * FROM calendar WHERE id = ?";
        return jdbcTemplate.query(sql, rowMapper, id).stream().findFirst();
    }

    public int update(Calendar calendar) {
        String sql = "UPDATE calendar SET title = ?, start_date = ?, end_date = ?, description = ?, locate = ?, updated_at = NOW() WHERE id = ?";
        return jdbcTemplate.update(sql,
                calendar.getTitle(),
                calendar.getStartDate(),
                calendar.getEndDate(),
                calendar.getDescription(),
                calendar.getLocate(),
                calendar.getId());
    }

    public int delete(Long id) {
        return jdbcTemplate.update("DELETE FROM calendar WHERE id = ?", id);
    }

    public final RowMapper<Calendar> rowMapper = (rs, rowNum) -> new Calendar(
            rs.getLong("id"),
            rs.getString("title"),
            rs.getTimestamp("start_date").toLocalDateTime(),
            rs.getTimestamp("end_date").toLocalDateTime(),
            rs.getString("description"),
            rs.getString("locate"),
            rs.getString("password"),
            rs.getTimestamp("created_at").toLocalDateTime(),
            rs.getTimestamp("updated_at").toLocalDateTime(),
            rs.getLong("author_id")
    );

    public String getPasswordById(Long id) {
        return jdbcTemplate.queryForObject("SELECT password FROM calendar WHERE id = ?", String.class, id);
    }
}
