package com.example.football_community.domain.member.dto.request;

import lombok.*;

import java.time.LocalDate;
@Getter
@NoArgsConstructor
@AllArgsConstructor
//회원가입 요청 DTO
public class MemberSignupRequestDto {
    private String memberName;
    private String nickname;
    private String email;
    private String password;
    private String phoneNumber;
    // 생년월일 6자리 - YYMMDD 형식
    private String birthday;


}
