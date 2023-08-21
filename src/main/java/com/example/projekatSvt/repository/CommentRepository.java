package com.example.projekatSvt.repository;

import com.example.projekatSvt.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {
}

