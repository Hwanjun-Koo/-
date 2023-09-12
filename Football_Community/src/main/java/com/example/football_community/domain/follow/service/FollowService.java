package com.example.football_community.domain.follow.service;

import com.example.football_community.domain.follow.dto.FollowersResponseDto;
import com.example.football_community.domain.follow.dto.FollowingsResponseDto;
import com.example.football_community.domain.follow.entity.Follow;
import com.example.football_community.domain.follow.repository.FollowRepository;
import com.example.football_community.domain.member.entity.Member;
import com.example.football_community.domain.member.repository.MemberRepository;
import com.example.football_community.domain.member.security.MemberIsLoginService;
import com.example.football_community.domain.member.security.UserDetailsImpl;
import com.example.football_community.global.exception.GlobalErrorCode;
import com.example.football_community.global.exception.GlobalException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FollowService {
    private final FollowRepository followRepository;
    private final MemberRepository memberRepository;
    private final MemberIsLoginService memberIsLoginService;

    @Transactional
    public void followMember(Long memberId, UserDetailsImpl userDetails) {
        Member member = memberIsLoginService.isLogin(userDetails);

        Member targetMember = memberRepository.findById(memberId)
                .orElseThrow(() -> new GlobalException(GlobalErrorCode.MEMBER_NOT_FOUND));

        Optional<Follow> followOptional = followRepository.findByMemberAndTargetMember(member, targetMember);
        if(followOptional.isPresent()){
            Follow follow = followOptional.get();
            targetMember.removeFollower();
            member.removeFollowing();
            followRepository.delete(follow);
        } else {
            Follow follow = Follow.builder()
                    .member(member)
                    .targetMember(targetMember)
                    .build();
            targetMember.addFollower();
            member.addFollowing();
            followRepository.save(follow);
        }
    }

    @Transactional
    public boolean isFollowed(Long memberId, UserDetailsImpl userDetails){
        Member member = memberIsLoginService.isLogin(userDetails);

        Member targetMember = memberRepository.findById(memberId)
                .orElseThrow(() -> new GlobalException(GlobalErrorCode.MEMBER_NOT_FOUND));

        Optional<Follow> followOptional = followRepository.findByMemberAndTargetMember(member, targetMember);
        if (followOptional.isPresent()) {
            return true;
        } else {
            return false;
        }
    }

    @Transactional
    public List<FollowingsResponseDto> getFollowings(Long memberId){
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new GlobalException(GlobalErrorCode.MEMBER_NOT_FOUND));
        List<Follow> follows = followRepository.findByMember(member);
        List<FollowingsResponseDto> dtos = follows.stream()
                .map(FollowingsResponseDto::of)
                .collect(Collectors.toList());
        return dtos;
    }

    @Transactional
    public List<FollowersResponseDto> getFollowers(Long memberId){
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new GlobalException(GlobalErrorCode.MEMBER_NOT_FOUND));
        List<Follow> follows = followRepository.findByTargetMember(member);
        List<FollowersResponseDto> dtos = follows.stream()
                .map(FollowersResponseDto::of)
                .collect(Collectors.toList());
        return dtos;
    }
}
