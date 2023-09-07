package com.example.football_community.domain.match.entity;

import com.example.football_community.domain.match.dto.request.MatchUpdateRequestDto;
import com.example.football_community.domain.team.entity.Team;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Match {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long matchId;
    @ManyToOne
    @JoinColumn(name = "home_team_id")
    private Team homeTeam;
    @ManyToOne
    @JoinColumn(name = "away_team_id")
    private Team awayTeam;
    @Column(name = "GROUND")
    private String ground;
    @Column(name = "MATCH_DATE")
    private LocalDateTime matchDate;
    @Builder.Default
    private Integer homeScore = 0;
    @Builder.Default
    private Integer awayScore = 0;


    public void updateMatchInfo(MatchUpdateRequestDto requestDto) {
        if (requestDto.getGround() != null) {
            this.ground = requestDto.getGround();
        }
        if (requestDto.getMatchDate() != null) {
            this.matchDate = requestDto.getMatchDate();
        }
        if (requestDto.getHomeScore() != null) {
            this.homeScore = requestDto.getHomeScore();
        }
        if (requestDto.getAwayScore() != null) {
            this.awayScore = requestDto.getAwayScore();
        }
    }

}
