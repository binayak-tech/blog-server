package com.blogPostApp.blogserver.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blogPostApp.blogserver.entities.User;
import com.blogPostApp.blogserver.repositories.UserRepository;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User getUserProfile(String userName) {
        Optional<User> userOptional = userRepository.findByUserName(userName);

        if (userOptional.isPresent()) {
            return userOptional.get();
        } else {
            return null; // You may also consider throwing a custom exception
        }
    }


    public User editUserProfile(String userName, User updatedUser) {
        User existingUser = userRepository.findByUserName(userName).orElse(null);
        if (existingUser == null) {
            return null;
        }
        existingUser.setBio(updatedUser.getBio());
        existingUser.setProfilePicture(updatedUser.getProfilePicture());

        return userRepository.save(existingUser);
    }
}
