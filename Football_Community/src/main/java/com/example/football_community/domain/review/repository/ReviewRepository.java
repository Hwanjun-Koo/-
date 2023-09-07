package com.example.football_community.domain.review.repository;

import com.example.football_community.domain.member.entity.Member;
import com.example.football_community.domain.review.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {
    List<Review> findByMember(Member member);
}
