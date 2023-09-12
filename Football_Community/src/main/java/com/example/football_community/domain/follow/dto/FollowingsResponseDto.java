package com.example.football_community.domain.follow.dto;

import com.example.football_community.domain.follow.entity.Follow;
import com.example.football_community.domain.member.entity.Member;
import lombok.*;

@Getter
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class FollowingsResponseDto {
    private Long memberId;
    private String memberName;
    private String nickname;

    public static FollowingsResponseDto of(Follow follow) {
        Member targetMember = follow.getTargetMember();
        return FollowingsResponseDto.builder()
                .memberId(targetMember.getMemberId())
                .memberName(targetMember.getMemberName())
                .nickname(targetMember.getNickname())
                .build();
    }
}
