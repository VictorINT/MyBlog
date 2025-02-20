package com.example.blog.controllers;

import com.example.blog.model.Post;
import com.example.blog.service.PostService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.util.List;

@RestController
@RequestMapping("/posts")
public class PostController {

    private final PostService postService;
    private static final Logger logger = LoggerFactory.getLogger(PostController.class);

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping
    public List<Post> getAllPosts() {
        return postService.getAllPosts();
    }

    @GetMapping("/{id}")
    public Post getPostById(@PathVariable Long id) {
        return postService.getPostById(id);
    }

    @PostMapping("/mod")
    public ResponseEntity<?> createPost(
            @AuthenticationPrincipal OAuth2User principal,
            @RequestBody Post post) {
        String userEmail = principal.getAttribute("email");
        
        if (!"victor.s.florescu@gmail.com".equalsIgnoreCase(userEmail)) {
            logger.warn("Unauthorized moderation attempt by user '{}'", userEmail);
            return ResponseEntity
                    .status(HttpStatus.FORBIDDEN)
                    .body("Only authorized moderators can perform this action");
        }

        Post createdPost = postService.createPost(post);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdPost);
    }

    @DeleteMapping("/mod/{id}")
    public ResponseEntity<?> deletePost(
            @AuthenticationPrincipal OAuth2User principal,
            @PathVariable Long id) {
        String userEmail = principal.getAttribute("email");
        
        if (!"victor.s.florescu@gmail.com".equalsIgnoreCase(userEmail)) {
            logger.warn("Unauthorized deletion attempt by user '{}'", userEmail);
            return ResponseEntity
                .status(HttpStatus.FORBIDDEN)
                .body("Only authorized moderators can perform this action");
        }
        
        postService.deletePost(id);
        return ResponseEntity.ok("Post deleted successfully");
    }

    @PutMapping("/mod/{id}")
    public ResponseEntity<?> updatePost(
            @AuthenticationPrincipal OAuth2User principal,
            @PathVariable Long id,
            @RequestBody Post post) {
        String userEmail = principal.getAttribute("email");
        
        if (!"victor.s.florescu@gmail.com".equalsIgnoreCase(userEmail)) {
            logger.warn("Unauthorized update attempt by user '{}'", userEmail);
            return ResponseEntity
                .status(HttpStatus.FORBIDDEN)
                .body("Only authorized moderators can perform this action");
        }
        
        Post updatedPost = postService.updatePost(id, post);
        return ResponseEntity.ok(updatedPost);
    }

    //! Useful but additional

    @GetMapping("/firstN")
    public List<Post> getFirstNPosts(@RequestParam Long n) {
        return postService.getFirstNPosts(n);
    }

    @GetMapping("/searchName")
    public List<Post> searchPostByName(@RequestParam String name){
        return postService.searchPostByName(name);
    }
}
