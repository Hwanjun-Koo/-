package com.example.football_community.domain.post.entity;

import com.example.football_community.domain.member.entity.Member;
import com.example.football_community.global.timestamp.TimeStamped;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.sql.Time;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Post extends TimeStamped {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long postId;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MEMBER_ID")
    private Member member;
    @Column(name = "TITLE")
    private String title;
    @Column(name = "CONTENT")
    private String content;

    public void updatePost(PostUpdateRequestDto request) {
        if (request.getTitle() != null) {
            this.title = request.getTitle();
        }
        if(request.getContent() != null){
            this.content = request.getContent();
        }
    }
}
