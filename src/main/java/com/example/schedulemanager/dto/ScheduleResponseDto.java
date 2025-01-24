package com.example.schedulemanager.dto;

import com.example.schedulemanager.entity.Schedule;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class ScheduleResponseDto {
    private Long id;
    private String username;
    private String contents;
    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;

    public ScheduleResponseDto(Long id, String username, String contents, LocalDateTime updatedDate) {
        this.id = id;
        this.username = username;
        this.contents = contents;
        this.updatedDate = updatedDate;
    }

    public ScheduleResponseDto(Schedule schedule) {
        this.id = schedule.getId();
        this.username = schedule.getUsername();
        this.contents = schedule.getContents();
        this.createdDate = schedule.getCreatedDate();
        this.updatedDate = schedule.getUpdatedDate();
    }
}
