package com.blogPostApp.blogserver.controllers;

import java.util.List;

import com.blogPostApp.blogserver.dto.LikeDTO;
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

    @PostMapping("/like")
    public ResponseEntity<LikeDTO> likePost(@RequestBody Like like) {
        LikeDTO newLike = likeService.addLike(like);
        return new ResponseEntity<>(newLike, HttpStatus.CREATED);
    }

    @GetMapping("/{postId}")
    public ResponseEntity<Integer> getLikeCountForPost(@PathVariable int postId) {
        int likeCount = likeService.getLikeCountForPost(postId);
        return new ResponseEntity<>(likeCount, HttpStatus.OK);
    }

    @DeleteMapping("/{likeId}")
    public ResponseEntity<String> removeLike(@PathVariable int likeId) {
        likeService.removeLike(likeId);
        return new ResponseEntity<>("Like removed successfully", HttpStatus.OK);
    }
}
