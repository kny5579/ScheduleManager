package com.example.schedulemanager.dto.author;

import com.example.schedulemanager.entity.Author;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class AuthorResponseDto {
    private Long id;
    private String username;
    private String email;
    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;

    public AuthorResponseDto(Author author) {
        this.id = author.getId();
        this.username = author.getUsername();
        this.email = author.getEmail();
        this.createdDate = author.getCreatedDate();
        this.updatedDate = author.getUpdatedDate();
    }
}
