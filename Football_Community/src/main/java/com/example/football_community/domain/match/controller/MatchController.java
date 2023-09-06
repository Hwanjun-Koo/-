package com.example.football_community.domain.match.controller;

import com.example.football_community.domain.match.dto.request.MatchRegisterRequestDto;
import com.example.football_community.domain.match.dto.request.MatchUpdateRequestDto;
import com.example.football_community.domain.match.service.MatchService;
import com.example.football_community.global.message.ResponseMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/match")
@RequiredArgsConstructor
public class MatchController {
    private final MatchService matchService;

    @PostMapping("/register")
    public ResponseEntity registerMatch(
            @RequestBody MatchRegisterRequestDto requestDto) {
        matchService.registerMatch(requestDto);
        return ResponseMessage.SuccessResponse("경기 등록 성공", "");
    }

    @GetMapping("/{matchId}")
    public ResponseEntity getMatchInfo(
            @PathVariable Long matchId
    ) {
        return ResponseMessage.SuccessResponse("경기 정보 불러오기 성공", matchService.getMatchInfo(matchId));
    }

    @PutMapping("/{matchId}")
    public ResponseEntity updateMatch(
            @PathVariable Long matchId,
            @RequestBody MatchUpdateRequestDto requestDto
    ) {
        matchService.updateMatchInfo(matchId, requestDto);
        return ResponseMessage.SuccessResponse("경기 정보 수정 성공", "");
    }

    @DeleteMapping("/{matchId}")
    public ResponseEntity deleteMatch(
            @PathVariable Long matchId
    ) {
        matchService.deleteMatch(matchId);
        return ResponseMessage.SuccessResponse("경기 삭제 성공", "");
    }
}
