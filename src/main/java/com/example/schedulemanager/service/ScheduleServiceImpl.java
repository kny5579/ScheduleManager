package com.example.schedulemanager.service;

import com.example.schedulemanager.dto.ScheduleRequestDto;
import com.example.schedulemanager.dto.ScheduleResponseDto;
import com.example.schedulemanager.entity.Schedule;
import com.example.schedulemanager.repository.ScheduleRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class ScheduleServiceImpl implements ScheduleService{

    private final ScheduleRepository scheduleRepository;

    public ScheduleServiceImpl(ScheduleRepository scheduleRepository) {
        this.scheduleRepository = scheduleRepository;
    }


    @Override
    public ScheduleResponseDto saveSchedule(ScheduleRequestDto scheduleRequestDto) {
        Schedule schedule = new Schedule(scheduleRequestDto.getUsername(),scheduleRequestDto.getPassword(),scheduleRequestDto.getContents());

        return scheduleRepository.saveSchedule(schedule);
    }

    @Override
    public List<ScheduleResponseDto> findAllSchedule(LocalDateTime updatedDate,String username) {
        return scheduleRepository.findAllSchedule(updatedDate,username);
    }

    @Override
    public ScheduleResponseDto findScheduleById(Long id) {
        Optional<Schedule> optionalSchedule = getOptionalSchedule(id);
        return new ScheduleResponseDto(optionalSchedule.get());
    }

    @Override
    public ScheduleResponseDto updateSchedule(Long id, ScheduleRequestDto scheduleRequestDto) {
        Schedule schedule = getOptionalSchedule(id).get();
        if(!schedule.getPassword().equals(scheduleRequestDto.getPassword())) {
            throw new IllegalArgumentException("비밀번호 불일치");
        }
        LocalDateTime updatedDate = LocalDateTime.now();
        return scheduleRepository.updateSchedule(id, scheduleRequestDto.getUsername(),scheduleRequestDto.getContents(),updatedDate);
    }

    private Optional<Schedule> getOptionalSchedule(Long id) {
        Optional<Schedule> optionalSchedule = scheduleRepository.findScheduleById(id);

        if(optionalSchedule.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Does not exist id = " + id);
        }
        return optionalSchedule;
    }
}
