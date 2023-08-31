package com.example.football_community.domain.member.security;


import com.example.football_community.domain.member.entity.Member;
import com.example.football_community.global.exception.GlobalErrorCode;
import com.example.football_community.global.exception.GlobalException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class MemberIsLoginService {

    @Transactional
    public Member isLogin(UserDetailsImpl userDetails){
        if(userDetails != null){
            return userDetails.getUser();
        } else{
            throw new GlobalException(GlobalErrorCode.LOGIN_REQUIRED);
        }
    }
}
