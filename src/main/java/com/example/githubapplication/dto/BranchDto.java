package com.example.githubapplication.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class BranchDto {

    private String name;
    private CommitDto commit;

    public BranchDto(String branch) {
    }

}
