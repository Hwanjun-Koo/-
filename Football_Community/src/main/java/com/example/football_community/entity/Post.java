package com.example.football_community.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;

@Entity
public class Post {

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;
}
