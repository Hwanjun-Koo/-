package com.example.football_community.domain.member.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class CodeCheckRequestDto {
    @NotBlank(message = "이메일을 입력해 주세요.")
    private String email;

    @NotBlank(message = "인증 코드를 입력해 주세요.")
    private String code;
}
