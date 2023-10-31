package com.blogPostApp.blogserver.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blogPostApp.blogserver.entities.User;
import com.blogPostApp.blogserver.repositories.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User registerUser(User user) {
        // Implement user registration logic, including validation, hashing passwords,
        // and saving to the database.
        // Return the registered user.

        return userRepository.save(user);
    }

    public User loginUser(String username, String password) {
        // Implement user login logic, including password validation.
        // Return the logged-in user or null if authentication fails.

        User user = userRepository.findByUsername(username);
        if (user != null && user.getPassword().equals(password)) {
            return user;
        }
        return null;
    }

    public User editUserProfile(int userId, User updatedUser) {
        // Retrieve the user by userId and ensure the user exists.
        User existingUser = userRepository.findById(userId).orElse(null);
        if (existingUser == null) {
            return null;
        }

        // Update user profile fields that can be modified (e.g., bio, profile picture).
        // Ensure any necessary validation and security checks.

        existingUser.setBio(updatedUser.getBio());
        existingUser.setProfilePicture(updatedUser.getProfilePicture());

        return userRepository.save(existingUser);
    }
}
