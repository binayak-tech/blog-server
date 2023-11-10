package com.blogPostApp.blogserver.repositories;

import com.blogPostApp.blogserver.entities.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Integer> {

    // Custom query to get post IDs for the user's own posts
    @Query("SELECT p.id FROM Post p WHERE p.user.userName = :username")
    List<Integer> findPostIdsByUsername(@Param("username") String username);

    // Custom query to get post IDs for the posts from users the current user is following
    @Query("SELECT p.id FROM Post p INNER JOIN p.user.following u WHERE u.userName = :username")
    List<Integer> findPostIdsByFollowing(@Param("username") String username);

    List<Post> findByCategory_Id(int categoryId);
}

