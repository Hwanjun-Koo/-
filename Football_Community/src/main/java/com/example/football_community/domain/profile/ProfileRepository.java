package com.example.football_community.domain.profile;

import com.example.football_community.domain.member.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfileRepository extends JpaRepository<Profile, Long> {
    Profile findByUser(Member member);
}
