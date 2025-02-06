package com.example.schedulemanager.dto.schedule;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
public class ScheduleRequestDto {

    private Long authorId;

    private String nickname;

    @NotBlank(message = "필수 입력값입니다.")
    @Size(max = 200, message = "최대 200자까지 입력 가능")
    private String contents;

    @NotBlank(message = "필수 입력값입니다.")
    private String password;
}
