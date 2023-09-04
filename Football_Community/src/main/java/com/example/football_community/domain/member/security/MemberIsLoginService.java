package com.example.football_community.domain.member.security;


import com.example.football_community.domain.member.entity.Member;
import com.example.football_community.global.exception.GlobalErrorCode;
import com.example.football_community.global.exception.GlobalException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
//로그인 상태를 확인하는 클래스
public class MemberIsLoginService {

    @Transactional
    public Member isLogin(UserDetailsImpl userDetails){
        if(userDetails != null){
            return userDetails.getMember();
        } else{
            throw new GlobalException(GlobalErrorCode.LOGIN_REQUIRED);
        }
    }
}
