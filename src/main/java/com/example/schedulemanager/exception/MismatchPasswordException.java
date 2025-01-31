package com.example.schedulemanager.exception;

public class MismatchPasswordException extends RuntimeException{
    public MismatchPasswordException(String message) {
        super(message);
    }
}
