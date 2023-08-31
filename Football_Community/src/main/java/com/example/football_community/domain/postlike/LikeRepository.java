package com.example.football_community.domain.postlike;

import com.example.football_community.domain.post.Post;
import com.example.football_community.domain.member.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LikeRepository extends JpaRepository<Like, Long> {
    Like findByUserIdAndPostId(Long userId, Long postId);

    Like findByUserAndPost(Member member, Post post);
}
