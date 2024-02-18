package com.example.githubapplication.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpClientErrorException;

import java.util.LinkedHashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<Map<String, Object>> handleUserNotFoundException(UserNotFoundException ex) {
        return new ResponseEntity<>(errorResponse(404, "User not found"), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(RepositoryNotFoundException.class)
    public ResponseEntity<String> handleRepositoryNotFoundException(RepositoryNotFoundException ex) {
        return new ResponseEntity<>("Repository not found", HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(HttpClientErrorException.NotFound.class)
    public ResponseEntity<Map<String, Object>> handleRepositoryNotFoundException(HttpClientErrorException.NotFound ex) {
        return new ResponseEntity<>(errorResponse(404, "User not found"), HttpStatus.NOT_FOUND);
    }

    private Map<String, Object> errorResponse(int status, String message) {
        Map<String, Object> errorMap = new LinkedHashMap<>();
        errorMap.put("status", status);
        errorMap.put("message", message);
        return errorMap;
    }
}
