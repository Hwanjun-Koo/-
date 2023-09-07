package com.example.football_community.domain.review.controller;

import com.example.football_community.domain.member.security.UserDetailsImpl;
import com.example.football_community.domain.review.dto.request.ReviewUpdateRequestDto;
import com.example.football_community.domain.review.dto.request.ReviewUploadRequestDto;
import com.example.football_community.domain.review.service.ReviewService;
import com.example.football_community.global.message.ResponseMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/review")
@RequiredArgsConstructor
public class ReviewController {
    private final ReviewService reviewService;

    @PostMapping("/upload")
    public ResponseEntity uploadReview(
            @RequestBody ReviewUploadRequestDto requestDto,
            @AuthenticationPrincipal UserDetailsImpl userDetails
    ) {
        reviewService.uploadReview(requestDto, userDetails);
        return ResponseMessage.SuccessResponse("리뷰 작성 완료", "");
    }

    @GetMapping()
    public ResponseEntity getMyReviews(
            @AuthenticationPrincipal UserDetailsImpl userDetails
    ) {
        return ResponseMessage.SuccessResponse("내가 쓴 리뷰 불러오기 완료", reviewService.myReviews(userDetails));
    }

    @PutMapping("/{reviewId}")
    public ResponseEntity updateReview(
            @PathVariable Long reviewId,
            @RequestBody ReviewUpdateRequestDto requestDto,
            @AuthenticationPrincipal UserDetailsImpl userDetails
    ) {
        reviewService.updateReview(reviewId, requestDto, userDetails);
        return ResponseMessage.SuccessResponse("리뷰 수정 완료", "");
    }

    @DeleteMapping("/{reviewId}")
    public ResponseEntity deleteReview(
            @PathVariable Long reviewId,
            @AuthenticationPrincipal UserDetailsImpl userDetails
    ) {
        reviewService.deleteReview(reviewId, userDetails);
        return ResponseMessage.SuccessResponse("리뷰 삭제 완료", "");
    }
}
