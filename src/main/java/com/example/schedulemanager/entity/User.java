package com.example.schedulemanager.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class User {
    private Long id;
    private String username;
    private String email;
    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;
}
