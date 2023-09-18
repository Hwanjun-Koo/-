package com.example.football_community.domain.post.dto.response;

import com.example.football_community.domain.post.entity.Post;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class PostDetailsResponseDto {
    private Long postId;
    private String authorNickname; //작성자 닉네임
    private String title;
    private String content;
    private int likeCount;
    private int commentCount;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createdAt;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime modifiedAt;

    public static PostDetailsResponseDto of(Post post) {
        return PostDetailsResponseDto.builder()
                .postId(post.getPostId())
                .authorNickname(post.getMember().getNickname())
                .title(post.getTitle())
                .content(post.getContent())
                .likeCount(post.getLikeCount())
                .commentCount(post.getCommentCount())
                .createdAt(post.getCreatedAt())
                .modifiedAt(post.getModifiedAt())
                .build();
    }
}
