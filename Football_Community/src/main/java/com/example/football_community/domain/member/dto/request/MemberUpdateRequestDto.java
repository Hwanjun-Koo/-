package com.example.football_community.domain.member.dto.request;

import lombok.*;

@Getter
@NoArgsConstructor
@AllArgsConstructor
//회원 정보 수정 요청 DTO
public class MemberUpdateRequestDto {
    private String memberName;
    private String nickname;
    private String phoneNumber;
    private String birthday;
}
