package com.example.schedulemanager.service.schedule;

import com.example.schedulemanager.dto.Schedule.ScheduleRequestDto;
import com.example.schedulemanager.dto.Schedule.ScheduleResponseDto;
import com.example.schedulemanager.entity.Schedule;
import com.example.schedulemanager.exception.CannotDeleteScheduleException;
import com.example.schedulemanager.exception.MismatchPasswordException;
import com.example.schedulemanager.exception.NotFoundInformationException;
import com.example.schedulemanager.repository.schedule.ScheduleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ScheduleServiceImpl implements ScheduleService {

    private final ScheduleRepository scheduleRepository;

    @Override
    public ScheduleResponseDto saveSchedule(ScheduleRequestDto scheduleRequestDto) {
        Schedule schedule = new Schedule(
                scheduleRequestDto.getAuthorId(),
                scheduleRequestDto.getNickname(),
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
                .orElseThrow(() -> new NotFoundInformationException("id 불일치: " + id));
    }

    @Override
    public Page<ScheduleResponseDto> findSchedulesByPage(int pageNum, int pageSize) {
        Long totalScheduleCnt = scheduleRepository.getTotalScheduleCnt();
        List<ScheduleResponseDto> scheduleResponseDtoList = scheduleRepository.findSchedulesByPage(pageNum, pageSize);
        Pageable pageable = PageRequest.of(pageNum - 1, pageSize);
        return new PageImpl<>(scheduleResponseDtoList, pageable, totalScheduleCnt);
    }

    @Override
    public ScheduleResponseDto updateSchedule(Long id, ScheduleRequestDto scheduleRequestDto) {
        checkPasswordMatch(getScheduleById(id), scheduleRequestDto.getPassword());
        LocalDateTime updatedDate = LocalDateTime.now();
        return scheduleRepository.updateSchedule(
                id,
                scheduleRequestDto.getNickname(),
                scheduleRequestDto.getContents(),
                updatedDate);
    }

    @Override
    public void deleteSchedule(Long id, ScheduleRequestDto scheduleRequestDto) {
        checkPasswordMatch(getScheduleById(id), scheduleRequestDto.getPassword());
        if (scheduleRepository.deleteSchedule(id) == 0)
            throw new CannotDeleteScheduleException("삭제 불가");
    }

    private Schedule getScheduleById(Long id) {
        return scheduleRepository.findScheduleById(id)
                .orElseThrow(() -> new NotFoundInformationException("id가 올바르지 않습니다: " + id));
    }

    private void checkPasswordMatch(Schedule schedule, String password) {
        if (!schedule.getPassword().equals(password)) {
            throw new MismatchPasswordException("비밀번호 불일치");
        }
    }
}
