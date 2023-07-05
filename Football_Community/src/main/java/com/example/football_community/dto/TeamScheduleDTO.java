package com.example.football_community.dto;

import com.example.football_community.entity.Match;
import com.example.football_community.entity.Team;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public class TeamScheduleDTO {
    private Long scheduleId;
    private List<MatchDTO> matchDTOS;

    public Long getScheduleId() {
        return scheduleId;
    }

    public void setScheduleId(Long scheduleId) {
        this.scheduleId = scheduleId;
    }

    public List<MatchDTO> getMatchDTOS() {
        return matchDTOS;
    }

    public void setMatchDTOS(List<MatchDTO> matchDTOS) {
        this.matchDTOS = matchDTOS;
    }
}
