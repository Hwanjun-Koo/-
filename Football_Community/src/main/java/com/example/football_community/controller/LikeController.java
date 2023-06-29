package com.example.football_community.controller;

import com.example.football_community.service.LikeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/football-community/like")
public class LikeController {
    private final LikeService likeService;

    public LikeController(LikeService likeService) {
        this.likeService = likeService;
    }

    @PostMapping("/like/{userId}/{postId}")
    public ResponseEntity<String> likePost(
            @PathVariable Long userId,
            @PathVariable Long postId
    ) {
        likeService.likePost(userId, postId);
        return ResponseEntity.ok("좋아요를 눌렀습니다.");
    }

    @PostMapping("unlike/{userId}/{postId}")
    public ResponseEntity<String> unlikePost(
            @PathVariable Long userId,
            @PathVariable Long postId
    ) {
        likeService.unlikePost(userId, postId);
        return ResponseEntity.ok("좋아요를 취소했습니다.");
    }
}
