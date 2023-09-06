package com.example.football_community.domain.league.service;

import com.example.football_community.domain.league.dto.request.LeagueRegisterRequestDto;
import com.example.football_community.domain.league.dto.request.TeamsInLeagueRequestDto;
import com.example.football_community.domain.league.dto.response.LeagueInfoResponseDto;
import com.example.football_community.domain.league.entity.League;
import com.example.football_community.domain.league.repository.LeagueRepository;
import com.example.football_community.domain.team.entity.Team;
import com.example.football_community.domain.team.repository.TeamRepository;
import com.example.football_community.global.exception.GlobalErrorCode;
import com.example.football_community.global.exception.GlobalException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class LeagueService {
    private final LeagueRepository leagueRepository;
    private final TeamRepository teamRepository;

    @Transactional
    public void registerLeague(LeagueRegisterRequestDto requestDto) {
        League league = League.builder()
                .leagueName(requestDto.getLeagueName())
                .country(requestDto.getCountry())
                .foundDate(requestDto.getFoundDate())
                .build();
        leagueRepository.save(league);
    }

    @Transactional
    public LeagueInfoResponseDto getLeagueInfo(Long leagueId) {
        League league = leagueRepository.findById(leagueId)
                .orElseThrow(() -> new GlobalException(GlobalErrorCode.LEAGUE_NOT_FOUND));
        return LeagueInfoResponseDto.of(league);
    }

    @Transactional
    public void updateTeamsInLeague(Long leagueId, List<TeamsInLeagueRequestDto> requestDtos){
        League league = leagueRepository.findById(leagueId)
                .orElseThrow(() -> new GlobalException(GlobalErrorCode.LEAGUE_NOT_FOUND));
        Map<Long, Team> teamMap = teamRepository.findAllByLeague(league)
                .stream()
                .collect(Collectors.toMap(Team::getTeamId, Function.identity()));
        List<Team> teamsToDelete = teamMap.values().stream()
                .filter(team -> requestDtos.stream()
                        .noneMatch(dto -> dto.getTeamId().equals(team.getTeamId())))
                .collect(Collectors.toList());
        for (Team team : teamsToDelete) {
            league.removeTeam(team);
        }

        for (TeamsInLeagueRequestDto requestDto : requestDtos) {
            Long teamId = requestDto.getTeamId();
            Team team = teamRepository.findById(teamId)
                    .orElseThrow(() -> new GlobalException(GlobalErrorCode.TEAM_NOT_FOUND));
            if (!teamMap.containsKey(teamId)) {
                league.addTeam(team);
            }
        }
    }

    @Transactional
    public void deleteLeague(Long leagueId) {
        League league = leagueRepository.findById(leagueId)
                .orElseThrow(() -> new GlobalException(GlobalErrorCode.LEAGUE_NOT_FOUND));
        leagueRepository.delete(league);
    }
}
