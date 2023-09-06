package com.example.football_community.domain.team.controller;

import com.example.football_community.domain.team.dto.request.TeamRegisterRequestDto;
import com.example.football_community.domain.team.dto.request.TeamUpdateRequestDto;
import com.example.football_community.domain.team.service.TeamService;
import com.example.football_community.global.message.ResponseMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RequestMapping("/team")
@RequiredArgsConstructor
@RestController
public class TeamController {
    private final TeamService teamService;

    @PostMapping("/register")
    public ResponseEntity registerTeam(
            @RequestBody TeamRegisterRequestDto requestDto
    ) {
        teamService.registerTeam(requestDto);
        return ResponseMessage.SuccessResponse("팀 등록 성공", "");
    }

    @GetMapping("/{teamId}")
    public ResponseEntity getTeamInfo(
            @PathVariable Long teamId
    ) {
        return ResponseMessage.SuccessResponse("팀 정보 불러오기 성공", teamService.getTeamInfo(teamId));
    }

    @PutMapping("/{teamId}")
    public ResponseEntity updateTeamInfo(
            @PathVariable Long teamId,
            @RequestBody TeamUpdateRequestDto requestDto
    ) {
        teamService.updateTeamInfo(teamId, requestDto);
        return ResponseMessage.SuccessResponse("팀 정보 수정 성공", "");
    }

    @DeleteMapping("/{teamId}")
    public ResponseEntity deleteTeam(
            @PathVariable Long teamId
    ) {
        teamService.deleteTeam(teamId);
        return ResponseMessage.SuccessResponse("팀 삭제 성공", "");
    }
}
