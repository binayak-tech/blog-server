package com.blogPostApp.blogserver.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blogPostApp.blogserver.entities.Like;
import com.blogPostApp.blogserver.repositories.LikeRepository;

@Service
public class LikeService {

    @Autowired
    private LikeRepository likeRepository;

    public Like addLike(Like like) {
        // Implement logic to add a new like and return the like object.
        return likeRepository.save(like);
    }

    public List<Like> getLikesForPost(int postId) {
        // Implement logic to retrieve all likes for a post with the given postId.
        return likeRepository.findAllByPostId(postId);
    }

    public void removeLike(int likeId) {
        // Implement logic to remove a like with the given likeId.
        likeRepository.deleteById(likeId);
    }
}