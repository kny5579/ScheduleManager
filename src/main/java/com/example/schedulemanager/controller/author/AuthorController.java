package com.example.schedulemanager.controller.author;

import com.example.schedulemanager.dto.author.AuthorRequestDto;
import com.example.schedulemanager.dto.author.AuthorResponseDto;
import com.example.schedulemanager.service.author.AuthorService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/author")
public class AuthorController {

    private final AuthorService authorService;

    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @PostMapping
    public ResponseEntity<AuthorResponseDto> createAuthor(@Valid @RequestBody AuthorRequestDto authorRequestDto) {
        return new ResponseEntity<>(authorService.saveAuthor(authorRequestDto), HttpStatus.CREATED);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<AuthorResponseDto> updateAuthor(@PathVariable Long id, @Valid @RequestBody AuthorRequestDto authorRequestDto) {
        return new ResponseEntity<>(authorService.updateAuthor(id, authorRequestDto), HttpStatus.OK);
    }
}
