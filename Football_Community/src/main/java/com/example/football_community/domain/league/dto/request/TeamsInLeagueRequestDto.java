package com.example.football_community.domain.league.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class TeamsInLeagueRequestDto {
    private Long leagueId;
    private Long teamId;
}
