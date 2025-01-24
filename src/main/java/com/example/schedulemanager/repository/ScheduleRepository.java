package com.example.schedulemanager.repository;

import com.example.schedulemanager.dto.ScheduleResponseDto;
import com.example.schedulemanager.entity.Schedule;
import org.springframework.stereotype.Repository;

public interface ScheduleRepository {

    ScheduleResponseDto saveSchedule(Schedule schedule);
}
