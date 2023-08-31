package com.example.football_community.domain.member.dto.request;

import lombok.*;

import java.time.LocalDate;
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class MemberSignupRequestDto {
    private String memberName;
    private String nickname;

    private String email;
    private String password;
    private String phoneNumber;
    private String birthday;


}
