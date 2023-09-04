package com.example.football_community.domain.member.dto.request;

import lombok.*;

@Getter
@NoArgsConstructor
@AllArgsConstructor
//닉네임 중복확인 요청 DTO
public class NicknameCheckRequestDto {
    private String nickname;
}
