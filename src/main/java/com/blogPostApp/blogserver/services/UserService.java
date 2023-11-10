package com.blogPostApp.blogserver.services;

import com.blogPostApp.blogserver.dto.UserDTO;
import com.blogPostApp.blogserver.entities.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blogPostApp.blogserver.entities.User;
import com.blogPostApp.blogserver.repositories.UserRepository;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public UserDTO getUserProfile(String userName) {
        Optional<User> userOptional = userRepository.findByUserName(userName);

        return userOptional.map(user -> new UserDTO(
                user.getId(),
                user.getUsername(),
                user.getEmail(),
                user.getProfilePicture(),
                user.getBio()
        )).orElse(null);
    }

    public UserDTO editUserProfile(String userName, UserDTO updatedUserDTO) {
        User existingUser = userRepository.findByUserName(userName).orElse(null);
        if (existingUser == null) {
            return null;
        }
        existingUser.setBio(updatedUserDTO.getBio());
        existingUser.setProfilePicture(updatedUserDTO.getProfilePicture());

        User savedUser = userRepository.save(existingUser);

        return new UserDTO(
                savedUser.getId(),
                savedUser.getUsername(),
                savedUser.getEmail(),
                savedUser.getProfilePicture(),
                savedUser.getBio()
        );
    }
}
