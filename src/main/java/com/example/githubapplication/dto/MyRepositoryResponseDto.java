package com.example.githubapplication.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class MyRepositoryResponseDto {
    private List<RepositoryDto> repositories;
}
