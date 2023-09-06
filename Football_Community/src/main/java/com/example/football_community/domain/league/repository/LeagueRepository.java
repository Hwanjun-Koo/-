package com.example.football_community.domain.league.repository;

import com.example.football_community.domain.league.entity.League;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LeagueRepository extends JpaRepository<League, Long> {
}
