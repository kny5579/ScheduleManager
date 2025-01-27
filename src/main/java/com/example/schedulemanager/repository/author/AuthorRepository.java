package com.example.schedulemanager.repository.author;

import com.example.schedulemanager.dto.author.AuthorResponseDto;
import com.example.schedulemanager.entity.Author;

public interface AuthorRepository {
    AuthorResponseDto saveAuthor(Author author);
}
