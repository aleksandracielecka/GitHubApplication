package com.example.githubapplication.exception;

import org.junit.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;


import static org.junit.jupiter.api.Assertions.*;

public class GlobalExceptionHandlerTest {

    private final GlobalExceptionHandler globalExceptionHandler = new GlobalExceptionHandler();

    @Test
    public void testHandleUserNotFoundException() {
        UserNotFoundException ex = new UserNotFoundException("User not found");
        ResponseEntity<?> responseEntity = globalExceptionHandler.handleUserNotFoundException(ex);

        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
        assertEquals("{status=404, message=User not found}", responseEntity.getBody().toString());
    }

    @Test
    public void testHandleRepositoryNotFoundException() {
        RepositoryNotFoundException ex = new RepositoryNotFoundException("Repository not found");
        ResponseEntity<?> responseEntity = globalExceptionHandler.handleRepositoryNotFoundException(ex);

        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
        assertEquals("Repository not found", responseEntity.getBody().toString());
    }

//    @Test
//    public void testHandleHttpClientErrorException() {
//        HttpClientErrorException.NotFound ex = new HttpClientErrorException.NotFound("User not found");
//        ResponseEntity<?> responseEntity = globalExceptionHandler.handleRepositoryNotFoundException(ex);
//
//        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
//        assertEquals("{status=404, message=User not found}", responseEntity.getBody().toString());
//    }

}