package com.example.football_community.service;

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

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
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

    public TeamSchedule createSchedule(String teamName) {
        Optional<Team> teamOptional = teamRepository.findByTeamName(teamName);
        if (teamOptional.isEmpty()) {
            throw new RuntimeException("팀을 찾을 수 없습니다.");
        }

        Team team = teamOptional.get();

        List<Match> matches = matchRepository.findByTeamsContaining(team);
        TeamSchedule teamSchedule = new TeamSchedule();
        teamSchedule.setTeam(team);
        teamSchedule.setMatches(new HashSet<>(matches));

        TeamSchedule savedSchedule = teamScheduleRepository.save(teamSchedule);
        return savedSchedule;
    }

    public List<TeamScheduleDTO> getSchedules(String teamName) {
        Optional<Team> teamOptional = teamRepository.findByTeamName(teamName);
        if (teamOptional.isEmpty()) {
            throw new RuntimeException("팀을 찾을 수 없습니다.");
        }

        Team team = teamOptional.get();

        List<Match> allMatches = matchRepository.findAll();
        List<Match> teamMatches = allMatches.stream()
                .filter(match -> match.getTeams().contains(team))
                .collect(Collectors.toList());

        return teamMatches.stream()
                .map(match -> convertToDTO(match, team))
                .collect(Collectors.toList());
    }

    public void updateSchedule(Long scheduleId, TeamScheduleDTO scheduleDetails) {
        Optional<TeamSchedule> scheduleOptional = teamScheduleRepository.findById(scheduleId);
        if (scheduleOptional.isPresent()) {
            TeamSchedule schedule = scheduleOptional.get();

            if (scheduleDetails.getMatchTime() != null) {
                schedule.getMatches().forEach(match -> match.setMatchTime(scheduleDetails.getMatchTime()));
            }
            if (scheduleDetails.getVenue() != null) {
                schedule.getMatches().forEach(match -> match.setVenue(scheduleDetails.getVenue()));
            }

            teamScheduleRepository.save(schedule);
        } else {
            throw new RuntimeException("존재하지 않는 팀 스케줄입니다.");
        }
    }
    public void deleteSchedule(Match match) {
        Optional<TeamSchedule> teamScheduleOptional = teamScheduleRepository.findByMatchesContaining(match);
        if (teamScheduleOptional.isPresent()) {
            TeamSchedule teamSchedule = teamScheduleOptional.get();
            teamScheduleRepository.delete(teamSchedule);
        }
    }

    private static TeamScheduleDTO convertToDTO(Match match, Team team) {
        Team opposingTeam = match.getTeams().stream()
                .filter(t -> !t.equals(team))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("상대 팀을 찾을 수 없습니다."));

        TeamScheduleDTO scheduleDTO = new TeamScheduleDTO();
        scheduleDTO.setMatchTime(match.getMatchTime());
        scheduleDTO.setOpposingTeam(opposingTeam.getTeamName());
        scheduleDTO.setVenue(match.getVenue());
        return scheduleDTO;
    }
}
