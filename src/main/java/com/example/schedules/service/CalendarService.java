package com.example.schedules.service;

import com.example.schedules.dto.CalendarRequestDto;
import com.example.schedules.dto.CalendarResponseDto;

import java.util.List;

public interface CalendarService {
    CalendarResponseDto create(CalendarRequestDto request);
    List<CalendarResponseDto> getAll();
    CalendarResponseDto getOne(Long id);
    CalendarResponseDto update(Long id, CalendarRequestDto request);
    void delete(Long id, String password);
}
