package com.example.football_community.domain.follow.controller;

import com.example.football_community.domain.follow.service.FollowService;
import com.example.football_community.domain.member.security.UserDetailsImpl;
import com.example.football_community.global.message.ResponseMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class FollowController {
    private final FollowService followService;

    @PostMapping("/member/{memberId}/follow")
    public ResponseEntity follow(
            @PathVariable Long memberId,
            @AuthenticationPrincipal UserDetailsImpl userDetails
    ) {
        followService.followMember(memberId, userDetails);
        String message = null;
        if (followService.isFollowed(memberId, userDetails)) {
            message = "팔로우 하였습니다.";
        } else {
            message = "팔로우 취소하였습니다.";
        }
        return ResponseMessage.SuccessResponse(message, "");
    }

    @GetMapping("/member/{memberId}/followings")
    public ResponseEntity followings(
            @PathVariable Long memberId
    ) {
        return ResponseMessage.SuccessResponse("팔로잉 목록 불러오기 완료.", followService.getFollowings(memberId));
    }

    @GetMapping("/member/{memberId}/followers")
    public ResponseEntity followers(
            @PathVariable Long memberId
    ) {
        return ResponseMessage.SuccessResponse("팔로워 목록 불러오기 완료.", followService.getFollowers(memberId));
    }
}
