package com.example.schedulemanager.repository;

import com.example.schedulemanager.dto.ScheduleResponseDto;
import com.example.schedulemanager.entity.Schedule;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface ScheduleRepository {

    ScheduleResponseDto saveSchedule(Schedule schedule);
    List<ScheduleResponseDto> findAllSchedule(LocalDateTime updatedDate,String username);
    Optional<Schedule> findScheduleById(Long id);
    ScheduleResponseDto updateSchedule(Long id, String username, String contents,LocalDateTime updatedDate);
    int deleteSchedule(Long id);

}
