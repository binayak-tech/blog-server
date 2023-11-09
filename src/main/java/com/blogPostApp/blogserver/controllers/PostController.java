package com.blogPostApp.blogserver.controllers;

import com.blogPostApp.blogserver.config.JwtService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.blogPostApp.blogserver.entities.Post;
import com.blogPostApp.blogserver.services.PostService;

import java.util.List;

@RestController
@RequestMapping("/api/posts")
public class PostController {

    private final PostService postService;
    private final JwtService jwtService;

    @Autowired
    public PostController(PostService postService, JwtService jwtService) {
        this.postService = postService;
        this.jwtService = jwtService;
    }
    // Endpoint to create a new blog post
    private String getCurrentUsername(HttpServletRequest request) {
        String authorizationHeader = request.getHeader("Authorization");
        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            String jwtToken = authorizationHeader.substring(7); // Remove "Bearer " prefix
            return jwtService.extractUserNameFromJwtToken(jwtToken);
        }
        return null;
    }
    // Endpoint to get posts for the dashboard
    @GetMapping("/dashboard")
    public ResponseEntity<List<Integer>> getDashboardPosts(HttpServletRequest request) {
        String currentUsername = getCurrentUsername(request);

        if (currentUsername != null) {
            List<Integer> dashboardPostIds = postService.getDashboardPostIds(currentUsername);
            return new ResponseEntity<>(dashboardPostIds, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }

    @PostMapping("/create")
    public ResponseEntity<Post> createPost(@RequestBody Post post) {

        Post savedPost = postService.createPost(post);
        return new ResponseEntity<>(savedPost, HttpStatus.CREATED);
    }

    // Endpoint to retrieve a single blog post by its ID
    @GetMapping("/{postId}")
    public ResponseEntity<Post> getPostById(@PathVariable int postId) {
        Post post = postService.getPostById(postId);
        if (post == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(post, HttpStatus.OK);
    }

    // Endpoint to update an existing blog post
    @PutMapping("/{postId}")
    public ResponseEntity<Post> updatePost(@PathVariable int postId, @RequestBody Post updatedPost) {
        // Check if the post with the given ID exists and the user is authorized to
        // update it
        Post post = postService.getPostById(postId);
        if (post == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        // Update the post's properties with values from updatedPost
        post.setTitle(updatedPost.getTitle());
        post.setSummary(updatedPost.getSummary());
        post.setContent(updatedPost.getContent());
        post.setCategory(updatedPost.getCategory());

        Post updated = postService.updatePost(post);
        return new ResponseEntity<>(updated, HttpStatus.OK);
    }

    // Endpoint to delete a blog post by its ID
    @DeleteMapping("/{postId}")
    public ResponseEntity<String> deletePost(@PathVariable int postId) {
        // Check if the post with the given ID exists and the user is authorized to
        // delete it
        Post post = postService.getPostById(postId);
        if (post == null) {
            return new ResponseEntity<>("Post not found", HttpStatus.NOT_FOUND);
        }

        postService.deletePost(post);
        return new ResponseEntity<>("Post deleted successfully", HttpStatus.OK);
    }
}
