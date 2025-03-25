package com.example.schedules.service;

import com.example.schedules.dto.CalendarRequestDto;
import com.example.schedules.dto.CalendarResponseDto;
import com.example.schedules.entity.Author;
import com.example.schedules.entity.Calendar;
import com.example.schedules.repository.AuthorRepository;
import com.example.schedules.repository.CalendarRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CalendarServiceImpl implements CalendarService {

    private final CalendarRepository calendarRepository;
    private final AuthorRepository authorRepository;

    public CalendarServiceImpl(CalendarRepository calendarRepository, AuthorRepository authorRepository) {
        this.calendarRepository = calendarRepository;
        this.authorRepository = authorRepository;
    }

    @Override
    public CalendarResponseDto create(CalendarRequestDto request) {
        Author author = authorRepository.findById(request.getAuthorId())
                .orElseThrow(() -> new RuntimeException("작성자를 찾을 수 없습니다."));

        Calendar calendar = new Calendar();
        calendar.setTitle(request.getTitle());
        calendar.setStartDate(request.getStartDate());
        calendar.setEndDate(request.getEndDate());
        calendar.setDescription(request.getDescription());
        calendar.setLocate(request.getLocate());
        calendar.setPassword(request.getPassword());
        calendar.setAuthorId(author.getId());

        Long id = calendarRepository.save(calendar);
        calendar.setId(id);

        return new CalendarResponseDto(id, calendar.getTitle(), calendar.getStartDate(), calendar.getEndDate(), calendar.getDescription(), calendar.getLocate());
    }

    @Override
    public List<CalendarResponseDto> getAll() {
        return calendarRepository.findAll().stream()
                .map(c -> new CalendarResponseDto(c.getId(), c.getTitle(), c.getStartDate(), c.getEndDate(), c.getDescription(), c.getLocate()))
                .collect(Collectors.toList());
    }

    @Override
    public CalendarResponseDto getOne(Long id) {
        Calendar c = calendarRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("일정을 찾을 수 없습니다."));
        return new CalendarResponseDto(c.getId(), c.getTitle(), c.getStartDate(), c.getEndDate(), c.getDescription(), c.getLocate());
    }

    @Override
    public CalendarResponseDto update(Long id, CalendarRequestDto request) {
        Calendar calendar = calendarRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("일정을 찾을 수 없습니다."));

        if (!calendar.getPassword().equals(request.getPassword())) {
            throw new SecurityException("비밀번호가 일치하지 않습니다.");
        }

        calendar.setTitle(request.getTitle());
        calendar.setStartDate(request.getStartDate());
        calendar.setEndDate(request.getEndDate());
        calendar.setDescription(request.getDescription());
        calendar.setLocate(request.getLocate());
        calendarRepository.update(calendar);

        return new CalendarResponseDto(calendar.getId(), calendar.getTitle(), calendar.getStartDate(), calendar.getEndDate(), calendar.getDescription(), calendar.getLocate());
    }

    @Override
    public void delete(Long id, String password) {
        Calendar calendar = calendarRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("일정을 찾을 수 없습니다."));

        if (!calendar.getPassword().equals(password)) {
            throw new SecurityException("비밀번호가 일치하지 않습니다.");
        }

        calendarRepository.delete(id);
    }
}
