package com.example.projekatSvt.dto;

import com.example.projekatSvt.entity.Group;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Getter
@Setter
public class GroupDto {

    private Long id;

    @NotNull
    @NotEmpty
    private String name;
    @NotEmpty
    private String description;
    private LocalDateTime creationTime;


    public GroupDto (Group group) {
        this.id = group.getId();
        this.name = group.getName();
        this.description = group.getDescription();
        this.creationTime = group.getCreationDate();
    }

    public GroupDto() {
    }
}

