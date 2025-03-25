package com.example.schedules.entity;

import lombok.*;
import java.time.LocalDateTime;

/**
 * Calendar Entity
 */

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class Calendar {
    private Long id;
    private String title;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private String description;
    private String locate;
    private String password;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private Long authorId;
}
