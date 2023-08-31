package com.example.football_community.domain.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/football-community/user")
@RestController
public class MemberController {
    private final MemberService memberService;

    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody Member member) {
        memberService.createUser(member);
        return ResponseEntity.ok("회원가입이 완료되었습니다.");
    }

    @GetMapping("/{userId}")
    public ResponseEntity<MemberDTO> getUser(
            @PathVariable Long userId) {
        MemberDTO memberDTO = memberService.getUser(userId);
        return ResponseEntity.ok(memberDTO);
    }

    @GetMapping("/all")
    public ResponseEntity<List<MemberDTO>> getAllUsers() {
        List<MemberDTO> memberDTOList = memberService.getAllUser();
        return ResponseEntity.ok(memberDTOList);
    }

    @PutMapping("/edit/{userId}")
    public ResponseEntity<String> updateUser(
            @PathVariable Long userId,
            @RequestBody MemberDTO memberDTO
    ) {
        MemberDTO updatedUser = memberService.updateUser(userId, memberDTO);
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
