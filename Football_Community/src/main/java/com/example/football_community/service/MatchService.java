package com.example.football_community.service;

import com.example.football_community.dto.MatchDTO;
import com.example.football_community.entity.Match;
import com.example.football_community.entity.Team;
import com.example.football_community.repository.MatchRepository;
import com.example.football_community.repository.TeamRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;

@Service
@Transactional
public class MatchService {
    private final MatchRepository matchRepository;
    private final TeamRepository teamRepository;

    @Autowired
    public MatchService(MatchRepository matchRepository, TeamRepository teamRepository) {
        this.matchRepository = matchRepository;
        this.teamRepository = teamRepository;
    }

    public Match createMatch(Match match) {
        Set<Team> teams = new HashSet<>();

        for (String teamName : match.getTeamNames()) {
            Optional<Team> teamOptional = teamRepository.findByTeamName(teamName);
            if (teamOptional.isPresent()) {
                Team team = teamOptional.get();
                teams.add(team);
            } else {
                throw new RuntimeException("존재하지 않는 팀입니다.");
            }

        }
        match.setTeams(teams);
        Match savedMatch = matchRepository.save(match);
        return savedMatch;
    }

    public MatchDTO updateMatch(Long matchId, MatchDTO matchDetails) {
        Optional<Match> matchOptional = matchRepository.findById(matchId);
        if (matchOptional.isPresent()) {
            Match match = matchOptional.get();
            if (matchDetails.getMatchTime() != null) {
                match.setMatchTime(matchDetails.getMatchTime());
            }
            if (matchDetails.getVenue() != null) {
                match.setVenue(matchDetails.getVenue());
            }

            Set<Team> teams = match.getTeams();
            if (teams == null) {
                teams = new HashSet<>();
            }

            if (matchDetails.getTeamNames() != null) {
                for (String teamName : matchDetails.getTeamNames()) {
                    Optional<Team> teamOptional = teamRepository.findByTeamName(teamName);
                    if (teamOptional.isPresent()) {
                        Team team = teamOptional.get();
                        teams.add(team);
                    } else {
                        throw new RuntimeException("존재하지 않는 팀입니다.");
                    }
                }
            }

            match.setTeams(teams);
            Match updatedMatch = matchRepository.save(match);
            return convertToDTO(updatedMatch);
        } else {
            throw new RuntimeException("존재하지 않는 경기입니다.");
        }
    }


    public void deleteMatch(Long matchId) {
        Optional<Match> matchOptional = matchRepository.findById(matchId);
        if (matchOptional.isPresent()) {
            Match match = matchOptional.get();
            matchRepository.delete(match);
        } else {
            throw new RuntimeException("매치를 찾을 수 없습니다.");
        }
    }
    private MatchDTO convertToDTO(Match match){
        MatchDTO matchDTO = new MatchDTO();
        matchDTO.setMatch_id(match.getId());
        matchDTO.setMatchTime(match.getMatchTime());
        matchDTO.setVenue(match.getVenue());

        if (match.getTeamNames() != null) {
            matchDTO.setTeamNames(match.getTeamNames());
        } else {
            matchDTO.setTeamNames(new ArrayList<>());
        }
        return matchDTO;
    }
}
