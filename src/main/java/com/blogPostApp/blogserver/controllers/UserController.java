package com.blogPostApp.blogserver.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.blogPostApp.blogserver.entities.User;
import com.blogPostApp.blogserver.services.UserService;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/{userName}")
    public ResponseEntity<User> getUserProfile(@PathVariable String userName) {
        User user = userService.getUserProfile(userName);

        if (user == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(user, HttpStatus.OK);
    }


    // Edit user profile
    @PutMapping("/{userName}")
    public ResponseEntity<User> editUserProfile(@PathVariable String userName, @RequestBody User updatedUser) {
        User editedUser = userService.editUserProfile(userName, updatedUser);
        if (editedUser == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(editedUser, HttpStatus.OK);
    }
}
