package com.example.football_community.domain.review.dto.response;

import com.example.football_community.domain.match.dto.response.MatchInfoResponseDto;
import com.example.football_community.domain.review.entity.Review;
import lombok.*;

@Getter
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class ReviewDetailsResponseDto {
    private Long reviewId;
    private MatchInfoResponseDto matchFacts;
    private String title;
    private String content;
    private Integer rating;

    public static ReviewDetailsResponseDto of(Review review) {
        return ReviewDetailsResponseDto.builder()
                .reviewId(review.getReviewId())
                .matchFacts(MatchInfoResponseDto.of(review.getMatch()))
                .title(review.getTitle())
                .content(review.getContent())
                .rating(review.getRating())
                .build();
    }
}
