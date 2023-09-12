package com.example.football_community.domain.postlike.controller;

import com.example.football_community.domain.member.security.UserDetailsImpl;
import com.example.football_community.domain.postlike.service.PostLikeService;
import com.example.football_community.global.message.ResponseMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class PostLikeController {
    private final PostLikeService postLikeService;

    @PostMapping("/post/{postId}/like")
    public ResponseEntity likePost(
            @PathVariable Long postId,
            @AuthenticationPrincipal UserDetailsImpl userDetails
    ) {
        postLikeService.likePost(postId, userDetails);
        String message = null;
        if (postLikeService.isLiked(postId,userDetails)){
            message = "좋아요를 눌렀습니다.";
        } else {
            message = "좋아요를 취소했습니다.";
        }
        return ResponseMessage.SuccessResponse(message, "");
    }
}
