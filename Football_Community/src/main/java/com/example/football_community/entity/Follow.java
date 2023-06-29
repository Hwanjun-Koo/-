package com.example.football_community.entity;

import jakarta.persistence.*;

@Entity
public class Follow {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long follow_id;

    @ManyToOne
    @JoinColumn(name = "follower_id")
    private User follower;

    @ManyToOne
    @JoinColumn(name = "following_id")
    private User following;


    public Follow(User follower, User following) {
        this.follower = follower;
        this.following = following;
    }

    public Follow() {
    }

    public User getFollower() {
        return follower;
    }

    public User getFollowing() {
        return following;
    }
}
