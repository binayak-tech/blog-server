package com.blogPostApp.blogserver.dto;

import com.blogPostApp.blogserver.entities.Post;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class PostDTO {
    private int id;
    private String title;
    private String slug;
    private String summary;
    private String content;
    private LocalDateTime createdDate;
    private int category_id;
    private int user_id;
    private String userName;
    // Other fields as needed

    public static PostDTO fromEntity(Post post) {
        PostDTO postDTO = new PostDTO();
        postDTO.setId(post.getId());
        postDTO.setTitle(post.getTitle());
        postDTO.setSlug(post.getSlug());
        postDTO.setSummary(post.getSummary());
        postDTO.setContent(post.getContent());
        postDTO.setCreatedDate(post.getCreatedDate());
        postDTO.setCategory_id(post.getCategory().getId());
        postDTO.setUser_id(post.getUser().getId());
        postDTO.setUserName(post.getUser().getUsername());
        // Set other fields as needed
        return postDTO;
    }
}
