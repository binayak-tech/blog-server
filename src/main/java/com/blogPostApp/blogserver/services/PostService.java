package com.blogPostApp.blogserver.services;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blogPostApp.blogserver.entities.Post;
import com.blogPostApp.blogserver.repositories.PostRepository;

@Service
public class PostService {

    @Autowired
    private PostRepository postRepository; // You need to create the PostRepository interface

    public Post createPost(Post post) {
        // Perform any additional business logic or validation before saving the post
        return postRepository.save(post);
    }

    public Post getPostById(int postId) {
        return postRepository.findById(postId).orElse(null);
    }

    public List<Post> getAllPosts() {
        return postRepository.findAll();
    }

    public Post updatePost(Post post) {
        // You can add business logic or validation before updating
        return postRepository.save(post);
    }

    public void deletePost(Post post) {
        postRepository.delete(post);
    }
}
