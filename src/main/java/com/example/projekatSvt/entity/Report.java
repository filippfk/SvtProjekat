package com.example.projekatSvt.entity;


import com.example.projekatSvt.entity.ReportReason;
import jakarta.persistence.*;
import jakarta.persistence.Entity;
//import jakarta.persistence.Id;
//import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "report")
public class Report {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private ReportReason reason;
    @Column(nullable = false)
    private LocalDate timestamp;

    @Column()
    private boolean isAccepted;

    @ManyToOne()
    private User user;

    @ManyToOne()
    private Report report;

}
