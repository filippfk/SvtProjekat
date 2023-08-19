package com.example.projekatSvt.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "group")
public class Group {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String name;

    @Column(nullable = false)
    private String description;

    @Column (nullable = false)
    private LocalDateTime creationDate;

    @Column()
    private Long GroupAdmin;

    @Column
    private boolean isDeleted;

    @Column(nullable = false)
    private boolean isSuspended;

    @Column
    private String suspendedReason;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Post> posts;

    @OneToMany
    private Set<GroupRequest> groupRequests;

    public Group() {
    }
}
