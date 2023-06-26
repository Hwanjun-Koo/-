package com.example.football_community.entity;

import jakarta.persistence.*;

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

    @Column()

    @OneToMany
    @JoinColumn(name = "user_id")
    private List<User> user;
}
