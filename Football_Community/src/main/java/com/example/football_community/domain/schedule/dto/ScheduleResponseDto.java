package com.example.football_community.domain.schedule.dto;

import com.example.football_community.domain.match.dto.response.MatchInfoResponseDto;
import com.example.football_community.domain.match.entity.Match;
import com.example.football_community.domain.team.entity.Team;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class ScheduleResponseDto {
    private List<MatchInfoResponseDto> matchInfos;
}
