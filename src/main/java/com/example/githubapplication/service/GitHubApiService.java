package com.example.githubapplication.service;

import com.example.githubapplication.dto.BranchDto;
import com.example.githubapplication.dto.RepositoryDto;
import com.example.githubapplication.mapper.MyMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.Objects.isNull;
@Service
public class GitHubApiService {
    private final String GITHUB_API_URL = "https://api.github.com/users/";
    private final String GITHUB_API_URL_BRANCHES = "https://api.github.com/repos/";
    private final RestTemplate restTemplate;
    private final MyMapper myMapper;
    private final String githubAccessToken;


    public GitHubApiService(RestTemplate restTemplate, MyMapper myMapper, @Value("${github.access.token}") String githubAccessToken) {
        this.restTemplate = restTemplate;
        this.myMapper = myMapper;
        this.githubAccessToken = githubAccessToken;
    }

    public List<RepositoryDto> getUserRepositories(String username) {

        String apiUrl = UriComponentsBuilder.fromHttpUrl(GITHUB_API_URL + username + "/repos")
                .queryParam("type", "owner")
                .build()
                .toUriString();

        HttpEntity<String> entity = getStringHttpEntity();


        ResponseEntity<List<RepositoryDto>> responseEntity = restTemplate.exchange(
                apiUrl,
                HttpMethod.GET,
                entity,
                new ParameterizedTypeReference<>() {
                }
        );
        List<RepositoryDto> repositories = responseEntity.getBody();

        if (isNull(repositories)) {
            return Collections.emptyList();
        }
        repositories = repositories.stream()
                .map(repo -> {
                    repo.setOwnerLogin(username);
                    List<BranchDto> branches = getBranchesForRepository(username, repo.getName());
                    repo.setBranches(branches);
                    return repo;
                })
                .collect(Collectors.toList());

        return myMapper.mapToMyRepositoryResponseDto(repositories).getRepositories();
    }


    public List<BranchDto> getBranchesForRepository(String username, String repositoryName) {
        String branchesUrl = GITHUB_API_URL_BRANCHES + username + "/" + repositoryName + "/branches";
        HttpEntity<String> entity = getStringHttpEntity();

        ResponseEntity<List<BranchDto>> branchesResponse = restTemplate.exchange(
                branchesUrl,
                HttpMethod.GET,
                entity,
                new ParameterizedTypeReference<>() {
                }
        );
        if (branchesResponse.getStatusCode() == HttpStatus.OK) {
            return branchesResponse.getBody();

        } else {
            throw new RuntimeException("Failed to retrieve branches for repository: " + repositoryName);
        }


    }

    private HttpEntity<String> getStringHttpEntity() {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "token " + githubAccessToken);
        HttpEntity<String> entity = new HttpEntity<>(headers);
        return entity;
    }


}
