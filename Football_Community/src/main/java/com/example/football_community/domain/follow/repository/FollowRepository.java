package com.example.football_community.domain.follow.repository;

import com.example.football_community.domain.follow.entity.Follow;
import com.example.football_community.domain.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FollowRepository extends JpaRepository<Follow, Long> {
    Optional<Follow> findByMemberAndTargetMember(Member member, Member targetMember);

    List<Follow> findByMember(Member member);

    List<Follow> findByTargetMember(Member member);
}
