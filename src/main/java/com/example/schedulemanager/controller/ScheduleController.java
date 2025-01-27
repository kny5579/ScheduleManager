package com.example.schedulemanager.controller;


import com.example.schedulemanager.dto.ScheduleRequestDto;
import com.example.schedulemanager.dto.ScheduleResponseDto;
import com.example.schedulemanager.service.ScheduleService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/schedules")
public class ScheduleController {

    private final ScheduleService scheduleService;

    public ScheduleController(ScheduleService scheduleService) {
        this.scheduleService = scheduleService;
    }

    @PostMapping
    public ResponseEntity<ScheduleResponseDto> createSchedule(@RequestBody ScheduleRequestDto scheduleRequestDto) {
        return new ResponseEntity<>(scheduleService.saveSchedule(scheduleRequestDto), HttpStatus.CREATED);
    }

    @GetMapping("/find")
    public List<ScheduleResponseDto> findAllSchedule(
            @RequestParam(value = "updatedDate", required = false) String updatedStringDate,
            @RequestParam(value = "username", required = false) String username
    ) {
        return scheduleService.findAllSchedule(getLocalDateTime(updatedStringDate), username);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ScheduleResponseDto> findScheduleById(@PathVariable Long id) {
        return new ResponseEntity<>(scheduleService.findScheduleById(id), HttpStatus.OK);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<ScheduleResponseDto> updateSchedule(@PathVariable Long id, @RequestBody ScheduleRequestDto scheduleRequestDto) {
        return new ResponseEntity<>(scheduleService.updateSchedule(id, scheduleRequestDto), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteSchedule(@PathVariable Long id, @RequestBody ScheduleRequestDto scheduleRequestDto) {
        scheduleService.deleteSchedule(id, scheduleRequestDto);
        return new ResponseEntity<>("삭제되었습니다.", HttpStatus.OK);
    }

    private LocalDateTime getLocalDateTime(String updatedStringDate) {
        //param으로 들어온 날짜를 데이터 타입에 맞게 파싱
        LocalDateTime updatedDate = null;
        if (updatedStringDate != null && !updatedStringDate.isEmpty()) {
            updatedDate = LocalDateTime.parse(updatedStringDate + "T00:00:00");
        }
        return updatedDate;
    }
}
