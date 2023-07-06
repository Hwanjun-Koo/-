package com.example.football_community.repository;

import com.example.football_community.entity.Like;
import com.example.football_community.entity.Post;
import com.example.football_community.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LikeRepository extends JpaRepository<Like, Long> {
    Like findByUserIdAndPostId(Long userId, Long postId);

    Like findByUserAndPost(User user, Post post);
}
