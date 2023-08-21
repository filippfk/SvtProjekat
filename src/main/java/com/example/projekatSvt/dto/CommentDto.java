package com.example.projekatSvt.dto;

import com.example.projekatSvt.entity.Comment;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Data
public class CommentDto {

    private Long id;

    @NotBlank
    private String text;
    @NotNull
    private LocalDate timestamp;

    @NotBlank
    private boolean isDeleted;

    public CommentDto (Comment comment) {
        this.id = comment.getId();
        this.text = comment.getText();
        this.timestamp = comment.getTimestamp();
        this.isDeleted = comment.isDeleted();
    }
}

