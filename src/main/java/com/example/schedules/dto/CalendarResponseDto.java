package com.example.schedules.dto;

import lombok.*;
import java.time.LocalDateTime;

/**
 * API 명세서에 맞춰서 Dto 작성
 */

@Getter
@Setter
@AllArgsConstructor
public class CalendarResponseDto {
    private Long id;
    private String title;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private String description;
    private String locate;
}
