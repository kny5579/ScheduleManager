package com.example.schedulemanager.exception;

public class NotFoundScheduleException extends RuntimeException {
    public NotFoundScheduleException(String message) {
        super(message);
    }
}
