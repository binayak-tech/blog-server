package com.blogPostApp.blogserver.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.blogPostApp.blogserver.entities.Like;
import com.blogPostApp.blogserver.services.LikeService;

@RestController
@RequestMapping("/api/likes")
public class LikeController {

    @Autowired
    private LikeService likeService;

    // Endpoint to like a post
    @PostMapping("/like")
    public ResponseEntity<Like> likePost(@RequestBody Like like) {
        Like newLike = likeService.addLike(like);
        return new ResponseEntity<>(newLike, HttpStatus.CREATED);
    }

    // Endpoint to get likes for a post
    @GetMapping("/post/{postId}")
    public ResponseEntity<List<Like>> getLikesForPost(@PathVariable int postId) {
        List<Like> likes = likeService.getLikesForPost(postId);
        return new ResponseEntity<>(likes, HttpStatus.OK);
    }

    // Endpoint to remove a like
    @DeleteMapping("/{likeId}")
    public ResponseEntity<String> removeLike(@PathVariable int likeId) {
        likeService.removeLike(likeId);
        return new ResponseEntity<>("Like removed successfully", HttpStatus.OK);
    }
}
