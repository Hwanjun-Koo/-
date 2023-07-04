package com.example.football_community.repository;

import com.example.football_community.entity.Match;
import com.example.football_community.entity.MatchReview;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MatchReviewRepository extends JpaRepository<MatchReview, Long> {
    List<MatchReview> findByMatch(Match match);
}
