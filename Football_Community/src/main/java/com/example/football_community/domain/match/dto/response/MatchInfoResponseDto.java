package com.example.football_community.domain.match.dto.response;

import com.example.football_community.domain.match.entity.Match;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class MatchInfoResponseDto {
    private Long matchId;
    private String homeTeam;
    private String awayTeam;
    private String ground;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime matchDate;
    private String currentScore;

    public static MatchInfoResponseDto of(Match match) {
        String homeTeam = (match.getHomeTeam() != null) ? match.getHomeTeam().getTeamName() : null;
        String awayTeam = (match.getAwayTeam() != null) ? match.getAwayTeam().getTeamName() : null;

        String formattedScore = formatMatchScore(match);
        return MatchInfoResponseDto.builder()
                .matchId(match.getMatchId())
                .homeTeam(homeTeam)
                .awayTeam(awayTeam)
                .ground(match.getGround())
                .matchDate(match.getMatchDate())
                .currentScore(formattedScore)
                .build();
    }

    private static String formatMatchScore(Match match) {
        Integer homeScore = match.getHomeScore();
        Integer awayScore = match.getAwayScore();

        String homeScoreStr = (homeScore != null) ? homeScore.toString() : "N/A";
        String awayScoreStr = (awayScore != null) ? awayScore.toString() : "N/A";

        return "Home " + homeScoreStr + " : " + awayScoreStr + " Away";
    }
}
