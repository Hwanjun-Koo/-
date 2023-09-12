package com.example.football_community.domain.post.entity;

import com.example.football_community.domain.member.entity.Member;
import com.example.football_community.domain.post.dto.request.PostUpdateRequestDto;
import com.example.football_community.global.timestamp.TimeStamped;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


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

    @Column(name = "LIKE_COUNT")
    private int likeCount = 0;

    public void updatePost(PostUpdateRequestDto requestDto) {

        if (requestDto.getTitle() != null) {
            this.title = requestDto.getTitle();
        }
        if (requestDto.getContent() != null) {
            this.content = requestDto.getContent();
        }
    }

    public void addLike() {
        this.likeCount += 1;
    }

    public void removeLike() {
        this.likeCount -= 1;
    }
}
