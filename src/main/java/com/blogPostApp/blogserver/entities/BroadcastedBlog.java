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

    // constructors
    public BroadcastedBlog() {
    }

    public BroadcastedBlog(Post post, LocalDateTime createdAt) {
        this.post = post;
        this.createdAt = createdAt;
    }

    // getters and setters
    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

}
