package com.example.football_community.controller;

import com.example.football_community.dto.TeamDTO;
import com.example.football_community.entity.Team;
import com.example.football_community.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/football-community/team")
@RestController
public class TeamController {

    private final TeamService teamService;

    @Autowired
    public TeamController(TeamService teamService) {
        this.teamService = teamService;
    }

    @PostMapping("/create")
    public ResponseEntity<TeamDTO> createTeam(@RequestBody Team team) {
        TeamDTO createdTeam = teamService.createTeam(team);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdTeam);
    }

    @GetMapping("/{teamId}")
    public ResponseEntity<TeamDTO> getTeam(
            @PathVariable Long teamId) {
        TeamDTO teamDTO = teamService.getTeam(teamId);
        return  ResponseEntity.ok(teamDTO);
    }

    @GetMapping("/all")
    public  ResponseEntity<List<TeamDTO>> getAllTeams(){
        List<TeamDTO> teamDTOList = teamService.getAllTeam();
        return ResponseEntity.ok(teamDTOList);
    }

    @PutMapping("/modification/{teamId}")
    public ResponseEntity<TeamDTO> updateTeam(
            @PathVariable Long teamId,
            @RequestBody TeamDTO teamDTO
    ) {
        TeamDTO updatedTeam = teamService.updateTeam(teamId, teamDTO);
        return ResponseEntity.ok(updatedTeam);
    }

    @DeleteMapping("/delete/{teamId}")
    public ResponseEntity<String> deleteTeam(
            @PathVariable Long teamId
    ) {
        teamService.deleteTeam(teamId);
        return ResponseEntity.ok("팀이 삭제되었습니다.");
    }

}
