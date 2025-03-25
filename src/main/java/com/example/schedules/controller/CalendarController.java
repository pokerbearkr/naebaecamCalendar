package com.example.schedules.controller;

import com.example.schedules.dto.CalendarRequestDto;
import com.example.schedules.dto.CalendarResponseDto;
import com.example.schedules.service.CalendarService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController //응답받을 데이터가 있으므로 RestController
@RequestMapping("/api/schedules")
public class CalendarController {

    private final CalendarService calendarService;

    public CalendarController(CalendarService calendarService) {
        this.calendarService = calendarService;
    }

    // 일정 생성
    @PostMapping
    public ResponseEntity<CalendarResponseDto> create(@Valid @RequestBody CalendarRequestDto request) {
        return ResponseEntity.ok(calendarService.create(request));
    }

    // 전체 일정 조회
    @GetMapping
    public ResponseEntity<List<CalendarResponseDto>> getAll() {
        return ResponseEntity.ok(calendarService.getAll());
    }

    // 특정 일정 조회
    @GetMapping("/{id}")
    public ResponseEntity<CalendarResponseDto> getOne(@PathVariable Long id) {
        return ResponseEntity.ok(calendarService.getOne(id));
    }

    // 일정 수정
    @PutMapping("/{id}")
    public ResponseEntity<CalendarResponseDto> update(@PathVariable Long id,
                                                      @Valid @RequestBody CalendarRequestDto request) {
        return ResponseEntity.ok(calendarService.update(id, request));
    }

    // 일정 삭제
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id,
                                       @RequestBody CalendarRequestDto request) {
        calendarService.delete(id, request.getPassword());
        return ResponseEntity.noContent().build();
    }
}
