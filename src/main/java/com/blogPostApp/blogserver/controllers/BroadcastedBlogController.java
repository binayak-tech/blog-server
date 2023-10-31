package com.blogPostApp.blogserver.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.blogPostApp.blogserver.entities.BroadcastedBlog;
import com.blogPostApp.blogserver.services.BroadcastedBlogService;

@RestController
@RequestMapping("/api/broadcasted-blogs")
public class BroadcastedBlogController {

    @Autowired
    private BroadcastedBlogService broadcastedBlogService;

    // Endpoint to add a broadcasted blog
    @PostMapping("/add")
    public ResponseEntity<BroadcastedBlog> addBroadcastedBlog(@RequestBody BroadcastedBlog broadcastedBlog) {
        BroadcastedBlog newBroadcastedBlog = broadcastedBlogService.addBroadcastedBlog(broadcastedBlog);
        return new ResponseEntity<>(newBroadcastedBlog, HttpStatus.CREATED);
    }

    // Endpoint to get all broadcasted blogs
    @GetMapping("/all")
    public ResponseEntity<List<BroadcastedBlog>> getBroadcastedBlogs() {
        List<BroadcastedBlog> broadcastedBlogs = broadcastedBlogService.getBroadcastedBlogs();
        return new ResponseEntity<>(broadcastedBlogs, HttpStatus.OK);
    }
}