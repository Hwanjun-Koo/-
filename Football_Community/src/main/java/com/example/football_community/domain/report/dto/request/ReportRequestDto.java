package com.example.football_community.domain.report.dto.request;

import com.example.football_community.domain.report.entity.ReportType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class ReportRequestDto {
    private ReportType reportType;
}
