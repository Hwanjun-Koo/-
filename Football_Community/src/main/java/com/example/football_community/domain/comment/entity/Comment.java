package com.example.football_community.domain.comment.entity;

import com.example.football_community.domain.comment.dto.request.CommentUpdateRequestDto;
import com.example.football_community.domain.member.entity.Member;
import com.example.football_community.domain.post.entity.Post;
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
public class Comment extends TimeStamped {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long commentId;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MEMBER_ID")
    private Member member;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "POST_ID")
    private Post post;
    @Column(name = "CONTENT")
    private String content;
    @Column(name = "REPORT_COUNT")
    private int reportCount = 0;

    public void updateComment(CommentUpdateRequestDto requestDto) {
        if (requestDto.getContent() != null) {
            this.content = requestDto.getContent();
        }
    }

    public void addReport() {
        this.reportCount += 1;
    }

}
