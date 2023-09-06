package com.example.football_community.domain.match.service;

import com.example.football_community.domain.match.dto.request.MatchRegisterRequestDto;
import com.example.football_community.domain.match.dto.request.MatchUpdateRequestDto;
import com.example.football_community.domain.match.dto.response.MatchInfoResponseDto;
import com.example.football_community.domain.match.entity.Match;
import com.example.football_community.domain.match.repository.MatchRepository;
import com.example.football_community.domain.team.entity.Team;
import com.example.football_community.domain.team.repository.TeamRepository;
import com.example.football_community.global.exception.GlobalErrorCode;
import com.example.football_community.global.exception.GlobalException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MatchService {
    private final MatchRepository matchRepository;
    private final TeamRepository teamRepository;

    @Transactional
    public void registerMatch(MatchRegisterRequestDto requestDto) {
        Team homeTeam = teamRepository.findById(requestDto.getHomeTeamId())
                .orElseThrow(() -> new GlobalException(GlobalErrorCode.TEAM_NOT_FOUND));
        Team awayTeam = teamRepository.findById(requestDto.getAwayTeamId())
                .orElseThrow(() -> new GlobalException(GlobalErrorCode.TEAM_NOT_FOUND));
        Match match = Match.builder()
                .homeTeam(homeTeam)
                .awayTeam(awayTeam)
                .ground(requestDto.getGround())
                .matchDate(requestDto.getMatchDate())
                .build();
        matchRepository.save(match);
    }

    @Transactional
    public MatchInfoResponseDto getMatchInfo(Long matchId) {
        Match match = matchRepository.findById(matchId)
                .orElseThrow(() -> new GlobalException(GlobalErrorCode.MATCH_NOT_FOUND));
        return MatchInfoResponseDto.of(match);
    }

    @Transactional
    public void updateMatchInfo(Long matchId, MatchUpdateRequestDto requestDto) {
        Match match = matchRepository.findById(matchId)
                .orElseThrow(() -> new GlobalException(GlobalErrorCode.MATCH_NOT_FOUND));
        match.updateMatchInfo(requestDto);
    }

    @Transactional
    public void deleteMatch(Long matchId) {
        Match match = matchRepository.findById(matchId)
                .orElseThrow(() -> new GlobalException(GlobalErrorCode.MATCH_NOT_FOUND));
        matchRepository.delete(match);
    }
}
