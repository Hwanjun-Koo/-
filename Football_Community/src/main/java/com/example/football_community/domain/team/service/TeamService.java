package com.example.football_community.domain.team.service;

import com.example.football_community.domain.team.dto.request.TeamRegisterRequestDto;
import com.example.football_community.domain.team.dto.request.TeamUpdateRequestDto;
import com.example.football_community.domain.team.dto.response.TeamInfoResponseDto;
import com.example.football_community.domain.team.entity.Team;
import com.example.football_community.domain.team.repository.TeamRepository;
import com.example.football_community.global.exception.GlobalErrorCode;
import com.example.football_community.global.exception.GlobalException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TeamService {
    private final TeamRepository teamRepository;

    @Transactional
    public void registerTeam(TeamRegisterRequestDto requestDto) {
        Team team = Team.builder()
                .teamName(requestDto.getTeamName())
                .league(requestDto.getLeague())
                .ground(requestDto.getGround())
                .headCoach(requestDto.getHeadCoach())
                .foundDate(requestDto.getFoundDate())
                .build();
    }

    @Transactional
    public TeamInfoResponseDto getTeamInfo(Long teamId) {
        Team team = teamRepository.findById(teamId)
                .orElseThrow(() -> new GlobalException(GlobalErrorCode.TEAM_NOT_FOUND));
        return TeamInfoResponseDto.of(team);
    }

    @Transactional
    public void updateTeamInfo(Long teamId, TeamUpdateRequestDto requestDto) {
        Team team = teamRepository.findById(teamId)
                .orElseThrow(() -> new GlobalException(GlobalErrorCode.TEAM_NOT_FOUND));
        team.updateTeamInfo(requestDto);
    }

    @Transactional
    public void deleteTeam(Long teamId) {
        Team team = teamRepository.findById(teamId)
                .orElseThrow(() -> new GlobalException(GlobalErrorCode.TEAM_NOT_FOUND));
        teamRepository.delete(team);
    }
}
