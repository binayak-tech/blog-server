package com.blogPostApp.blogserver.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.blogPostApp.blogserver.entities.Post;
import com.blogPostApp.blogserver.services.PostService;

@RestController
@RequestMapping("/api/posts")
public class PostController {

    @Autowired
    private PostService postService;

    // Endpoint to create a new blog post
    @PostMapping("/create")
    public ResponseEntity<Post> createPost(@RequestBody Post post) {
        // You can perform validation here before saving the post
        // For example, check if the user is authorized to create a post
        // Ensure the category specified exists, etc.

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
