package com.example.schedulemanager.service.author;

import com.example.schedulemanager.dto.author.AuthorRequestDto;
import com.example.schedulemanager.dto.author.AuthorResponseDto;
import com.example.schedulemanager.entity.Author;
import com.example.schedulemanager.repository.author.AuthorRepository;
import org.springframework.stereotype.Service;

@Service
public class AuthorServiceImpl implements AuthorService{

    private final AuthorRepository authorRepository;

    public AuthorServiceImpl(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @Override
    public AuthorResponseDto saveAuthor(AuthorRequestDto authorRequestDto) {
        Author author = new Author(
                authorRequestDto.getUsername(),
                authorRequestDto.getEmail()
        );
        return authorRepository.saveAuthor(author);
    }
}
