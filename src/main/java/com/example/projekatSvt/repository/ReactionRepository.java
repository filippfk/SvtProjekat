package com.example.projekatSvt.repository;

import com.example.projekatSvt.entity.Reaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReactionRepository extends JpaRepository<Reaction, Long> {

    List<Reaction> findReactionsByPostId(Long id);
    List<Reaction> findReactionsByUserId(Long id);
}
