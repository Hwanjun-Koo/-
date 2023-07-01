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


    @OneToMany(cascade = CascadeType.ALL, mappedBy = "favTeam")
    private List<Profile> profiles;

    @ManyToMany(mappedBy = "teams")
    private List<Match> matches;
//
//    @OneToMany(mappedBy = "team")
//    private List<Notification> notifications;
//
//    @OneToMany(mappedBy = "team")
//    private List<TeamSchedule> teamSchedules;

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

    //    public List<Match> getMatches() {
//        return matches;
//    }
//
//    public void setMatches(List<Match> matches) {
//        this.matches = matches;
//    }
//
//    public List<Notification> getNotifications() {
//        return notifications;
//    }
//
//    public void setNotifications(List<Notification> notifications) {
//        this.notifications = notifications;
//    }
//
//    public List<TeamSchedule> getTeamSchedules() {
//        return teamSchedules;
//    }

//    public void setTeamSchedules(List<TeamSchedule> teamSchedules) {
//        this.teamSchedules = teamSchedules;
//    }

}
