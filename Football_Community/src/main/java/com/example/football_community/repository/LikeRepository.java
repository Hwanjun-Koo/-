package com.example.football_community.repository;

import com.example.football_community.entity.Like;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LikeRepository extends JpaRepository<Like, Long> {
    Like findByUserIdAndPostId(Long userId, Long postId);
}
