package com.blogPostApp.blogserver.services;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.blogPostApp.blogserver.entities.Category;
import com.blogPostApp.blogserver.entities.User;
import com.blogPostApp.blogserver.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blogPostApp.blogserver.entities.Post;
import com.blogPostApp.blogserver.repositories.PostRepository;

@Service
public class PostService {

    private final PostRepository postRepository;
    private final UserRepository userRepository;

    public PostService(PostRepository postRepository, UserRepository userRepository) {
        this.postRepository = postRepository;
        this.userRepository = userRepository;
    }

    public Post createPost(Post post) {
        // Perform any additional business logic or validation before saving the post
        return postRepository.save(post);
    }

    public List<Integer> getDashboardPostIds(String username) {
        // Implement logic to retrieve post IDs for the user's own posts
        List<Integer> userPostIds = postRepository.findPostIdsByUsername(username);

        // Implement logic to retrieve post IDs for the posts from users the current user is following
        List<Integer> followingPostIds = postRepository.findPostIdsByFollowing(username);

        // Combine and return the lists
        List<Integer> dashboardPostIds = new ArrayList<>();
        dashboardPostIds.addAll(userPostIds);
        dashboardPostIds.addAll(followingPostIds);

        return dashboardPostIds;
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
