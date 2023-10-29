package com.blogPostApp.blogserver.entities;

import java.time.LocalDateTime;

import jakarta.persistence.*;

@Entity
@Table(name = "broadcasted_blogs")
public class BroadcastedBlog {
    @Id
    @OneToOne
    @JoinColumn(name = "post_id")
    private Post post;

    @Column(nullable = false)
    private LocalDateTime createdAt;

    // Getters and setters
}
