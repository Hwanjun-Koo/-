package com.example.football_community.domain.schedule.service;

import com.example.football_community.domain.match.dto.response.MatchInfoResponseDto;
import com.example.football_community.domain.match.entity.Match;
import com.example.football_community.domain.match.repository.MatchRepository;
import com.example.football_community.domain.schedule.dto.ScheduleResponseDto;
import com.example.football_community.domain.team.entity.Team;
import com.example.football_community.domain.team.repository.TeamRepository;
import com.example.football_community.global.exception.GlobalErrorCode;
import com.example.football_community.global.exception.GlobalException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ScheduleService {
    private final MatchRepository matchRepository;
    private final TeamRepository teamRepository;

    public ScheduleResponseDto getSchedules(Long teamId) {
        Team team = teamRepository.findById(teamId)
                .orElseThrow(() -> new GlobalException(GlobalErrorCode.TEAM_NOT_FOUND));
        List<Match> matches = matchRepository.findMatchesByTeam(team);
        List<MatchInfoResponseDto> dtos = matches.stream()
                .map(MatchInfoResponseDto::of)
                .collect(Collectors.toList());
        return ScheduleResponseDto.builder()
                .matchInfos(dtos)
                .build();
    }
}
