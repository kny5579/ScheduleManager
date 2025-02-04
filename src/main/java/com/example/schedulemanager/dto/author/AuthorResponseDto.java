package com.example.schedulemanager.dto.author;

import com.example.schedulemanager.entity.Author;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class AuthorResponseDto {
    private final Long id;
    private final String username;
    private final String email;
    private final LocalDateTime createdDate;
    private final LocalDateTime updatedDate;

    public AuthorResponseDto(Author author) {
        this.id = author.getId();
        this.username = author.getUsername();
        this.email = author.getEmail();
        this.createdDate = author.getCreatedDate();
        this.updatedDate = author.getUpdatedDate();
    }
}
