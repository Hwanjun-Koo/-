package com.example.football_community.domain.member.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
//이메일 인증코드를 확인하는 DTO
public class CodeCheckRequestDto {
    //코드를 받은 이메일
    @NotBlank(message = "이메일을 입력해 주세요.")
    private String email;

    //이메일 속 코드
    @NotBlank(message = "인증 코드를 입력해 주세요.")
    private String code;
}
