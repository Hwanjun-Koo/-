package com.example.football_community.domain.matchreview;

import com.example.football_community.domain.match.Match;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MatchReviewRepository extends JpaRepository<MatchReview, Long> {
    List<MatchReview> findByMatch(Match match);
}
