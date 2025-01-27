package com.example.schedulemanager.dto.Schedule;

import lombok.Getter;

@Getter
public class ScheduleRequestDto {
    private Long authorId;
    private String username;
    private String contents;
    private String password;
}
