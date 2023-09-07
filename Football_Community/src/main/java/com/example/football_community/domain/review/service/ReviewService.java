package com.example.football_community.domain.review.service;

import com.example.football_community.domain.match.entity.Match;
import com.example.football_community.domain.match.repository.MatchRepository;
import com.example.football_community.domain.member.entity.Member;
import com.example.football_community.domain.member.security.MemberIsLoginService;
import com.example.football_community.domain.member.security.UserDetailsImpl;
import com.example.football_community.domain.review.dto.request.ReviewUpdateRequestDto;
import com.example.football_community.domain.review.dto.request.ReviewUploadRequestDto;
import com.example.football_community.domain.review.dto.response.ReviewDetailsResponseDto;
import com.example.football_community.domain.review.entity.Review;
import com.example.football_community.domain.review.repository.ReviewRepository;
import com.example.football_community.global.exception.GlobalErrorCode;
import com.example.football_community.global.exception.GlobalException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ReviewService {
    private final ReviewRepository reviewRepository;
    private final MatchRepository matchRepository;
    private final MemberIsLoginService memberIsLoginService;
    @Transactional
    public void uploadReview(ReviewUploadRequestDto requestDto, UserDetailsImpl userDetails) {
        Member member = memberIsLoginService.isLogin(userDetails);
        Match match = matchRepository.findById(requestDto.getMatchId())
                .orElseThrow(() -> new GlobalException(GlobalErrorCode.MATCH_NOT_FOUND));
        Review review = Review.builder()
                .member(member)
                .match(match)
                .title(requestDto.getTitle())
                .content(requestDto.getContent())
                .rating(requestDto.getRating())
                .build();
        reviewRepository.save(review);
    }

    @Transactional
    public List<ReviewDetailsResponseDto> myReviews(UserDetailsImpl userDetails) {
        Member member = memberIsLoginService.isLogin(userDetails);
        List<Review> reviews = reviewRepository.findByMember(member);
        List<ReviewDetailsResponseDto> dtos = reviews.stream()
                .map(ReviewDetailsResponseDto::of)
                .collect(Collectors.toList());
        return dtos;
    }

    @Transactional
    public void updateReview(Long reviewId, ReviewUpdateRequestDto requestDto, UserDetailsImpl userDetails) {
        Review review = reviewRepository.findById(reviewId)
                .orElseThrow(() -> new GlobalException(GlobalErrorCode.REVIEW_NOT_FOUND));
        // 접근한 회원의 MemberId 확인
        Long currentMemberId = userDetails.getMember().getMemberId();

        // 수정하려는 리뷰 작성자의 MemberId 확인
        Long reviewAuthorId = review.getMember().getMemberId();

        if (currentMemberId.equals(reviewAuthorId)) {
            review.updateReview(requestDto);
        } else {
            throw new GlobalException(GlobalErrorCode.ACCESS_DENIED);
        }
    }

    @Transactional
    public void deleteReview(Long reviewId, UserDetailsImpl userDetails) {
        Review review = reviewRepository.findById(reviewId)
                .orElseThrow(() -> new GlobalException(GlobalErrorCode.REVIEW_NOT_FOUND));
        // 접근한 회원의 MemberId 확인
        Long currentMemberId = userDetails.getMember().getMemberId();

        // 수정하려는 리뷰 작성자의 MemberId 확인
        Long reviewAuthorId = review.getMember().getMemberId();

        if (currentMemberId.equals(reviewAuthorId)) {
            reviewRepository.delete(review);
        } else {
            throw new GlobalException(GlobalErrorCode.ACCESS_DENIED);
        }
    }
}
