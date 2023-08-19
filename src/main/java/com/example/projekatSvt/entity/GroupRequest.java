package com.example.projekatSvt.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "groupRequest")
public class GroupRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column()
    private boolean approved;

    @Column()
    private LocalDateTime createdAt;

    @Column()
    private LocalDateTime at;

    @ManyToOne
    private Group group;

    public GroupRequest() {
    }
}

