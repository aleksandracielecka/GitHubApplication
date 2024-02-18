package com.example.githubapplication.mapper;


import com.example.githubapplication.dto.*;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class MyMapper {
    private final ModelMapper modelMapper;

    public MyMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public MyRepositoryResponseDto mapToMyRepositoryResponseDto(List<RepositoryDto> repositories) {
        MyRepositoryResponseDto myRepositoryResponseDto = new MyRepositoryResponseDto();
        List<RepositoryDto> mappedRepositories = repositories.stream()
                .map(this::mapToRepositoryDto)
                .collect(Collectors.toList());
        myRepositoryResponseDto.setRepositories(mappedRepositories);
        return myRepositoryResponseDto;
    }

    public RepositoryDto mapToRepositoryDto(RepositoryDto repository) {
        RepositoryDto mappedRepository = modelMapper.map(repository, RepositoryDto.class);
        List<BranchDto> mappedBranches = repository.getBranches().stream()
                .map(branch -> modelMapper.map(branch, BranchDto.class))
                .collect(Collectors.toList());
        mappedRepository.setBranches(mappedBranches);
        return mappedRepository;
    }

}
