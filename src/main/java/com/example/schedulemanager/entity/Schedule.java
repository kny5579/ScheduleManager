package com.example.schedulemanager.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class Schedule {
    private Long id;
    private Long authorId;
    private String password;
    private String contents;
    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;

    public Schedule(Long authorId, String password, String contents) {
        this.authorId = authorId;
        this.password = password;
        this.contents = contents;
        this.createdDate = LocalDateTime.now();
        this.updatedDate = LocalDateTime.now();
    }

}
