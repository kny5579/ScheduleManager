package com.example.schedulemanager.repository.author;

import com.example.schedulemanager.dto.author.AuthorResponseDto;
import com.example.schedulemanager.entity.Author;

import java.time.LocalDateTime;
import java.util.Optional;

public interface AuthorRepository {
    AuthorResponseDto saveAuthor(Author author);

    Optional<Author> findAuthorById(Long id);

    AuthorResponseDto updateAuthor(Long id, String username, String email, LocalDateTime updatedDate);
}
