package com.example.football_community.service;

import com.example.football_community.dto.MatchDTO;
import com.example.football_community.dto.TeamScheduleDTO;
import com.example.football_community.entity.Match;
import com.example.football_community.entity.Team;
import com.example.football_community.entity.TeamSchedule;
import com.example.football_community.repository.MatchRepository;
import com.example.football_community.repository.TeamRepository;
import com.example.football_community.repository.TeamScheduleRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@Transactional
public class TeamScheduleService {
    private final TeamScheduleRepository teamScheduleRepository;
    private final MatchRepository matchRepository;
    private final TeamRepository teamRepository;

    @Autowired
    public TeamScheduleService(TeamScheduleRepository teamScheduleRepository, MatchRepository matchRepository, TeamRepository teamRepository) {
        this.teamScheduleRepository = teamScheduleRepository;
        this.matchRepository = matchRepository;
        this.teamRepository = teamRepository;
    }


    public TeamScheduleDTO getSchedule(Long teamId) {
        Optional<Team> teamOptional = teamRepository.findById(teamId);
        if (teamOptional.isPresent()) {
            Team team = teamOptional.get();
            TeamSchedule teamSchedule = teamScheduleRepository.findByTeam(team);
            if (teamSchedule != null) {
                return convertToDTO(teamSchedule);
            } else {
                throw new RuntimeException("스케줄을 찾을 수 없습니다.");
            }
        } else {
            throw new RuntimeException("팀을 찾을 수 없습니다.");
        }
    }


    private static TeamScheduleDTO convertToDTO(TeamSchedule teamSchedule) {
        TeamScheduleDTO teamScheduleDTO = new TeamScheduleDTO();
        teamScheduleDTO.setScheduleId(teamSchedule.getId());
        List<Match> matches = teamSchedule.getMatches();
        if (matches != null) {
            List<MatchDTO> matchDTOs = matches.stream()
                    .map(match -> convertToMatchDTO(match))
                    .collect(Collectors.toList());
            teamScheduleDTO.setMatchDTOS(matchDTOs);
        }

        return teamScheduleDTO;

    }

    private static MatchDTO convertToMatchDTO(Match match) {
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
