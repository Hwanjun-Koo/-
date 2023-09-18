package com.example.football_community.domain.report.repository;

import com.example.football_community.domain.comment.entity.Comment;
import com.example.football_community.domain.member.entity.Member;
import com.example.football_community.domain.post.entity.Post;
import com.example.football_community.domain.report.entity.Report;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ReportRepository extends JpaRepository<Report, Long> {
    Optional<Report> findByMemberAndTargetPost(Member member, Post post);

    Optional<Report> findByMemberAndTargetComment(Member member, Comment comment);
}
