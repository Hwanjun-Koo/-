package com.example.football_community.domain.schedule;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
