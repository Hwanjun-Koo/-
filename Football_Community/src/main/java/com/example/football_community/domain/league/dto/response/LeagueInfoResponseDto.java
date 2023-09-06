package com.example.football_community.domain.league.dto.response;

import com.example.football_community.domain.league.entity.League;
import com.example.football_community.domain.team.entity.Team;
import lombok.*;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class LeagueInfoResponseDto {
    private String leagueName;
    private String country;
    private String foundDate;
    private List<String> teams;

    public static LeagueInfoResponseDto of(League league) {
        List<String> teams = league.getTeams().stream()
                .map(Team::getTeamName)
                .collect(Collectors.toList());
        return LeagueInfoResponseDto.builder()
                .leagueName(league.getLeagueName())
                .country(league.getCountry())
                .foundDate(league.getFoundDate())
                .teams(teams)
                .build();
    }
}
