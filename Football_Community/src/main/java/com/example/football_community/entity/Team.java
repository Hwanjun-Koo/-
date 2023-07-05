package com.example.football_community.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Team {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long team_id;
    @Column(nullable = false, unique = true)
    private String teamName;
    @Column(nullable = false, unique = true)
    private String home_stadium;
    @Column(nullable = false)
    private String league;
    @Column
    private Integer favTeamScore = 0;


    @OneToMany(cascade = CascadeType.ALL, mappedBy = "favTeam")
    private List<Profile> profiles;

    @JsonIgnore
    @ManyToMany(mappedBy = "teams")
    private List<Match> matches;

    @OneToOne(mappedBy = "team")
    private TeamSchedule teamSchedule;

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

    public List<Profile> getProfile() {
        return profiles;
    }

    public void addProfile(Profile profile) {
        if (profile != null) {
            if (profiles == null) {
                profiles = new ArrayList<>();
            }
            profiles.add(profile);
            if (profile.getFavTeam() != this) {
                profile.setFavTeam(this);
            }
        }
    }

    public Integer getFavTeamScore() {
        return favTeamScore;
    }

    public void setFavTeamScore(Integer favTeamScore) {
        this.favTeamScore = favTeamScore;
    }

    public void incrementFavTeamScore() {
        favTeamScore++;
    }

    public void decrementFavTeamScore() {
        if (favTeamScore > 0) {
            favTeamScore--;
        }
    }

    public TeamSchedule getTeamSchedule() {
        return teamSchedule;
    }

    public void setTeamSchedule(TeamSchedule teamSchedule) {
        this.teamSchedule = teamSchedule;
    }

}
