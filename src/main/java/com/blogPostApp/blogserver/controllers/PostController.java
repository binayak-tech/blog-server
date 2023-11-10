package com.blogPostApp.blogserver.controllers;

import com.blogPostApp.blogserver.config.JwtService;
import com.blogPostApp.blogserver.dto.PostDTO;
import com.blogPostApp.blogserver.entities.Category;
import com.blogPostApp.blogserver.services.CategoryService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.blogPostApp.blogserver.entities.Post;
import com.blogPostApp.blogserver.services.PostService;

import java.util.List;

@RestController
@RequestMapping("/api/posts")
public class PostController {

    private final PostService postService;
    private final JwtService jwtService;
    @Autowired
    private  CategoryService categoryService;

    public PostController(PostService postService, JwtService jwtService, CategoryService categoryService) {
        this.postService = postService;
        this.jwtService = jwtService;
        this.categoryService = categoryService;
    }
    // Endpoint to create a new blog post
    private String getCurrentUsername(HttpServletRequest request) {
        String authorizationHeader = request.getHeader("Authorization");
        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            String jwtToken = authorizationHeader.substring(7); // Remove "Bearer " prefix
            return jwtService.extractUserNameFromJwtToken(jwtToken);
        }
        return null;
    }
    // Endpoint to get posts for the dashboard
    @GetMapping("/dashboard")
    public ResponseEntity<List<Integer>> getDashboardPosts(HttpServletRequest request) {
        try {
            String currentUsername = getCurrentUsername(request);

            if (currentUsername != null) {
                List<Integer> dashboardPostIds = postService.getDashboardPostIds(currentUsername);
                return new ResponseEntity<>(dashboardPostIds, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
            }
        } catch (Exception e) {
            // Log the exception for debugging purposes
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/create")
    public ResponseEntity<PostDTO> createPost(@RequestBody PostDTO postDTO) {
        try {
            Post savedPost = postService.createPost(postDTO);
            if (savedPost == null) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }

            PostDTO savedPostDTO = PostDTO.fromEntity(savedPost);
            return new ResponseEntity<>(savedPostDTO, HttpStatus.CREATED);
        } catch (Exception e) {
            // Log the exception for debugging purposes
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{postId}")
    public ResponseEntity<PostDTO> getPostById(@PathVariable int postId) {
        Post post = postService.getPostById(postId);
        if (post == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        PostDTO postDTO = PostDTO.fromEntity(post);
        return new ResponseEntity<>(postDTO, HttpStatus.OK);
    }

    @PutMapping("/{postId}")
    public ResponseEntity<PostDTO> updatePost(@PathVariable int postId, @RequestBody PostDTO updatedPostDTO) {
        try {
            Post post = postService.getPostById(postId);
            if (post == null) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }

            post.setTitle(updatedPostDTO.getTitle());
            post.setSummary(updatedPostDTO.getSummary());
            post.setContent(updatedPostDTO.getContent());

            // Assuming you have a method to fetch Category by ID in your service
            Category category = categoryService.getCategoryById(updatedPostDTO.getCategory_id());
            post.setCategory(category);

            Post updatedPost = postService.updatePost(post);
            updatedPostDTO = PostDTO.fromEntity(updatedPost);

            return new ResponseEntity<>(updatedPostDTO, HttpStatus.OK);
        } catch (Exception e) {
            // Log the exception for debugging purposes
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @GetMapping("/category/{categoryId}")
    public ResponseEntity<List<PostDTO>> getPostsByCategory(@PathVariable int categoryId) {
        try {
            List<PostDTO> posts = postService.getPostsByCategory(categoryId);
            return new ResponseEntity<>(posts, HttpStatus.OK);
        } catch (Exception e) {
            // Log the exception for debugging purposes
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{postId}")
    public ResponseEntity<String> deletePost(@PathVariable int postId) {
        try {
            Post post = postService.getPostById(postId);
            if (post == null) {
                return new ResponseEntity<>("Post not found", HttpStatus.NOT_FOUND);
            }

            postService.deletePost(post);
            return new ResponseEntity<>("Post deleted successfully", HttpStatus.OK);
        } catch (Exception e) {
            // Log the exception for debugging purposes
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
