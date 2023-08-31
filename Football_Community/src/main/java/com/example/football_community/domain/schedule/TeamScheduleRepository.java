package com.example.football_community.domain.schedule;

import com.example.football_community.domain.team.Team;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeamScheduleRepository extends JpaRepository<TeamSchedule, Long> {


    TeamSchedule findByTeam(Team team);
}
