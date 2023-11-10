package com.blogPostApp.blogserver.controllers;

import com.blogPostApp.blogserver.config.JwtService;
import com.blogPostApp.blogserver.dto.UserDTO;
import com.blogPostApp.blogserver.entities.Category;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.blogPostApp.blogserver.entities.User;
import com.blogPostApp.blogserver.services.UserService;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private JwtService jwtService;

    // Method to extract current user's username from JWT token in Authorization header
    private String getCurrentUsername(HttpServletRequest request) {
        String authorizationHeader = request.getHeader("Authorization");
        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            String jwtToken = authorizationHeader.substring(7); // Remove "Bearer " prefix
            return jwtService.extractUserNameFromJwtToken(jwtToken);
        }
        return null;
    }
    @GetMapping("/profile")
    public ResponseEntity<UserDTO> getUserProfile(HttpServletRequest request) {
        String currentUsername = getCurrentUsername(request);

        if (currentUsername != null) {
            UserDTO userDTO = userService.getUserProfile(currentUsername);

            if (userDTO != null) {
                return new ResponseEntity<>(userDTO, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } else {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }

    @GetMapping("/id")
    public ResponseEntity<Integer> getUserId(HttpServletRequest request) {
        String currentUsername = getCurrentUsername(request);

        if (currentUsername != null) {
            UserDTO user = userService.getUserProfile(currentUsername);

            if (user != null) {
                return new ResponseEntity<>(user.getId(), HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } else {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }


    // Edit user profile
    @PutMapping("/{userName}")
    public ResponseEntity<UserDTO> editUserProfile(@PathVariable String userName, @RequestBody UserDTO updatedUserDTO) {
        UserDTO editedUserDTO = userService.editUserProfile(userName, updatedUserDTO);
        if (editedUserDTO == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(editedUserDTO, HttpStatus.OK);
    }
}
