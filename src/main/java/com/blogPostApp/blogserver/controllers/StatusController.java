package com.blogPostApp.blogserver.controllers;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StatusController {
    @GetMapping("/")
    public String showMessage() {
        return "This website is working nicely";
    }

    @SpringBootApplication
    public static class BlogServerApplication {

        public static void main(String[] args) {
            SpringApplication.run(BlogServerApplication.class, args);
        }

    }
}
