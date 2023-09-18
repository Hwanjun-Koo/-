package com.example.football_community.domain.report.dto.response;

import com.example.football_community.domain.report.entity.Report;
import com.example.football_community.domain.report.entity.ReportType;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class ReportResponseDto {
    private Long reportId;
    private Long memberId;
    private ReportType reportType;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createdAt;

    public static ReportResponseDto from(Report report) {
        return ReportResponseDto.builder()
                .reportId(report.getReportId())
                .memberId(report.getMember().getMemberId())
                .reportType(report.getReportType())
                .createdAt(report.getCreatedAt())
                .build();
    }
}
