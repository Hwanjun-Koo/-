package com.example.football_community.domain.newsfeed;

import com.example.football_community.domain.member.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NewsfeedRepository extends JpaRepository<Newsfeed, Long> {

    Newsfeed findByUser(Member member);
}
