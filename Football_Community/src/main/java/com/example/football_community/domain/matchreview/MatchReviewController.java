package com.example.football_community.domain.matchreview;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/football-community/review")
@RestController
public class MatchReviewController {
    private final MatchReviewService matchReviewService;

    @Autowired
    public MatchReviewController(MatchReviewService matchReviewService) {
        this.matchReviewService = matchReviewService;
    }

    @PostMapping("{userId}/create/{matchId}")
    public ResponseEntity<MatchReview> create(
            @PathVariable Long userId,
            @PathVariable Long matchId,
            @RequestBody MatchReview review
    ) {
        MatchReview createdReview = matchReviewService.createReview(userId, matchId, review);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdReview);
    }

    @GetMapping("/{reviewId}")
    public ResponseEntity<MatchReviewDTO> getReview(
            @PathVariable Long reviewId
    ) {
        MatchReviewDTO reviewDTO = matchReviewService.getReview(reviewId);
        return ResponseEntity.ok(reviewDTO);
    }

    @GetMapping("/list/{matchId}")
    public ResponseEntity<List<MatchReviewDTO>> getAllReviews(
            @PathVariable Long matchId
    ) {
        List<MatchReviewDTO> reviewDTOList = matchReviewService.getAllReview(matchId);
        return ResponseEntity.ok(reviewDTOList);
    }

    @PutMapping("/edit/{reviewId}")
    public ResponseEntity<MatchReviewDTO> updateReview(
            @PathVariable Long reviewId,
            @RequestBody MatchReviewDTO reviewDTO
    ) {
        MatchReviewDTO updatedReview = matchReviewService.updateReview(reviewId, reviewDTO);
        return ResponseEntity.ok(updatedReview);
    }

    @DeleteMapping("/delete/{reviewId}")
    public ResponseEntity<String> deleteReview(
            @PathVariable Long reviewId
    ) {
        matchReviewService.deleteReview(reviewId);
        return ResponseEntity.ok("리뷰가 삭제되었습니다.");
    }
}


