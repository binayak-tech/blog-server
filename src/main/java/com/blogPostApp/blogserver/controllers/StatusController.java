package com.blogPostApp.blogserver.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StatusController {
    @GetMapping("/")
    public String showMessage() {
        return "This website is working nicely";
    }
}
