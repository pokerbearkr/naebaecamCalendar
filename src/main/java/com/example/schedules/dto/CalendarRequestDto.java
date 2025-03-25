package com.example.schedules.dto;

import jakarta.validation.constraints.*;
import lombok.Getter;

import java.time.LocalDateTime;

/**
 * 요청 보내는 RequestDto
 */
@Getter
public class CalendarRequestDto {
    @NotBlank
    @Size(max = 200)
    private String title;

    @NotNull
    private LocalDateTime startDate;

    @NotNull
    private LocalDateTime endDate;

    private String description;
    private String locate;

    @NotBlank
    private String password;

    @NotNull
    private Long authorId;
}
