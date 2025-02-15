package com.example.schedulemanager.repository.schedule;

import com.example.schedulemanager.dto.schedule.ScheduleResponseDto;
import com.example.schedulemanager.entity.Schedule;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface ScheduleRepository {

    ScheduleResponseDto saveSchedule(Schedule schedule);

    List<ScheduleResponseDto> findAllSchedule(LocalDateTime updatedDate, Long authorId);

    Optional<Schedule> findScheduleById(Long id);

    ScheduleResponseDto updateSchedule(Long id, String nickname, String contents, LocalDateTime updatedDate);

    int deleteSchedule(Long id);

    Long getTotalScheduleCnt();

    List<ScheduleResponseDto> findSchedulesByPage(int pageNum, int pageSize);
}
