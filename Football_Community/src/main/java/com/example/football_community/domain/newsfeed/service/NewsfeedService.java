package com.example.football_community.domain.newsfeed.service;

import com.example.football_community.domain.newsfeed.dto.PageDto;
import com.example.football_community.domain.post.dto.response.PostDetailsResponseDto;
import com.example.football_community.domain.post.entity.Post;
import com.example.football_community.domain.post.repository.PostRepository;
import com.example.football_community.domain.review.dto.response.ReviewDetailsResponseDto;
import com.example.football_community.domain.review.entity.Review;
import com.example.football_community.domain.review.repository.ReviewRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class NewsfeedService {
    private final PostRepository postRepository;
    private final ReviewRepository reviewRepository;

    @Transactional
    public PageDto getPosts(int page, int size){
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "createdAt"));
        Page<Post> postPage = postRepository.findAll(pageable);
        Page<Review> reviewPage = reviewRepository.findAll(pageable);
        boolean hasNext = postPage.hasNext();
        List<PostDetailsResponseDto> postDtos = postPage.getContent().stream()
                .map(PostDetailsResponseDto::of)
                .collect(Collectors.toList());
        List<ReviewDetailsResponseDto> reviewDtos = reviewPage.getContent().stream()
                .map(ReviewDetailsResponseDto::of)
                .collect(Collectors.toList());
        return PageDto.builder()
                .posts(postDtos)
                .reviews(reviewDtos)
                .hasNext(hasNext)
                .build();
    }
}
