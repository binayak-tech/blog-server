package com.blogPostApp.blogserver.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.blogPostApp.blogserver.entities.Like;

public interface LikeRepository extends JpaRepository<Like, Integer> {
    List<Like> findAllByPostId(int postId);
}
