package com.example.schedulemanager.controller.author;

import com.example.schedulemanager.dto.Schedule.ScheduleResponseDto;
import com.example.schedulemanager.dto.author.AuthorRequestDto;
import com.example.schedulemanager.dto.author.AuthorResponseDto;
import com.example.schedulemanager.service.author.AuthorService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/author")
public class AuthorController {

    private final AuthorService authorService;

    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @PostMapping
    public ResponseEntity<AuthorResponseDto> createSchedule(@RequestBody AuthorRequestDto authorRequestDto) {
        return new ResponseEntity<>(authorService.saveAuthor(authorRequestDto), HttpStatus.CREATED);
    }
}
