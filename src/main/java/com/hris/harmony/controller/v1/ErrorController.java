package com.hris.harmony.controller.v1;

import com.hris.harmony.util.ResponseUtil;
import jakarta.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Slf4j
@RestControllerAdvice
public class ErrorController {
    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<?> handleResponseStatusException(ResponseStatusException e) {
        log.error("ResponseStatusException: {}", e.getMessage());
        HttpStatusCode statusCode = e.getStatusCode();
        return ResponseUtil.buildResponse(HttpStatus.valueOf(statusCode.value()), e.getReason(), null);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<?> handleDataIntegrityViolationException(DataIntegrityViolationException e) {
        log.error("DataIntegrityViolationException: {}", e.getMessage());
        String message = "Unexpected database error.";
        HttpStatus status = HttpStatus.CONFLICT;

        if (e.getCause() != null && e.getCause().getMessage() != null) {
            String causeMessage = e.getCause().getMessage().toLowerCase();

            if (causeMessage.contains("duplicate key value") || causeMessage.contains("duplicate entry")) {
                message = "Data already exists.";
            } else if (causeMessage.contains("cannot be null")) {
                message = "Required data cannot be null.";
                status = HttpStatus.BAD_REQUEST;
            } else if (causeMessage.contains("foreign key constraint")) {
                message = "Data cannot be deleted because it is referenced by other data.";
                status = HttpStatus.BAD_REQUEST;
            }
        }

        return ResponseUtil.buildResponse(status, message, null);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<?> handleConstraintViolationException(ConstraintViolationException e) {
        log.error("ConstraintViolationException: {}", e.getMessage());

        List<String> errors = e.getConstraintViolations()
                .stream()
                .map(violation -> violation.getMessage())
                .toList();

        String errorMessage = String.join(", ", errors);
        return ResponseUtil.buildResponse(HttpStatus.BAD_REQUEST, errorMessage, null);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleMethodArgumentNotValid(MethodArgumentNotValidException e) {
        log.error("Validation error: {}", e.getMessage());

        List<String> errors = e.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(FieldError::getDefaultMessage)
                .toList();

        String errorMessage = String.join(", ", errors);
        return ResponseUtil.buildResponse(HttpStatus.BAD_REQUEST, errorMessage, null);
    }
}
