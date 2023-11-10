package com.blogPostApp.blogserver.entities;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class PostDto {
    private int id;
    private String title;
    private String slug;
    private String summary;
    private String content;
    private LocalDateTime createdDate;
    private String userName;

    // Getters and setters
}
