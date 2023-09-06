package com.example.football_community.domain.post.repository;

import com.example.football_community.domain.member.entity.Member;
import com.example.football_community.domain.post.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
    List<Post> findByMember(Member member);
}
