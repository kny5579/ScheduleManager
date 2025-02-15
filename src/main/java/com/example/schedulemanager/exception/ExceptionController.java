package com.example.schedulemanager.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class ExceptionController {

    //비밀번호 안 맞는 경우 예외처리 핸들러
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(MismatchPasswordException.class)
    public ResponseEntity<String> handlerMismatchPasswordException(MismatchPasswordException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
    }

    //일정 정보를 조회할 수 없는 경우 예외처리 핸들러
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NotFoundInformationException.class)
    public ResponseEntity<String> handlerNotFoundScheduleException(NotFoundInformationException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
    }

    //일정 삭제에 실패할 경우 예외처리 핸들러
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(CannotDeleteScheduleException.class)
    public ResponseEntity<String> handlerCannotDeleteScheduleException(CannotDeleteScheduleException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
    }

    //유효성 검사 실패할 경우 예외처리 핸들러
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handlerValidationException(MethodArgumentNotValidException e) {
        Map<String, String> errors = new HashMap<>();
        for (FieldError error : e.getBindingResult().getFieldErrors()) {
            errors.put(error.getField(), error.getDefaultMessage());
        }
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }
}
