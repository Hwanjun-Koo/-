package com.example.football_community.domain.postlike.repository;

import com.example.football_community.domain.member.entity.Member;
import com.example.football_community.domain.post.entity.Post;
import com.example.football_community.domain.postlike.entity.PostLike;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PostLikeRepository extends JpaRepository<PostLike, Long> {
    Optional<PostLike> findByMemberAndPost(Member member, Post post);
}
