package com.example.projekatSvt.dto;

import com.example.projekatSvt.entity.Report;
import com.example.projekatSvt.entity.ReportReason;
import com.example.projekatSvt.entity.User;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;

public class ReportDto {

    private Long id;
    @NotNull
    private ReportReason reason;
    @NotNull
    private LocalDate timestamp;

    @NotNull
    private User byUser;

    private boolean isAccepted;

    public ReportDto (Report report) {
        this.id = report.getId();
        this.reason = report.getReason();
        this.timestamp = report.getTimestamp();
        this.isAccepted = report.isAccepted();
    }
}