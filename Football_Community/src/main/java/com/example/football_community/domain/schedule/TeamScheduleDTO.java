package com.example.football_community.domain.schedule;

import com.example.football_community.domain.match.MatchDTO;

import java.util.List;

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
