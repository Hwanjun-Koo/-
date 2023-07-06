package com.example.football_community.controller;

import com.example.football_community.dto.PostDTO;
import com.example.football_community.entity.Post;
import com.example.football_community.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/football-community/post")
@RestController
public class PostController {
    private final PostService postService;

    @Autowired
    public PostController(PostService postService) {
        this.postService = postService;
    }

    @PostMapping("/create/{userId}")
    public ResponseEntity<PostDTO> create(
            @PathVariable Long userId, @RequestBody Post post) {
        PostDTO createdPost = postService.createPost(userId, post);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdPost);
    }

    @GetMapping("/{postId}")
    public ResponseEntity<PostDTO> getPost(
            @PathVariable Long postId
    ) {
        PostDTO postDTO = postService.getPost(postId);
        return ResponseEntity.ok(postDTO);
    }

    @GetMapping("/list/{userId}")
    public ResponseEntity<List<PostDTO>> getAllPosts(
            @PathVariable Long userId
    ) {
        List<PostDTO> postDTOList = postService.getAllPost(userId);
        return ResponseEntity.ok(postDTOList);
    }

    @PutMapping("/edit/{postId}")
    public ResponseEntity<PostDTO> updatePost(
            @PathVariable Long postId,
            @RequestBody PostDTO postDTO
    ) {
        PostDTO updatedPost = postService.updatePost(postId, postDTO);
        return ResponseEntity.ok(updatedPost);
    }

    @DeleteMapping("/delete/{postId}")
    public ResponseEntity<String> deletePost(
            @PathVariable Long postId
    ) {
        postService.deletePost(postId);
        return ResponseEntity.ok("게시물이 삭제되었습니다.");
    }
}
