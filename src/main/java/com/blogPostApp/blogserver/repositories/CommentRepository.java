package com.blogPostApp.blogserver.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.blogPostApp.blogserver.entities.Comment;

public interface CommentRepository extends JpaRepository<Comment, Integer> {
    List<Comment> findAllByPostId(int postId);
}