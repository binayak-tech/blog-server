package com.blogPostApp.blogserver.entities;

import java.time.LocalDateTime;

import jakarta.persistence.*;

@Entity
@Table(name = "broadcasted_blogs")
public class BroadcastedBlog {
    @Id
    @Column(name = "post_id")
    private int postId;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @OneToOne
    @JoinColumn(name = "post_id", referencedColumnName = "id") // Match this with the primary key of Post
    private Post post;

    // constructors
    public BroadcastedBlog() {
    }

    public BroadcastedBlog(int postId, LocalDateTime createdAt) {
        this.postId = postId;
        this.createdAt = createdAt;
    }

    // getters and setters

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public int getPostId() {
        return postId;
    }

    public void setPostId(int postId) {
        this.postId = postId;
    }

}
