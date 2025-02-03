package com.example.schedulemanager.controller.schedule;


import com.example.schedulemanager.dto.Schedule.ScheduleRequestDto;
import com.example.schedulemanager.dto.Schedule.ScheduleResponseDto;
import com.example.schedulemanager.service.schedule.ScheduleService;
import jakarta.validation.Valid;
import org.springframework.data.web.PagedModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/schedules")
@Validated
public class ScheduleController {

    private final ScheduleService scheduleService;

    public ScheduleController(ScheduleService scheduleService) {
        this.scheduleService = scheduleService;
    }

    @PostMapping
    public ResponseEntity<ScheduleResponseDto> createSchedule(@Valid @RequestBody ScheduleRequestDto scheduleRequestDto) {
        return new ResponseEntity<>(scheduleService.saveSchedule(scheduleRequestDto), HttpStatus.CREATED);
    }

    @GetMapping("/find")
    public List<ScheduleResponseDto> findAllSchedule(
            @RequestParam(value = "updatedDate", required = false) String updatedStringDate,
            @RequestParam(value = "authorId", required = false) Long authorId
    ) {
        return scheduleService.findAllSchedule(getLocalDateTime(updatedStringDate), authorId);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ScheduleResponseDto> findScheduleById(@PathVariable Long id) {
        return new ResponseEntity<>(scheduleService.findScheduleById(id), HttpStatus.OK);
    }

    @GetMapping("/paging")
    public ResponseEntity<PagedModel<ScheduleResponseDto>> findSchedulesByPage(
            @RequestParam(value = "pageNum") int pageNum,
            @RequestParam(value = "pageSize") int pageSize
    ) {
        return ResponseEntity.ok(new PagedModel<>(scheduleService.findSchedulesByPage(pageNum, pageSize)));
    }


    @PatchMapping("/{id}")
    public ResponseEntity<ScheduleResponseDto> updateSchedule(@PathVariable Long id, @Valid @RequestBody ScheduleRequestDto scheduleRequestDto) {
        return new ResponseEntity<>(scheduleService.updateSchedule(id, scheduleRequestDto), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteSchedule(@PathVariable Long id, @Valid @RequestBody ScheduleRequestDto scheduleRequestDto) {
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
