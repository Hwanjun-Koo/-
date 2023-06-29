package com.example.football_community.controller;

import com.example.football_community.dto.UserDTO;
import com.example.football_community.service.FollowService;
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
    public void followUser(@PathVariable Long followerId, @PathVariable Long followingId) {
        followService.followUser(followerId, followingId);
    }

    @PostMapping("/unfollow/{followerId}/{followingId}")
    public void unfollowUser(@PathVariable Long followerId, @PathVariable Long followingId) {
        followService.unfollowUser(followerId, followingId);
    }

    @GetMapping("/followers/{userId}")
    public List<UserDTO> getFollowers(@PathVariable Long userId) {
        return followService.getFollowers(userId);
    }

    @GetMapping("/followings/{userId}")
    public List<UserDTO> getFollowings(@PathVariable Long userId) {
        return followService.getFollowings(userId);
    }
}
