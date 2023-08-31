package com.example.football_community.domain.member.controller;

import com.example.football_community.domain.member.dto.request.LoginRequestDto;
import com.example.football_community.domain.member.dto.request.MemberSignupRequestDto;
import com.example.football_community.domain.member.dto.request.MemberUpdateRequestDto;
import com.example.football_community.domain.member.dto.response.MemberDetailResponseDto;
import com.example.football_community.domain.member.security.UserDetailsImpl;
import com.example.football_community.domain.member.service.MemberService;
import com.example.football_community.global.message.ResponseMessage;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;


@RequestMapping("/member")
@RestController
@RequiredArgsConstructor
@Slf4j
public class MemberController {
    private final MemberService memberService;

    @PostMapping("/signup")
    public ResponseEntity<String> register(@RequestBody MemberSignupRequestDto requestDto) {
        memberService.signup(requestDto);
        return ResponseMessage.SuccessResponse("회원가입이 완료되었습니다.", "");
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequestDto loginRequestDto, HttpServletResponse response) {
        memberService.login(loginRequestDto, response);
        return ResponseMessage.SuccessResponse("로그인 성공", "");
    }

    @GetMapping("/profile")
    public ResponseEntity<MemberDetailResponseDto> myProfile(
            @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return new ResponseEntity<>(memberService.getMemberDetail(userDetails), HttpStatus.OK);
    }


    @PutMapping("/edit")
    public ResponseEntity updateMember(
            @RequestBody MemberUpdateRequestDto requestDto,
            @AuthenticationPrincipal UserDetailsImpl userDetails
    ) {
        memberService.updateMember(userDetails, requestDto);
        return ResponseMessage.SuccessResponse("정보가 수정되었습니다.", "");
    }

    @DeleteMapping("/delete")
    public ResponseEntity<String> deleteMember(
            @AuthenticationPrincipal UserDetailsImpl userDetails
    ) {
        memberService.deleteMember(userDetails);
        return ResponseMessage.SuccessResponse("회원탈퇴가 완료되었습니다.", "");
    }

}
