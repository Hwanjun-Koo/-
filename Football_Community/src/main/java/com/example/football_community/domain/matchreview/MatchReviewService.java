package com.example.football_community.domain.matchreview;

import com.example.football_community.domain.match.Match;
import com.example.football_community.domain.member.Member;
import com.example.football_community.domain.match.MatchRepository;
import com.example.football_community.domain.member.MemberRepository;
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
    private MemberRepository memberRepository;

    public MatchReviewService(MatchReviewRepository matchReviewRepository, MatchRepository matchRepository, MemberRepository memberRepository) {
        this.matchReviewRepository = matchReviewRepository;
        this.matchRepository = matchRepository;
        this.memberRepository = memberRepository;
    }

    public MatchReview createReview(Long userId, Long matchId, MatchReview review) {
        Member member = memberRepository.findById(userId).orElseThrow(() -> new RuntimeException("사용자를 찾을 수 없습니다."));
        review.setAuthor(member);
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
        Member author = review.getAuthor();
        if (author != null) {
            reviewDTO.setAuthor(author.getUsername());
        }

        return reviewDTO;
    }
}
