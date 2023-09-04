package com.example.football_community.domain.member.dto.request;

import lombok.*;

@Getter
@NoArgsConstructor
@AllArgsConstructor
//이메일 인증코드를 발송하기 위한 DTO
public class EmailAuthRequestDto {
    //코드를 발송할 이메일
    private String email;
}
