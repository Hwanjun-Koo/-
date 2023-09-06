package com.example.football_community.domain.league.entity;

import com.example.football_community.domain.team.entity.Team;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class League {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long leagueId;
    @OneToMany(mappedBy = "league", cascade = CascadeType.ALL)
    private List<Team> teams;
    @Column(name = "LEAGUE_NAME")
    private String leagueName;
    @Column(name = "COUNTRY")
    private String country;
    @Column(name = "FOUND_DATE")
    private String foundDate;
}
