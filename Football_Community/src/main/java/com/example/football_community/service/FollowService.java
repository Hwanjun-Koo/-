package com.example.football_community.service;

import com.example.football_community.dto.UserDTO;
import com.example.football_community.entity.Follow;
import com.example.football_community.entity.User;
import com.example.football_community.repository.FollowRepository;
import com.example.football_community.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class FollowService {
    private final FollowRepository followRepository;
    private final UserRepository userRepository;

    @Autowired
    public FollowService(FollowRepository followRepository, UserRepository userRepository) {
        this.followRepository = followRepository;
        this.userRepository = userRepository;
    }

    public void followUser(Long followerId, Long followingId) {
        User follower = userRepository.findById(followerId)
                .orElseThrow(() -> new RuntimeException("유저를 찾을 수 없습니다."));
        User following = userRepository.findById(followingId)
                .orElseThrow(() -> new RuntimeException("유저를 찾을 수 없습니다."));

        Follow follow = new Follow(follower, following);
        followRepository.save(follow);
    }

    public void unfollowUser(Long followerId, Long followingId) {
        Follow follow = followRepository.findByFollowerIdAndFollowingId(followerId, followingId);
        if (follow != null) {
            followRepository.delete(follow);
        }
    }

    public List<UserDTO> getFollowers(Long userId) {
        List<Follow> followers = followRepository.findByFollowingId(userId);
        return followers.stream().map(follow -> convertToUserDTO(follow.getFollower()))
                .collect(Collectors.toList());
    }

    public List<UserDTO> getFollowings(Long userId) {
        List<Follow> followings = followRepository.findByFollowerId(userId);
        return followings.stream()
                .map(follow -> convertToUserDTO(follow.getFollowing()))
                .collect(Collectors.toList());
    }

    private UserDTO convertToUserDTO(User user) {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(user.getId());
        userDTO.setUsername(user.getUsername());
        userDTO.setEmail(user.getEmail());
        userDTO.setPhoneNumber(user.getPhoneNumber());
        userDTO.setCreatedDate(user.getCreatedDate());
        userDTO.setModifiedDate(user.getModifiedDate());
        return userDTO;
    }
}
