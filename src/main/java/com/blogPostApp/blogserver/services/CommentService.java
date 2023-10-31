package com.blogPostApp.blogserver.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blogPostApp.blogserver.entities.Comment;
import com.blogPostApp.blogserver.repositories.CommentRepository;

@Service
public class CommentService {

    @Autowired
    private CommentRepository commentRepository;

    public Comment addComment(Comment comment) {
        // Implement logic to add a new comment and return the comment object.
        return commentRepository.save(comment);
    }

    public List<Comment> getCommentsForPost(int postId) {
        // Implement logic to retrieve all comments for a post with the given postId.
        return commentRepository.findAllByPostId(postId);
    }

    public void removeComment(int commentId) {
        // Implement logic to remove a comment with the given commentId.
        commentRepository.deleteById(commentId);
    }
}
