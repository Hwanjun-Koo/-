package com.example.football_community.domain.team.entity;

import com.example.football_community.domain.league.entity.League;
import com.example.football_community.domain.match.entity.Match;
import com.example.football_community.domain.team.dto.request.TeamUpdateRequestDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;


@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Team {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long teamId;
    @Column(name = "TEAM_NAME")
    private String teamName;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "LEAGUE_ID")
    private League league;
    @Column(name = "GROUND")
    private String ground; //홈구장
    @Column(name = "HEAD_COACH")
    private String headCoach;
    @Column(name = "FOUND_DATE")
    private String foundDate; //창단일

    public void updateTeamInfo(TeamUpdateRequestDto requestDto) {
        if (requestDto.getTeamName() != null) {
            this.teamName = requestDto.getTeamName();
        }
        if (requestDto.getGround() != null) {
            this.ground = requestDto.getGround();
        }
        if (requestDto.getHeadCoach() != null) {
            this.headCoach = requestDto.getHeadCoach();
        }
    }

    public void setLeague(League league) {
        this.league = league;
    }
}
