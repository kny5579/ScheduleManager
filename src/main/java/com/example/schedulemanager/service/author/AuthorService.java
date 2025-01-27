package com.example.schedulemanager.service.author;

import com.example.schedulemanager.dto.Schedule.ScheduleRequestDto;
import com.example.schedulemanager.dto.Schedule.ScheduleResponseDto;
import com.example.schedulemanager.dto.author.AuthorRequestDto;
import com.example.schedulemanager.dto.author.AuthorResponseDto;
import org.springframework.stereotype.Service;


public interface AuthorService {
    AuthorResponseDto saveAuthor(AuthorRequestDto authorRequestDto);
}
