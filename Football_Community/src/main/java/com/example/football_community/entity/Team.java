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

    @Column()

    @OneToMany
    @JoinColumn(name = "user_id")
    private List<User> user;
>>>>>>> Team
}
