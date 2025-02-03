package com.example.schedulemanager.dto.author;

import jakarta.validation.constraints.Pattern;
import lombok.Getter;

@Getter
public class AuthorRequestDto {

    private String username;

    @Pattern(
            regexp = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$",
            message = "이메일 형식이 올바르지 않습니다."
    )
    private String email;
}
