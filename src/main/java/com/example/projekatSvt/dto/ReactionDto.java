package com.example.projekatSvt.dto;

import com.example.projekatSvt.entity.Reaction;
import com.example.projekatSvt.entity.ReactionType;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import java.time.LocalDateTime;

@Getter
@Setter
public class ReactionDto {

    private Long id;
    @NotEmpty
    private ReactionType type;

    @NotEmpty
    private LocalDateTime timestamp;

    public ReactionDto (Reaction reaction) {
        this.id = reaction.getId();
        this.type = reaction.getType();
        this.timestamp = reaction.getTimestamp();
    }

    public ReactionDto() {
    }
}