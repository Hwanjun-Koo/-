package com.example.football_community.domain.comment.repository;

import com.example.football_community.domain.comment.entity.Comment;
import com.example.football_community.domain.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findByMember(Member member);
}
