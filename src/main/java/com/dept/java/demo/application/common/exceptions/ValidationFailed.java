package com.dept.java.demo.application.common.exceptions;

public class ValidationFailed extends Exception {

    private final String message;

    public ValidationFailed(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
