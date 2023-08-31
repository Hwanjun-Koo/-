package com.example.football_community.domain.member.dto.request;

import lombok.*;

@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class MemberUpdateRequestDto {
    private String memberName;
    private String nickname;
    private String phoneNumber;
    private String birthday;
}
