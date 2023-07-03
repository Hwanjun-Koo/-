package com.example.football_community.controller;

import com.example.football_community.dto.TeamScheduleDTO;
import com.example.football_community.entity.TeamSchedule;
import com.example.football_community.service.TeamScheduleService;
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
    public ResponseEntity<TeamSchedule> create(
            @PathVariable String teamName
    ) {
        TeamSchedule teamSchedule = teamScheduleService.createSchedule(teamName);
        return ResponseEntity.status(HttpStatus.CREATED).body(teamSchedule);
    }

    @GetMapping("/all/{teamName}")
    public ResponseEntity<List<TeamScheduleDTO>> getSchedules(
            @PathVariable String teamName
    ) {
        List<TeamScheduleDTO> scheduleDTOList = teamScheduleService.getSchedules(teamName);
        return ResponseEntity.ok(scheduleDTOList);
    }

    @PutMapping("/edit/{scheduleId}")
    public ResponseEntity<String> updateSchedule(
            @PathVariable Long scheduleId,
            @RequestBody TeamScheduleDTO scheduleDTO
    ) {
        teamScheduleService.updateSchedule(scheduleId, scheduleDTO);
        return ResponseEntity.ok("일정이 수정되었습니다.");
    }

}
