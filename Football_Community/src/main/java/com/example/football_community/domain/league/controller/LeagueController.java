package com.example.football_community.domain.league.controller;

import com.example.football_community.domain.league.dto.request.LeagueRegisterRequestDto;
import com.example.football_community.domain.league.dto.request.TeamsInLeagueRequestDto;
import com.example.football_community.domain.league.service.LeagueService;
import com.example.football_community.global.message.ResponseMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RequestMapping("/league")
@RequiredArgsConstructor
@RestController
public class LeagueController {
    private final LeagueService leagueService;

    @PostMapping("/register")
    public ResponseEntity registerLeague(
            @RequestBody LeagueRegisterRequestDto requestDto
    ) {
        leagueService.registerLeague(requestDto);
        return ResponseMessage.SuccessResponse("리그가 등록되었습니다.", "");
    }

    @GetMapping("/{leagueId}")
    public ResponseEntity getLeagueInfo(
            @PathVariable Long leagueId
    ) {
        return ResponseMessage.SuccessResponse("리그 정보 불러오기에 성공하였습니다.", leagueService.getLeagueInfo(leagueId));
    }

    @PostMapping("/{leagueId}/teams")
    public ResponseEntity updateTeamsInLeague(
            @PathVariable Long leagueId,
            @RequestBody List<TeamsInLeagueRequestDto> requestDtos
    ) {
        leagueService.updateTeamsInLeague(leagueId, requestDtos);
        return ResponseMessage.SuccessResponse("참가 팀 정보가 업데이트 되었습니다", "");
    }

    @DeleteMapping("/{leagueId}")
    public ResponseEntity deleteLeague(
            @PathVariable Long leagueId
    ) {
        leagueService.deleteLeague(leagueId);
        return ResponseMessage.SuccessResponse("리그가 삭제되었습니다.", "");
    }
}
