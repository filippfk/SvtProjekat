package com.example.projekatSvt.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GroupRequestDto {

    private Long id;
    private String name;
    private String description;

    public GroupRequestDto() {
    }
}

