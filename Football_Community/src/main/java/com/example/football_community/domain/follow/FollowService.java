package com.example.football_community.domain.follow;

import com.example.football_community.domain.member.MemberDTO;
import com.example.football_community.domain.member.Member;
import com.example.football_community.domain.member.MemberRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class FollowService {
    private final FollowRepository followRepository;
    private final MemberRepository memberRepository;

    @Autowired
    public FollowService(FollowRepository followRepository, MemberRepository memberRepository) {
        this.followRepository = followRepository;
        this.memberRepository = memberRepository;
    }

    public void followUser(Long followerId, Long followingId) {
        Member follower = memberRepository.findById(followerId)
                .orElseThrow(() -> new RuntimeException("유저를 찾을 수 없습니다."));
        Member following = memberRepository.findById(followingId)
                .orElseThrow(() -> new RuntimeException("유저를 찾을 수 없습니다."));

        Follow follow = followRepository.findByFollowerIdAndFollowingId(followerId, followingId);
        if (follow == null) {
            follow = new Follow(follower, following);
            followRepository.save(follow);
            follower.addFollowing(follow);
            following.addFollower(follow);
        }
    }

    public void unfollowUser(Long followerId, Long followingId) {
        Member follower = memberRepository.findById(followerId)
                .orElseThrow(() -> new RuntimeException("유저를 찾을 수 없습니다."));
        Member following = memberRepository.findById(followingId)
                .orElseThrow(() -> new RuntimeException("유저를 찾을 수 없습니다."));
        Follow follow = followRepository.findByFollowerIdAndFollowingId(followerId, followingId);
        if (follow != null) {
            followRepository.delete(follow);
            follower.removeFollowing(follow);
            following.removeFollower(follow);
        }
    }

    public List<MemberDTO> getFollowers(Long userId) {
        List<Follow> followers = followRepository.findByFollowingId(userId);
        return followers.stream().map(follow -> convertToUserDTO(follow.getFollower()))
                .collect(Collectors.toList());
    }

    public List<MemberDTO> getFollowings(Long userId) {
        List<Follow> followings = followRepository.findByFollowerId(userId);
        return followings.stream()
                .map(follow -> convertToUserDTO(follow.getFollowing()))
                .collect(Collectors.toList());
    }

    private MemberDTO convertToUserDTO(Member member) {
        MemberDTO memberDTO = new MemberDTO();
        memberDTO.setId(member.getId());
        memberDTO.setUsername(member.getUsername());
        memberDTO.setEmail(member.getEmail());
        memberDTO.setPhoneNumber(member.getPhoneNumber());
        memberDTO.setCreatedDate(member.getCreatedDate());
        memberDTO.setModifiedDate(member.getModifiedDate());
        return memberDTO;
    }
}
