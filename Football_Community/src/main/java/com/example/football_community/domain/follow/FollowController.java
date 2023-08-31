package com.example.football_community.domain.follow;

import com.example.football_community.domain.member.MemberDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/football-community/follow")
public class FollowController {
    private final FollowService followService;


    public FollowController(FollowService followService) {
        this.followService = followService;
    }

    @PostMapping("/follow/{followerId}/{followingId}")
    public ResponseEntity<String> followUser(@PathVariable Long followerId, @PathVariable Long followingId) {
        followService.followUser(followerId, followingId);
        String message = "팔로우 하였습니다.";
        return ResponseEntity.ok(message);
    }

    @PostMapping("/unfollow/{followerId}/{followingId}")
    public ResponseEntity<String> unfollowUser(@PathVariable Long followerId, @PathVariable Long followingId) {
        followService.unfollowUser(followerId, followingId);
        String message = "언팔로우 하였습니다.";
        return ResponseEntity.ok(message);
    }

    @GetMapping("/followers/{userId}")
    public List<MemberDTO> getFollowers(@PathVariable Long userId) {
        return followService.getFollowers(userId);
    }

    @GetMapping("/followings/{userId}")
    public List<MemberDTO> getFollowings(@PathVariable Long userId) {
        return followService.getFollowings(userId);
    }
}
