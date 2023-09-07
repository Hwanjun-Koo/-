package com.example.football_community.domain.review.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ReviewUploadRequestDto {
    private Long matchId;
    private String title;
    private String content;
    private Integer rating;
}
