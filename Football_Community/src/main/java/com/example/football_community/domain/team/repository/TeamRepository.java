package com.example.football_community.domain.team.repository;

import com.example.football_community.domain.league.entity.League;
import com.example.football_community.domain.team.entity.Team;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;
import java.util.Map;

@Repository
public interface TeamRepository extends JpaRepository<Team, Long> {
    List<Team> findAllByLeague(League league);
}
