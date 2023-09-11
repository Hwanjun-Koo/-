package com.example.football_community.domain.match.repository;

import com.example.football_community.domain.match.entity.Match;
import com.example.football_community.domain.team.entity.Team;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MatchRepository extends JpaRepository<Match, Long>{
        @Query("SELECT m FROM Match m WHERE m.homeTeam = :team OR m.awayTeam = :team")
        List<Match> findMatchesByTeam(Team team);
}
