package com.example.football_community.domain.league.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class LeagueRegisterRequestDto {
    private String leagueName;
    private String country;
    private String foundDate;
}
