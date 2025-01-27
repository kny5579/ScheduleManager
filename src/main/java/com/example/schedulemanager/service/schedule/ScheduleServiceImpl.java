package com.example.schedulemanager.service.schedule;

import com.example.schedulemanager.dto.Schedule.ScheduleRequestDto;
import com.example.schedulemanager.dto.Schedule.ScheduleResponseDto;
import com.example.schedulemanager.entity.Schedule;
import com.example.schedulemanager.repository.schedule.ScheduleRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ScheduleServiceImpl implements ScheduleService {

    private final ScheduleRepository scheduleRepository;

    public ScheduleServiceImpl(ScheduleRepository scheduleRepository) {
        this.scheduleRepository = scheduleRepository;
    }

    @Override
    public ScheduleResponseDto saveSchedule(ScheduleRequestDto scheduleRequestDto) {
        Schedule schedule = new Schedule(
                scheduleRequestDto.getAuthorId(),
                scheduleRequestDto.getUsername(),
                scheduleRequestDto.getPassword(),
                scheduleRequestDto.getContents());
        return scheduleRepository.saveSchedule(schedule);
    }

    @Override
    public List<ScheduleResponseDto> findAllSchedule(LocalDateTime updatedDate, Long authorId) {
        return scheduleRepository.findAllSchedule(updatedDate, authorId);
    }

    @Override
    public ScheduleResponseDto findScheduleById(Long id) {
        return scheduleRepository.findScheduleById(id)
                .map(ScheduleResponseDto::new)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "id 불일치: " + id));
    }

    @Override
    public ScheduleResponseDto updateSchedule(Long id, ScheduleRequestDto scheduleRequestDto) {
        checkPasswordMatch(getScheduleById(id), scheduleRequestDto.getPassword());
        LocalDateTime updatedDate = LocalDateTime.now();
        return scheduleRepository.updateSchedule(
                id,
                scheduleRequestDto.getUsername(),
                scheduleRequestDto.getContents(),
                updatedDate);
    }

    @Override
    public void deleteSchedule(Long id, ScheduleRequestDto scheduleRequestDto) {
        checkPasswordMatch(getScheduleById(id), scheduleRequestDto.getPassword());
        if (scheduleRepository.deleteSchedule(id) == 0)
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "삭제 불가");
    }

    private Schedule getScheduleById(Long id) {
        return scheduleRepository.findScheduleById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "id 불일치: " + id));
    }

    private void checkPasswordMatch(Schedule schedule, String password) {
        if (!schedule.getPassword().equals(password)) {
            throw new IllegalArgumentException("비밀번호 불일치");
        }
    }
}
