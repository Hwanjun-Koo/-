package com.example.football_community.domain.follow;

import com.example.football_community.domain.member.entity.Member;
import jakarta.persistence.*;

@Entity
public class Follow {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long follow_id;

    @ManyToOne
    @JoinColumn(name = "follower_id")
    private Member follower;

    @ManyToOne
    @JoinColumn(name = "following_id")
    private Member following;


    public Follow(Member follower, Member following) {
        this.follower = follower;
        this.following = following;
    }

    public Follow() {
    }

    public Member getFollower() {
        return follower;
    }

    public Member getFollowing() {
        return following;
    }
}
