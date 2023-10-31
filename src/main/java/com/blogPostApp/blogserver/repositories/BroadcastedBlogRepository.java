package com.blogPostApp.blogserver.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.blogPostApp.blogserver.entities.BroadcastedBlog;

public interface BroadcastedBlogRepository extends JpaRepository<BroadcastedBlog, Integer> {
    // You can add custom query methods if needed
}
