package com.blogPostApp.blogserver.dto;

import com.blogPostApp.blogserver.entities.Like;
import lombok.Data;

@Data
public class LikeDTO {
    private int id;
    private int postId;
    private int userId;

    // Constructors, getters, and setters

    public static LikeDTO fromEntity(Like like) {
        LikeDTO likeDTO = new LikeDTO();
        likeDTO.setId(like.getId());
        likeDTO.setPostId(like.getPost().getId());
        likeDTO.setUserId(like.getUser().getId());
        return likeDTO;
    }

    // Getters and setters
}