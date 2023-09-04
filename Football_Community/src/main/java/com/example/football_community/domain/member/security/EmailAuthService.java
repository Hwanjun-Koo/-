package com.example.football_community.domain.member.security;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
@RequiredArgsConstructor
public class EmailAuthService {
    public String generateCode() {
        Random random = new Random();

        // 1000 이상 9999 이하(4자리)의 무작위 숫자를 생성
        int code = random.nextInt(9000) + 1000;

        // 생성된 숫자를 문자열로 변환하여 반환
        return String.valueOf(code);
    }
}
