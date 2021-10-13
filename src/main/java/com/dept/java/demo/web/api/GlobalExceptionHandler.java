package com.dept.java.demo.web.api;

import com.dept.java.demo.application.common.exceptions.ValidationFailed;
import java.time.Instant;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ResponseStatus(HttpStatus.CONFLICT)
    @ExceptionHandler(ValidationFailed.class)
    public ResponseEntity<ErrorResponse> handleValidationFailure(ValidationFailed exception) {
        return ResponseEntity.badRequest()
                             .body(ErrorResponse.forException(exception));
    }

    @Setter
    @Getter
    private static class ErrorResponse {

        private String message;
        private Instant timestamp;

        private ErrorResponse() {
            this.timestamp = Instant.now();
        }

        public static ErrorResponse forException(Exception exception) {
            ErrorResponse errorResponse = new ErrorResponse();
            errorResponse.setMessage(exception.getMessage());
            return errorResponse;
        }
    }
}
