package com.blogPostApp.blogserver.services;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import com.blogPostApp.blogserver.dto.PostDTO;
import com.blogPostApp.blogserver.entities.Category;
import com.blogPostApp.blogserver.entities.User;
import com.blogPostApp.blogserver.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blogPostApp.blogserver.entities.Post;
import com.blogPostApp.blogserver.repositories.PostRepository;

@Service
public class PostService {

    private final PostRepository postRepository;
    private final UserRepository userRepository;
    private  final CategoryService categoryService;

    public PostService(PostRepository postRepository, UserRepository userRepository, CategoryService categoryService) {
        this.postRepository = postRepository;
        this.userRepository = userRepository;
        this.categoryService = categoryService;
    }

    public List<PostDTO> getPostsByCategory(int categoryId) {
        List<Post> posts = postRepository.findByCategory_Id(categoryId);

        // Convert the list of Post entities to a list of PostDTOs
        return posts.stream()
                .map(PostDTO::fromEntity)
                .collect(Collectors.toList());
    }

    public PostDTO convertToDTO(Post post) {
        PostDTO postDTO = new PostDTO();
        postDTO.setId(post.getId());
        postDTO.setTitle(post.getTitle());
        postDTO.setSlug(post.getSlug());
        postDTO.setSummary(post.getSummary());
        postDTO.setContent(post.getContent());
        postDTO.setCreatedDate(post.getCreatedDate());
        // Map other fields as needed
        return postDTO;
    }

    public Post createPost(PostDTO postDTO) {
        User user = userRepository.findById(postDTO.getUser_id()).orElse(null);
        Category category = categoryService.getCategoryById(postDTO.getCategory_id());

        if (user == null || category == null) {
            // Handle the case where user or category is not found
            return null;
        }

        Post post = new Post();
        post.setTitle(postDTO.getTitle());
        post.setSlug(postDTO.getSlug());
        post.setSummary(postDTO.getSummary());
        post.setContent(postDTO.getContent());
        post.setCategory(category);
        post.setUser(user);

        return postRepository.save(post);
    }


    public List<Integer> getDashboardPostIds(String username) {
        // Implement logic to retrieve post IDs for the user's own posts
        List<Integer> userPostIds = postRepository.findPostIdsByUsername(username);

        // Implement logic to retrieve post IDs for the posts from users the current user is following
        List<Integer> followingPostIds = postRepository.findPostIdsByFollowing(username);

        // Combine and return the lists
        List<Integer> dashboardPostIds = new ArrayList<>();
        dashboardPostIds.addAll(userPostIds);
        dashboardPostIds.addAll(followingPostIds);

        return dashboardPostIds;
    }

    public Post getPostById(int postId) {
        return postRepository.findById(postId).orElse(null);
    }

    public Post updatePost(Post post) {
        // You can add business logic or validation before updating
        return postRepository.save(post);
    }

    public void deletePost(Post post) {
        postRepository.delete(post);
    }
}