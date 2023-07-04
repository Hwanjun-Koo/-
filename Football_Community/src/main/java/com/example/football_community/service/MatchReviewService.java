package com.example.football_community.service;

import com.example.football_community.dto.MatchReviewDTO;
import com.example.football_community.entity.Match;
import com.example.football_community.entity.MatchReview;
import com.example.football_community.entity.User;
import com.example.football_community.repository.MatchRepository;
import com.example.football_community.repository.MatchReviewRepository;
import com.example.football_community.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class MatchReviewService {
    private MatchReviewRepository matchReviewRepository;
    private MatchRepository matchRepository;
    private UserRepository userRepository;

    public MatchReviewService(MatchReviewRepository matchReviewRepository, MatchRepository matchRepository, UserRepository userRepository) {
        this.matchReviewRepository = matchReviewRepository;
        this.matchRepository = matchRepository;
        this.userRepository = userRepository;
    }

    public MatchReview createReview(Long userId, Long matchId, MatchReview review) {
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("사용자를 찾을 수 없습니다."));
        review.setAuthor(user);
        Match match = matchRepository.findById(matchId).orElseThrow(() -> new RuntimeException("경기를 찾을 수 없습니다."));
        review.setMatch(match);
        MatchReview savedReview = matchReviewRepository.save(review);
        return savedReview;
    }

    public MatchReviewDTO getReview(Long reviewId) {
        Optional<MatchReview> reviewOptional = matchReviewRepository.findById(reviewId);
        if (reviewOptional.isPresent()) {
            MatchReview review = reviewOptional.get();
            return convertToDTO(review);
        } else {
            throw new RuntimeException("존재하지 않는 리뷰입니다.");
        }
    }

    public List<MatchReviewDTO> getAllReview(Long matchId) {
        Match match = matchRepository.findById(matchId).orElseThrow(() -> new RuntimeException("경기를 찾을 수 없습니다."));
        List<MatchReview> reviews = matchReviewRepository.findByMatch(match);
        return reviews.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    public MatchReviewDTO updateReview(Long reviewId, MatchReviewDTO reviewDetails) {
        Optional<MatchReview> reviewOptional = matchReviewRepository.findById(reviewId);
        if (reviewOptional.isPresent()) {
            MatchReview review = reviewOptional.get();

            if (reviewDetails.getTitle() != null) {
                review.setTitle(reviewDetails.getTitle());
            }
            if (reviewDetails.getContent() != null) {
                review.setContent(reviewDetails.getContent());
            }
            MatchReview updatedReview = matchReviewRepository.save(review);
            return convertToDTO(updatedReview);
        } else {
            throw new RuntimeException("리뷰를 찾을 수 없습니다.");
        }
    }

    public void deleteReview(Long reviewId) {
        Optional<MatchReview> reviewOptional = matchReviewRepository.findById(reviewId);
        if (reviewOptional.isPresent()) {
            MatchReview review = reviewOptional.get();
            matchReviewRepository.delete(review);
        } else {
            throw new RuntimeException("리뷰를 찾을 수 없습니다.");
        }

    }

    private MatchReviewDTO convertToDTO(MatchReview review) {
        MatchReviewDTO reviewDTO = new MatchReviewDTO();
        reviewDTO.setId(review.getId());
        reviewDTO.setTitle(review.getTitle());
        reviewDTO.setContent(review.getContent());
        User author = review.getAuthor();
        if (author != null) {
            reviewDTO.setAuthor(author.getUsername());
        }

        return reviewDTO;
    }
}
