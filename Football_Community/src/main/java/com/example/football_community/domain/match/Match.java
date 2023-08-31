package com.example.football_community.domain.match;

import com.example.football_community.domain.matchreview.MatchReview;
import com.example.football_community.domain.team.Team;
import com.example.football_community.domain.schedule.TeamSchedule;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
public class Match {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private LocalDateTime matchTime;

    @Column
    private String venue;

    @ManyToMany
    @JoinTable(
            name = "match_team",
            joinColumns = @JoinColumn(name = "match_id"),
            inverseJoinColumns = @JoinColumn(name = "team_id")
    )
    private Set<Team> teams = new HashSet<>();

    @ElementCollection
    @CollectionTable(name = "match_team_names", joinColumns = @JoinColumn(name = "match_id"))
    @Column(name = "team_name")
    private List<String> teamNames;

    @JsonIgnore
    @ManyToOne
    private TeamSchedule teamSchedule;

    @JsonIgnore
    @OneToMany
    private List<MatchReview> reviews;


    public Match() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getMatchTime() {
        return matchTime;
    }

    public void setMatchTime(LocalDateTime matchTime) {
        this.matchTime = matchTime;
    }

    public List<String> getTeamNames() {
        return teamNames;
    }

    public void setTeamNames(List<String> teamNames) {
        this.teamNames = teamNames;
    }

    public Set<Team> getTeams() {
        return teams;
    }

    public void setTeams(Set<Team> teams) {
        this.teams = teams;
    }

    public String getVenue() {
        return venue;
    }

    public void setVenue(String venue) {
        this.venue = venue;
    }

    public List<MatchReview> getReviews() {
        return reviews;
    }

    public void setReviews(List<MatchReview> reviews) {
        this.reviews = reviews;
    }

    public TeamSchedule getTeamSchedule() {
        return teamSchedule;
    }

    public void setTeamSchedule(TeamSchedule teamSchedule) {
        this.teamSchedule = teamSchedule;
    }
}
