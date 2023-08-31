package com.example.football_community.domain.member.controller;

import com.example.football_community.domain.member.dto.MemberSignupRequestDto;
import com.example.football_community.domain.member.service.MemberService;
import com.example.football_community.domain.member.entity.Member;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/football-community/member")
@RestController
@RequiredArgsConstructor
@Slf4j

public class MemberController {
    private final MemberService memberService;

    @PostMapping("/signup")
    public ResponseEntity<String> register(@RequestBody Member member) {
        memberService.createUser(member);
        return ResponseEntity.ok("회원가입이 완료되었습니다.");
    }

    @GetMapping("/{userId}")
    public ResponseEntity<MemberSignupRequestDto> getUser(
            @PathVariable Long userId) {
        MemberSignupRequestDto memberSignupRequestDto = memberService.getUser(userId);
        return ResponseEntity.ok(memberSignupRequestDto);
    }

    @GetMapping("/all")
    public ResponseEntity<List<MemberSignupRequestDto>> getAllUsers() {
        List<MemberSignupRequestDto> memberSignupRequestDtoList = memberService.getAllUser();
        return ResponseEntity.ok(memberSignupRequestDtoList);
    }

    @PutMapping("/edit/{userId}")
    public ResponseEntity<String> updateUser(
            @PathVariable Long userId,
            @RequestBody MemberSignupRequestDto memberSignupRequestDto
    ) {
        MemberSignupRequestDto updatedUser = memberService.updateUser(userId, memberSignupRequestDto);
        String message = updatedUser.getUsername() + "님의 정보가 수정되었습니다.";
        return ResponseEntity.ok(message + "\n" + updatedUser.toString());
    }

    @DeleteMapping("/delete/{userId}")
    public ResponseEntity<String> deleteUser(
            @PathVariable Long userId
    ) {
        memberService.deleteUser(userId);
        return ResponseEntity.ok("회원탈퇴가 완료되었습니다.");
    }

}
