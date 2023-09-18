package com.example.football_community.domain.report.service;

import com.example.football_community.domain.comment.entity.Comment;
import com.example.football_community.domain.comment.repository.CommentRepository;
import com.example.football_community.domain.member.entity.Member;
import com.example.football_community.domain.member.security.MemberIsLoginService;
import com.example.football_community.domain.member.security.UserDetailsImpl;
import com.example.football_community.domain.post.entity.Post;
import com.example.football_community.domain.post.repository.PostRepository;
import com.example.football_community.domain.report.dto.request.ReportRequestDto;
import com.example.football_community.domain.report.entity.Report;
import com.example.football_community.domain.report.repository.ReportRepository;
import com.example.football_community.global.exception.GlobalErrorCode;
import com.example.football_community.global.exception.GlobalException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ReportService {
    private final ReportRepository reportRepository;
    private final PostRepository postRepository;
    private final CommentRepository commentRepository;
    private final MemberIsLoginService memberIsLoginService;

    @Transactional
    public void reportPost(Long postId, ReportRequestDto request, UserDetailsImpl userDetails) {
        Member member = memberIsLoginService.isLogin(userDetails);
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new GlobalException(GlobalErrorCode.POST_NOT_FOUND));

        Optional<Report> reportOptional = reportRepository.findByMemberAndTargetPost(member, post);
        if (reportOptional.isPresent()) {
            throw new GlobalException(GlobalErrorCode.DUPLICATE_REPORT);
        } else {
            reportRepository.save(Report.builder()
                    .member(member)
                    .reportType(request.getReportType())
                    .targetPost(post)
                    .build());
            post.addReport();
        }
    }

    @Transactional
    public void reportComment(Long commentId, ReportRequestDto request, UserDetailsImpl userDetails) {
        Member member = memberIsLoginService.isLogin(userDetails);
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new GlobalException(GlobalErrorCode.POST_NOT_FOUND));

        Optional<Report> reportOptional = reportRepository.findByMemberAndTargetComment(member, comment);
        if (reportOptional.isPresent()) {
            throw new GlobalException(GlobalErrorCode.DUPLICATE_REPORT);
        } else {
            reportRepository.save(Report.builder()
                    .member(member)
                    .reportType(request.getReportType())
                    .targetComment(comment)
                    .build());
            comment.addReport();
        }
    }
}
