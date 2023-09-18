package com.example.football_community.domain.report.controller;

import com.example.football_community.domain.member.security.UserDetailsImpl;
import com.example.football_community.domain.report.dto.request.ReportRequestDto;
import com.example.football_community.domain.report.service.ReportService;
import com.example.football_community.global.message.ResponseMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ReportController {
    private final ReportService reportService;

    @PostMapping("/post/{postId}/report")
    public ResponseEntity reportPost(
            @PathVariable Long postId,
            @RequestBody ReportRequestDto requestDto,
            @AuthenticationPrincipal UserDetailsImpl userDetails
    ) {
        reportService.reportPost(postId, requestDto, userDetails);
        return ResponseMessage.SuccessResponse("신고가 접수되었습니다.", "");
    }

    @PostMapping("/comment/{commentId}/report")
    public ResponseEntity reportComment(
            @PathVariable Long commentId,
            @RequestBody ReportRequestDto requestDto,
            @AuthenticationPrincipal UserDetailsImpl userDetails
    ) {
        reportService.reportComment(commentId, requestDto, userDetails);
        return ResponseMessage.SuccessResponse("신고가 접수되었습니다.", "");
    }
}
