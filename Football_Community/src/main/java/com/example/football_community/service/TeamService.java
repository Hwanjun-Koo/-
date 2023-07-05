package com.example.football_community.service;

import com.example.football_community.dto.TeamDTO;
import com.example.football_community.entity.Team;
import com.example.football_community.entity.TeamSchedule;
import com.example.football_community.repository.TeamRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class TeamService {
    private final TeamRepository teamRepository;

    @Autowired
    public TeamService(TeamRepository teamRepository) {
        this.teamRepository = teamRepository;
    }

    public TeamDTO createTeam(Team team){
        team.setTeamName(team.getTeamName());
        team.setLeague(team.getLeague());
        team.setHome_stadium(team.getHome_stadium());
        Team savedTeam = teamRepository.save(team);
        TeamSchedule schedule = new TeamSchedule();
        schedule.setTeam(savedTeam);
        return convertToDTO(savedTeam);
    }

    public TeamDTO getTeam(Long teamId) {
        Optional<Team> teamOptional = teamRepository.findById(teamId);
        if(teamOptional.isPresent()) {
            Team team = teamOptional.get();
            return convertToDTO(team);
        } else {
            throw new RuntimeException("존재하지 않는 축구 팀입니다.");
        }
    }

    public List<TeamDTO> getAllTeam() {
        List<Team> teams = teamRepository.findAll();
        return teams.stream().map(this::convertToDTO).collect(Collectors.toList());
    }
    public TeamDTO updateTeam(Long teamId, TeamDTO teamDetails) {
        Optional<Team> teamOptional = teamRepository.findById(teamId);
        if(teamOptional.isPresent()) {
            Team team = teamOptional.get();

            if(teamDetails.getTeamName() != null) {
                team.setTeamName(teamDetails.getTeamName());
            }
            if(teamDetails.getLeague() != null) {
                team.setLeague(teamDetails.getLeague());
            }
            if(teamDetails.getHome_stadium() != null) {
                team.setHome_stadium(teamDetails.getHome_stadium());
            }

            Team updatedTeam = teamRepository.save(team);
            return convertToDTO(updatedTeam);
        } else {
            throw new RuntimeException("존재하지 않는 축구 팀입니다.");
        }
    }

    public void deleteTeam(Long teamId) {
        Optional<Team> teamOptional = teamRepository.findById(teamId);
        if(teamOptional.isPresent()) {
            Team team = teamOptional.get();
            teamRepository.delete(team);
        } else {
            throw new RuntimeException("팀을 찾을 수 없습니다.");
        }
    }

    private TeamDTO convertToDTO(Team team) {
        TeamDTO teamDTO = new TeamDTO();
        teamDTO.setTeam_id(team.getTeam_id());
        teamDTO.setTeamName(team.getTeamName());
        teamDTO.setLeague(team.getLeague());
        teamDTO.setHome_stadium(team.getHome_stadium());
        teamDTO.setFavTeamScore(team.getFavTeamScore());
        return teamDTO;
    }

}

