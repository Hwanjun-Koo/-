package com.example.football_community.dto;

public class TeamDTO {
    private Long team_id;
    private String teamName;
    private String home_stadium;
    private String league;

    private Integer favTeamScore;

    public Long getTeam_id() {
        return team_id;
    }

    public void setTeam_id(Long team_id) {
        this.team_id = team_id;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public String getHome_stadium() {
        return home_stadium;
    }

    public void setHome_stadium(String home_stadium) {
        this.home_stadium = home_stadium;
    }

    public String getLeague() {
        return league;
    }

    public void setLeague(String league) {
        this.league = league;
    }

    public Integer getFavTeamScore() {
        return favTeamScore;
    }

    public void setFavTeamScore(Integer favTeamScore) {
        this.favTeamScore = favTeamScore;
    }
}
