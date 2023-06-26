package com.example.football_community.entity;

<<<<<<< HEAD
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
=======
import jakarta.persistence.*;
>>>>>>> Team

import java.util.List;

@Entity
public class Team {

<<<<<<< HEAD
=======
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long team_id;

    @Column(nullable = false, unique = true)
    private String teamName;

    @Column(nullable = false, unique = true)
    private String home_stadium;

    @Column(nullable = false)
    private String league;

<<<<<<< HEAD
    @OneToMany
    @JoinColumn(name = "user_id")
    private List<User> user;
>>>>>>> Team
=======
    @OneToMany(mappedBy = "team")
    private List<User> users;

    @ManyToMany(mappedBy = "teams")
    private List<Match> matches

    @OneToMany(mappedBy = "team")
    private List<Notification> notifications;

    @OneToMany(mappedBy = "team")
    private List<TeamSchedule> teamSchedules;

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

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public List<Match> getMatches() {
        return matches;
    }

    public void setMatches(List<Match> matches) {
        this.matches = matches;
    }

    public List<Notification> getNotifications() {
        return notifications;
    }

    public void setNotifications(List<Notification> notifications) {
        this.notifications = notifications;
    }

    public List<TeamSchedule> getTeamSchedules() {
        return teamSchedules;
    }

    public void setTeamSchedules(List<TeamSchedule> teamSchedules) {
        this.teamSchedules = teamSchedules;
    }
>>>>>>> Team
}
