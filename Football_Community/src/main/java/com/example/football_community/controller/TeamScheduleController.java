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


    @GetMapping("/all/{teamId}")
    public ResponseEntity<TeamScheduleDTO> getSchedules(
            @PathVariable Long teamId
    ) {
        TeamScheduleDTO scheduleDTO = teamScheduleService.getSchedule(teamId);
        return ResponseEntity.ok(scheduleDTO);
    }


}
