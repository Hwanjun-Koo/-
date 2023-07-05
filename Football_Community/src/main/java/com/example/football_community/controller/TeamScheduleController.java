package com.example.football_community.controller;

import com.example.football_community.dto.TeamScheduleDTO;
import com.example.football_community.entity.TeamSchedule;
import com.example.football_community.service.TeamScheduleService;
import com.example.football_community.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("football-community/schedule")
@RestController
public class TeamScheduleController {
    private final TeamScheduleService teamScheduleService;

    @Autowired
    public TeamScheduleController(TeamScheduleService teamScheduleService) {
        this.teamScheduleService = teamScheduleService;
    }

    @PostMapping("/create/{teamName}")
    public ResponseEntity<TeamScheduleDTO> create(
            @PathVariable String teamName
    ) {
        TeamScheduleDTO teamScheduleDTO = teamScheduleService.createSchedule(teamName);
        return ResponseEntity.status(HttpStatus.CREATED).body(teamScheduleDTO);
    }

    @GetMapping("/all/{teamName}")
    public ResponseEntity<TeamScheduleDTO> getSchedules(
            @PathVariable String teamName
    ) {
        TeamScheduleDTO scheduleDTO = teamScheduleService.getSchedule(teamName);
        return ResponseEntity.ok(scheduleDTO);
    }


}
