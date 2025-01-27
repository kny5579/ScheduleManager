package com.example.schedulemanager.service.author;

import com.example.schedulemanager.dto.author.AuthorRequestDto;
import com.example.schedulemanager.dto.author.AuthorResponseDto;


public interface AuthorService {
    AuthorResponseDto saveAuthor(AuthorRequestDto authorRequestDto);

    AuthorResponseDto updateAuthor(Long id, AuthorRequestDto authorRequestDto);
}
