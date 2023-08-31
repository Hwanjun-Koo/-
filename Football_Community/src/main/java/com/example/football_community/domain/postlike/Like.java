package com.example.football_community.domain.postlike;

import com.example.football_community.domain.member.Member;
import com.example.football_community.domain.post.Post;
import jakarta.persistence.*;

@Entity
@Table(name = "post_like")
public class Like {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long likeId;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private Member member;

    @ManyToOne
    @JoinColumn(name = "post_id")
    private Post post;

    public Like(Member member, Post post) {
        this.member = member;
        this.post = post;
    }

    public Like() {
    }

    public Long getLikeId() {
        return likeId;
    }

    public void setLikeId(Long likeId) {
        this.likeId = likeId;
    }

    public Member getUser() {
        return member;
    }

    public void setUser(Member member) {
        this.member = member;
    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }
}
