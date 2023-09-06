package com.example.football_community.domain.team.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class TeamRegisterRequestDto {
    private String teamName;
    private String ground;
    private String headCoach;
    private String foundDate;
}
