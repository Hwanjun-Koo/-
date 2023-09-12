package com.example.football_community.domain.follow.dto;

import com.example.football_community.domain.follow.entity.Follow;
import com.example.football_community.domain.member.entity.Member;
import lombok.*;

@Getter
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class FollowersResponseDto {
    private Long memberId;
    private String memberName;
    private String nickname;

    public static FollowersResponseDto of(Follow follow) {
        Member member = follow.getMember();
        return FollowersResponseDto.builder()
                .memberId(member.getMemberId())
                .memberName(member.getMemberName())
                .nickname(member.getNickname())
                .build();
    }
}
