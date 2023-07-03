package com.example.football_community.dto;

import com.example.football_community.entity.Match;
import com.example.football_community.entity.Team;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public class TeamScheduleDTO {
    private LocalDateTime matchTime;
    private String opposingTeam;
    private String venue;

    public LocalDateTime getMatchTime() {
        return matchTime;
    }

    public void setMatchTime(LocalDateTime matchTime) {
        this.matchTime = matchTime;
    }

    public String getOpposingTeam() {
        return opposingTeam;
    }

    public void setOpposingTeam(String opposingTeam) {
        this.opposingTeam = opposingTeam;
    }

    public String getVenue() {
        return venue;
    }

    public void setVenue(String venue) {
        this.venue = venue;
    }


}
