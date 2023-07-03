package com.example.football_community.repository;

import com.example.football_community.entity.Match;
import com.example.football_community.entity.Team;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MatchRepository extends JpaRepository<Match, Long> {
    List<Match> findByTeamsContaining(Team team);
}
