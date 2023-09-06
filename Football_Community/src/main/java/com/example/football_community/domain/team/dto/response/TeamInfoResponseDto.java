package com.example.football_community.domain.team.dto.response;

import com.example.football_community.domain.team.entity.Team;
import lombok.*;

@Getter
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class TeamInfoResponseDto {
    private Long teamId;
    private String teamName;
    private String leagueName;
    private String ground;
    private String headCoach;
    private String foundDate;

    public static TeamInfoResponseDto of(Team team) {
        String leagueName = (team.getLeague() != null) ? team.getLeague().getLeagueName() : null;
        return TeamInfoResponseDto.builder()
                .teamId(team.getTeamId())
                .teamName(team.getTeamName())
                .leagueName(leagueName)  // null if league is not assigned
                .ground(team.getGround())
                .headCoach(team.getHeadCoach())
                .foundDate(team.getFoundDate())
                .build();
    }
}
