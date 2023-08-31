package com.example.football_community.domain.member.service;

import com.example.football_community.domain.member.dto.MemberSignupRequestDto;
import com.example.football_community.domain.member.repository.MemberRepository;
import com.example.football_community.domain.member.entity.Member;
import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public void checkNickname (NicknameCheckRequestDto request){
        Optional<Member> found = memberRepository.findByNickName(request.getNickName());
        if(found.isPresent()){
            throw new GlobalException(GlobalErrorCode.DUPLICATE_NICK_NAME);
        }
    }
    @Transactional
    public void signup(final MemberSignupRequestDto signupRequestDto) {
        String password = passwordEncoder.encode(signupRequestDto.getPassword());
        createMember(signupRequestDto, password);
    }
    @Transactional
    public void createMember(MemberSignupRequestDto requestDto, String password) {
        Member member = Member.builder()
                .memberName(requestDto.getMemberName())
                .nickname(requestDto.getNickname())
                .email(requestDto.getEmail())
                .password(requestDto.getPassword())
                .birthday(requestDto.getBirthday())
                .phoneNumber(requestDto.getPhoneNumber())
                .build();
        member.calculateAge();
    }

    @Transactional
    public void sendCode(EmailAuthenticationRequestDto request){
        String email = request.getEmail();
        checkEmail(email);
        Optional<EmailCode> codeOptional = emailCodeRepository.findByEmail(email);
        if(codeOptional.isPresent()){
            EmailCode code = codeOptional.get();
            emailCodeRepository.delete(code);
        } else {
            String code = emailAuthenticationService.generateCode();

            EmailCode emailCode = EmailCode.builder()
                    .email(email)
                    .code(code)
                    .build();

            emailCodeRepository.save(emailCode);

            String emailBody = "이메일을 인증하려면 아래 코드를 입력하세요:\n" + code;
            emailService.sendEmail(email, "KnockKnock 이메일 인증 코드", emailBody);
        }
    }

    @Transactional
    public Boolean isValid(CheckAuthCodeRequestDto request) {
        String email = request.getEmail();
        String code = request.getCode();
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
    public MemberDetailResponseDto getMemberDetail(UserDetailsImpl userDetails) {
        Member member = memberIsLoginService.isLogin(userDetails);

        return MemberDetailResponseDto.of(member);
    }

    @Transactional
    public List<GetMembersResponseDto> getAllMembers() {
        List<Member> members = memberRepository.findAll();
        return members.stream()
                .map(GetMembersResponseDto::from)
                .collect(Collectors.toList());
    }

    @Transactional
    public void updateMember(UserDetailsImpl userDetails, MemberUpdateRequestDto request, MultipartFile profileImage) {
        Member targetMember = memberIsLoginService.isLogin(userDetails);
        Member member = memberRepository.findById(targetMember.getMemberId())
                .orElseThrow(() -> new GlobalException(GlobalErrorCode.MEMBER_NOT_FOUND));
        member.updateMember(request);
    }

    @Transactional
    public void deleteMember(UserDetailsImpl userDetails) {
        Member targetMember = memberIsLoginService.isLogin(userDetails);
        Member member = memberRepository.findById(targetMember.getMemberId())
                .orElseThrow(() -> new GlobalException(GlobalErrorCode.MEMBER_NOT_FOUND));
        memberRepository.delete(member);
    }

}
