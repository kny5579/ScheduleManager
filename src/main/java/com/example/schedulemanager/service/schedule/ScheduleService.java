package com.example.schedulemanager.service.schedule;

import com.example.schedulemanager.dto.Schedule.ScheduleRequestDto;
import com.example.schedulemanager.dto.Schedule.ScheduleResponseDto;
import org.springframework.data.domain.Page;

import java.time.LocalDateTime;
import java.util.List;

public interface ScheduleService {
    ScheduleResponseDto saveSchedule(ScheduleRequestDto scheduleRequestDto);

    List<ScheduleResponseDto> findAllSchedule(LocalDateTime updatedDate, Long authorId);

    ScheduleResponseDto findScheduleById(Long id);

    ScheduleResponseDto updateSchedule(Long id, ScheduleRequestDto scheduleRequestDto);

    void deleteSchedule(Long id, ScheduleRequestDto scheduleRequestDto);

    Page<ScheduleResponseDto> findSchedulesByPage(int pageNum, int pageSize);
}
