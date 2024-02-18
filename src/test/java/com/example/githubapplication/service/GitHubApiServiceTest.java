package com.example.githubapplication.service;

import com.example.githubapplication.dto.BranchDto;
import com.example.githubapplication.mapper.MyMapper;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;


import static org.mockito.Mockito.when;


public class GitHubApiServiceTest {
    @Mock
    private ModelMapper modelMapperMock;

    @Test
    public void shouldReturnBranchesForRepository() {
        RestTemplate restTemplateMock = Mockito.mock(RestTemplate.class);

        GitHubApiService githubApiService = new GitHubApiService(restTemplateMock, new MyMapper(modelMapperMock), "dummyToken");


        List<BranchDto> mockBranches = new ArrayList<>();

        mockBranches.add(new BranchDto("branch1"));
        mockBranches.add(new BranchDto("branch2"));
        ResponseEntity<List<BranchDto>> mockResponseEntity = new ResponseEntity<>(mockBranches, HttpStatus.OK);


        when(restTemplateMock.exchange(Mockito.anyString(), Mockito.eq(HttpMethod.GET), Mockito.any(), Mockito.eq(new ParameterizedTypeReference<List<BranchDto>>() {
        })))
                .thenReturn(mockResponseEntity);

        List<BranchDto> branches = githubApiService.getBranchesForRepository("testUser", "testRepo");

        Assert.assertNotNull(branches);
        Assert.assertEquals(2, branches.size());

    }


}