package com.example.football_community.domain.review.entity;

import com.example.football_community.domain.match.entity.Match;
import com.example.football_community.domain.member.entity.Member;
import com.example.football_community.domain.review.dto.request.ReviewUpdateRequestDto;
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
public class Review extends TimeStamped {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long reviewId;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MATCH_ID")
    private Match match;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MEMBER_ID")
    private Member member;
    @Column(name = "TITLE")
    private String title;
    @Column(name = "CONTENT")
    private String content;
    @Column(name = "RATING")
    private Integer rating;

    public void updateReview(ReviewUpdateRequestDto requestDto) {
        if (requestDto.getTitle() != null) {
            this.title = requestDto.getTitle();
        }
        if (requestDto.getContent() != null) {
            this.content = requestDto.getContent();
        }
        if (requestDto.getRating() != null) {
            this.rating = requestDto.getRating();
        }
    }
}
