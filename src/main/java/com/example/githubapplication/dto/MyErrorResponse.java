package com.example.githubapplication.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;


@Getter
@AllArgsConstructor
public class MyErrorResponse {
    private int status;
    private String message;
}
