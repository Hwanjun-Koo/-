package com.example.football_community.domain.comment.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CommentRegisterRequestDto {
    private Long postId;
    private String content;
}
