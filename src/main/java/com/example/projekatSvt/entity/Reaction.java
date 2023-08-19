package com.example.projekatSvt.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "reaction")
public class Reaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private ReactionType type;

    @Column(nullable = false)
    private LocalDateTime timestamp;

    @ManyToOne()
    private User user;

    @ManyToOne()
    private Post post;

    @ManyToOne()
    private Comment comment;

    public Reaction(ReactionType type, User user, Post post) {
        this.type = type;
        this.user = user;
        this.post = post;
        this.timestamp = LocalDateTime.now();
    }

    public Reaction() {
    }
}