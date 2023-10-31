package com.blogPostApp.blogserver.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.blogPostApp.blogserver.entities.Post;

@Repository
public interface PostRepository extends JpaRepository<Post, Integer> {

}
