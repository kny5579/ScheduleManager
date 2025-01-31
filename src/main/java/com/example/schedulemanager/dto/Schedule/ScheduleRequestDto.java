package com.example.schedulemanager.dto.Schedule;

import lombok.Getter;

@Getter
public class ScheduleRequestDto {
    private Long authorId;
    private String nickname;
    private String contents;
    private String password;
}
