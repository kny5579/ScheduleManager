package com.example.schedulemanager.service.author;

import com.example.schedulemanager.dto.author.AuthorRequestDto;
import com.example.schedulemanager.dto.author.AuthorResponseDto;
import com.example.schedulemanager.entity.Author;
import com.example.schedulemanager.exception.NotFoundInformationException;
import com.example.schedulemanager.repository.author.AuthorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepository authorRepository;

    @Override
    public AuthorResponseDto saveAuthor(AuthorRequestDto authorRequestDto) {
        Author author = new Author(
                authorRequestDto.getUsername(),
                authorRequestDto.getEmail()
        );
        return authorRepository.saveAuthor(author);
    }

    @Override
    public AuthorResponseDto updateAuthor(Long id, AuthorRequestDto authorRequestDto) {
        Optional<Author> existingAuthor = authorRepository.findAuthorById(id);
        if (existingAuthor.isEmpty()) {
            throw new NotFoundInformationException("id가 올바르지 않습니다: " + id);
        }
        LocalDateTime updatedDate = LocalDateTime.now();
        return authorRepository.updateAuthor(
                id,
                authorRequestDto.getUsername(),
                authorRequestDto.getEmail(),
                updatedDate);
    }

}
