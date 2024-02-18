package com.example.githubapplication.controller;

import com.example.githubapplication.dto.RepositoryDto;
import com.example.githubapplication.service.GitHubApiService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;


import java.util.List;

@RestController
public class RepositoryController {

    private final GitHubApiService githubApiService;

    public RepositoryController(GitHubApiService githubApiService) {
        this.githubApiService = githubApiService;
    }


    @GetMapping("/repositories/{username}")
    public ResponseEntity<?> getRepositories(@PathVariable String username) {

        List<RepositoryDto> repositories = githubApiService.getUserRepositories(username);
        return ResponseEntity.ok(repositories);

    }
}
