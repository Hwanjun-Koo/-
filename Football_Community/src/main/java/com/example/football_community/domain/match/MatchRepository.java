package com.example.football_community.domain.match;

import com.example.football_community.domain.team.Team;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MatchRepository extends JpaRepository<Match, Long> {
    List<Match> findByTeamsContaining(Team team);
}
