package com.example.projekatSvt.services;

import com.example.projekatSvt.entity.Post;
import com.example.projekatSvt.repository.PostRepository;
import com.example.projekatSvt.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service

public class PostServiceImpl implements PostService {

    @Autowired
    private PostRepository postRepository;

    @Override
    public Optional<Post> findPostById(Long id) {
        return postRepository.findById(id);
    }

    @Override
    public Post findPostByContent(String content) {
        Optional<Post> post = postRepository.findPostByContent(content);
        if(!post.isEmpty()){
            return post.get();
        }
        return null;
    }
    @Override
    public Post createPost(String content, Long userID) {
        Post newPost = new Post();
        newPost.setContent(content);
        newPost.setCreationDate(LocalDateTime.now());
        newPost.setUser(Math.toIntExact(userID));
        newPost.setDeleted(false);
        newPost = postRepository.save(newPost);
        return newPost;
    }

    @Override
    public List<Post> findAll() {
        return postRepository.findAll();
    }

    @Override
    public void savePost(Post post) {
        this.postRepository.save(post);
    }

    @Override
    public void deletePost(Long id) {
        this.postRepository.deleteById(id);
    }

    @Override
    public Post findOne(Long id) {
        return postRepository.findById(id).orElseGet(null);
    }

}
