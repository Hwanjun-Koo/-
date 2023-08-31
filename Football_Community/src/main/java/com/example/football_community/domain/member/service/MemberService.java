package com.example.football_community.domain.member.service;

import com.example.football_community.domain.member.dto.request.*;
import com.example.football_community.domain.member.dto.response.MemberDetailResponseDto;
import com.example.football_community.domain.member.repository.EmailCodeRepository;
import com.example.football_community.domain.member.repository.MemberRepository;
import com.example.football_community.domain.member.entity.Member;
import com.example.football_community.domain.member.repository.RefreshTokenRepository;
import com.example.football_community.domain.member.security.*;
import com.example.football_community.global.email.EmailService;
import com.example.football_community.global.exception.GlobalErrorCode;
import com.example.football_community.global.exception.GlobalException;
import com.example.football_community.global.jwt.JwtUtil;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.password.PasswordEncoder;
import java.util.Optional;


@Service
@Slf4j
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;
    private final EmailCodeRepository emailCodeRepository;
    private final EmailAuthService emailAuthService;
    private final EmailService emailService;
    private final RefreshTokenRepository refreshTokenRepository;
    private final JwtUtil jwtUtil;
    private final MemberIsLoginService memberIsLoginService;

    @Transactional
    public void checkNickname (NicknameCheckRequestDto requestDto){
        Optional<Member> memberOptional = memberRepository.findByNickname(requestDto.getNickname());
        if(memberOptional.isPresent()){
            throw new GlobalException(GlobalErrorCode.DUPLICATE_NICK_NAME);
        }
    }

    @Transactional
    public void checkEmail(String email){
        Optional<Member> found = memberRepository.findByEmail(email);
        if(found.isPresent()){
            throw new GlobalException(GlobalErrorCode.DUPLICATE_EMAIL);
        }
    }

    @Transactional
    public void sendCode(EmailAuthRequestDto requestDto){
        String email = requestDto.getEmail();
        checkEmail(email);
        Optional<EmailCode> codeOptional = emailCodeRepository.findByEmail(email);
        if(codeOptional.isPresent()){
            EmailCode code = codeOptional.get();
            emailCodeRepository.delete(code);
        } else {
            String code = emailAuthService.generateCode();

            EmailCode emailCode = EmailCode.builder()
                    .email(email)
                    .code(code)
                    .build();

            emailCodeRepository.save(emailCode);

            String emailBody = "이메일을 인증하려면 아래 코드를 입력하세요:\n" + code;
            emailService.sendEmail(email, "이메일 인증 코드", emailBody);
        }
    }

    @Transactional
    public Boolean isValid(CodeCheckRequestDto requestDto) {
        String email = requestDto.getEmail();
        String code = requestDto.getCode();
        Optional<EmailCode> codeOptional = emailCodeRepository.findByEmailAndCode(email, code);
        if (codeOptional.isPresent()){
            EmailCode emailCode = codeOptional.get();
            emailCodeRepository.delete(emailCode);
            return true;
        } else {
            return false;
        }
    }
    @Transactional
    public void signup(final MemberSignupRequestDto requestDto) {
        String password = passwordEncoder.encode(requestDto.getPassword());
        createMember(requestDto, password);
    }
    @Transactional
    public void createMember(MemberSignupRequestDto requestDto, String password) {
        Member member = Member.builder()
                .memberName(requestDto.getMemberName())
                .nickname(requestDto.getNickname())
                .email(requestDto.getEmail())
                .password(password)
                .birthday(requestDto.getBirthday())
                .phoneNumber(requestDto.getPhoneNumber())
                .build();
        member.calculateAge();
        memberRepository.save(member);
    }



    @Transactional
    public void login(LoginRequestDto loginRequestDto, HttpServletResponse response) {
        String email = loginRequestDto.getEmail();
        String password = loginRequestDto.getPassword();

        // 사용자 확인
        Member member = memberRepository.findByEmail(email).orElseThrow(
                ()-> new GlobalException(GlobalErrorCode.MEMBER_NOT_FOUND)
        );

        // 비밀번호 확인
        if(!passwordEncoder.matches(password, member.getPassword())){
            throw new GlobalException(GlobalErrorCode.PASSWORD_MISMATCH);
        }

        // http 응답에 헤더 추가
        response.addHeader(JwtUtil.ACCESS_TOKEN_HEADER, jwtUtil.createAccessToken(email));
        String rawToken = jwtUtil.createRefreshToken(email);
        response.addHeader(JwtUtil.REFRESH_TOKEN_HEADER, rawToken); // 리프레시 토큰 추가
        RefreshToken refreshToken = new RefreshToken(rawToken.substring(7), email);
        refreshTokenRepository.save(refreshToken);
    }

    @Transactional
    public MemberDetailResponseDto getMemberDetail(UserDetailsImpl userDetails) {
        Member member = memberIsLoginService.isLogin(userDetails);
        return MemberDetailResponseDto.of(member);
    }

    @Transactional
    public void updateMember(UserDetailsImpl userDetails, MemberUpdateRequestDto requestDto) {
        Member targetMember = memberIsLoginService.isLogin(userDetails);
        Member member = memberRepository.findById(targetMember.getMemberId())
                .orElseThrow(() -> new GlobalException(GlobalErrorCode.MEMBER_NOT_FOUND));
        member.updateMemberInfo(requestDto);
    }

    @Transactional
    public void deleteMember(UserDetailsImpl userDetails) {
        Member targetMember = memberIsLoginService.isLogin(userDetails);
        Member member = memberRepository.findById(targetMember.getMemberId())
                .orElseThrow(() -> new GlobalException(GlobalErrorCode.MEMBER_NOT_FOUND));
        memberRepository.delete(member);
    }

}
