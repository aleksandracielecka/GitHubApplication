package com.example.githubapplication.exception;

public class RepositoryNotFoundException extends RuntimeException{
    public RepositoryNotFoundException(String message) {
        super(message);
    }


}
