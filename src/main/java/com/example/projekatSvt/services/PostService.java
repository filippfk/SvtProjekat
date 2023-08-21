package com.example.projekatSvt.services;

import com.example.projekatSvt.entity.Post;

import java.util.List;
import java.util.Optional;

public interface PostService {

    Optional<Post> findPostById(Long id);
    Post findPostByContent(String content);
    Post createPost(String content, Long userID);
    List<Post> findAll();
    void savePost(Post post);
    void deletePost(Long id);

    Post findOne(Long id);
}

