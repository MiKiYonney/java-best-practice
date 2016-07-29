package com.best.practice.exception;

public class ValidationException extends RuntimeException {
    public ValidationException(String message) {
        super(message);
    }

    private static final String errorMessage = "系统异常";
    public ValidationException() {
        super(errorMessage);
    }
}
