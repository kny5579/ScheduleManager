package com.example.schedulemanager.exception;

public class CannotDeleteScheduleException extends RuntimeException {
    public CannotDeleteScheduleException(String message) {
        super(message);
    }
}
