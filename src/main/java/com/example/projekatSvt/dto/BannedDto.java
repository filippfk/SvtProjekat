package com.example.projekatSvt.dto;

import com.example.projekatSvt.entity.Banned;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;

public class BannedDto {

    private Long id;

    @NotNull
    private LocalDate timestamp;

    public BannedDto (Banned banned) {
        this.id = banned.getId();
        this.timestamp = banned.getTimestamp();
    }
}
