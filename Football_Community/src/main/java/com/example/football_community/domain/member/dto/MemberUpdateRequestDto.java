package com.example.football_community.domain.member.dto;

import lombok.*;

@Getter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class MemberUpdateRequestDto {
    private String memberName;
    private String nickname;
    private String phoneNumber;
    private String birthday;
}
