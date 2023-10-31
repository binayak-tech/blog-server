package com.blogPostApp.blogserver.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blogPostApp.blogserver.entities.BroadcastedBlog;
import com.blogPostApp.blogserver.repositories.BroadcastedBlogRepository;

@Service
public class BroadcastedBlogService {

    @Autowired
    private BroadcastedBlogRepository broadcastedBlogRepository;

    public BroadcastedBlog addBroadcastedBlog(BroadcastedBlog broadcastedBlog) {
        // Implement logic to add a new broadcasted blog and return the broadcasted blog
        // object.
        return broadcastedBlogRepository.save(broadcastedBlog);
    }

    public List<BroadcastedBlog> getBroadcastedBlogs() {
        // Implement logic to retrieve all broadcasted blogs.
        return broadcastedBlogRepository.findAll();
    }
}
