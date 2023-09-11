package com.example.football_community.domain.schedule.controller;

import com.example.football_community.domain.schedule.service.ScheduleService;
import com.example.football_community.global.message.ResponseMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ScheduleController {
    private final ScheduleService scheduleService;

    @GetMapping("/team/{teamId}/schedules")
    public ResponseEntity getSchedules(
            @PathVariable Long teamId
    ) {
        return ResponseMessage.SuccessResponse("팀 일정 불러오기 성공", scheduleService.getSchedules(teamId));
    }
}
