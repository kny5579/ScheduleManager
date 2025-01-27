package com.example.schedulemanager.service.schedule;

import com.example.schedulemanager.dto.Schedule.ScheduleRequestDto;
import com.example.schedulemanager.dto.Schedule.ScheduleResponseDto;

import java.time.LocalDateTime;
import java.util.List;

public interface ScheduleService {
    ScheduleResponseDto saveSchedule(ScheduleRequestDto scheduleRequestDto);

    List<ScheduleResponseDto> findAllSchedule(LocalDateTime updatedDate, String username);

    ScheduleResponseDto findScheduleById(Long id);

    ScheduleResponseDto updateSchedule(Long id, ScheduleRequestDto scheduleRequestDto);

    void deleteSchedule(Long id, ScheduleRequestDto scheduleRequestDto);
}
