package com.blogPostApp.blogserver.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {

    private int id;
    private String userName;
    private String email;
    private String profilePicture;
    private String bio;
}