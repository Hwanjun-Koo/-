package com.example.football_community.domain.post.controller;

import com.example.football_community.domain.member.security.UserDetailsImpl;
import com.example.football_community.domain.post.dto.request.PostUpdateRequestDto;
import com.example.football_community.domain.post.dto.request.PostUploadRequestDto;
import com.example.football_community.domain.post.service.PostService;
import com.example.football_community.global.message.ResponseMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RequestMapping("/post")
@RequiredArgsConstructor
@RestController
public class PostController {
    private final PostService postService;

    @PostMapping("/upload")
    public ResponseEntity uploadPost(
            @RequestBody PostUploadRequestDto requestDto,
            @AuthenticationPrincipal UserDetailsImpl userDetails
    ) {
        postService.createPost(requestDto, userDetails);
        return ResponseMessage.SuccessResponse("게시글이 작성되었습니다.", "");
    }

    @GetMapping("/myPosts")
    public ResponseEntity myPosts(
            @AuthenticationPrincipal UserDetailsImpl userDetails
    ) {
        return ResponseMessage.SuccessResponse("내가 쓴 게시글을 불러왔습니다.",
                postService.myPosts(userDetails));
    }

    @PutMapping("/{postId}")
    public ResponseEntity updatePost(
            @PathVariable Long postId,
            @RequestBody PostUpdateRequestDto requestDto,
            @AuthenticationPrincipal UserDetailsImpl userDetails
    ) {
        postService.updatePost(postId, requestDto, userDetails);
        return ResponseMessage.SuccessResponse("게시글을 수정하였습니다.", "");
    }

    @DeleteMapping("/{postId}")
    public ResponseEntity deletePost(
            @PathVariable Long postId,
            @AuthenticationPrincipal UserDetailsImpl userDetails
    ) {
        postService.deletePost(postId, userDetails);
        return ResponseMessage.SuccessResponse("게시글을 삭제하였습니다.", "");
    }
}
