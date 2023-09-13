package com.example.football_community.domain.newsfeed.dto;

import com.example.football_community.domain.post.dto.response.PostDetailsResponseDto;
import lombok.*;

import java.util.List;

@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PageDto {
    private List<PostDetailsResponseDto> posts;
    private boolean hasNext;
}
