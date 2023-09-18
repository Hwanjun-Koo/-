package com.example.football_community.domain.comment.controller;

import com.example.football_community.domain.comment.dto.request.CommentRegisterRequestDto;
import com.example.football_community.domain.comment.dto.request.CommentUpdateRequestDto;
import com.example.football_community.domain.comment.service.CommentService;
import com.example.football_community.domain.member.security.UserDetailsImpl;
import com.example.football_community.global.message.ResponseMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RequestMapping("/comment")
@RequiredArgsConstructor
@RestController
public class CommentController {
    private final CommentService commentService;

    @PostMapping("/register")
    public ResponseEntity registerComment(
            @RequestBody CommentRegisterRequestDto requestDto,
            @AuthenticationPrincipal UserDetailsImpl userDetails
    ) {
        commentService.createComment(requestDto, userDetails);
        return ResponseMessage.SuccessResponse("댓글이 작성되었습니다.", "");
    }

    @GetMapping("/myComments")
    public ResponseEntity myComments(
            @AuthenticationPrincipal UserDetailsImpl userDetails
    ) {
        return ResponseMessage.SuccessResponse("내가 쓴 댓글 불러오기 완료",
                commentService.myComments(userDetails));
    }

    @PutMapping("/{commentId}")
    public ResponseEntity updateComment(
            @PathVariable Long commentId,
            @RequestBody CommentUpdateRequestDto requestDto,
            @AuthenticationPrincipal UserDetailsImpl userDetails
    ) {
        commentService.updateComment(commentId, requestDto, userDetails);
        return ResponseMessage.SuccessResponse("댓글을 수정하였습니다.", "");
    }

    @DeleteMapping("/{commentId}")
    public ResponseEntity deleteComment(
            @PathVariable Long commentId,
            @AuthenticationPrincipal UserDetailsImpl userDetails
    ) {
        commentService.deleteComment(commentId, userDetails);
        return ResponseMessage.SuccessResponse("댓글을 삭제하였습니다", "");
    }
}
