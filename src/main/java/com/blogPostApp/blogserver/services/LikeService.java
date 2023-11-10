package com.blogPostApp.blogserver.services;

import java.util.List;

import com.blogPostApp.blogserver.dto.LikeDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blogPostApp.blogserver.entities.Like;
import com.blogPostApp.blogserver.repositories.LikeRepository;

@Service
public class LikeService {

    @Autowired
    private LikeRepository likeRepository;

    public LikeDTO addLike(Like like) {
        Like newLike = likeRepository.save(like);
        return newLike.toDTO();
    }

    public int getLikeCountForPost(int postId) {
        return likeRepository.findAllByPostId(postId).size();
    }

    public void removeLike(int likeId) {
        likeRepository.deleteById(likeId);
    }
}