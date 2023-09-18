package com.example.football_community.domain.comment.service;

import com.example.football_community.domain.comment.dto.request.CommentRegisterRequestDto;
import com.example.football_community.domain.comment.dto.request.CommentUpdateRequestDto;
import com.example.football_community.domain.comment.dto.response.CommentDetailsResponseDto;
import com.example.football_community.domain.comment.entity.Comment;
import com.example.football_community.domain.comment.repository.CommentRepository;
import com.example.football_community.domain.member.entity.Member;
import com.example.football_community.domain.member.security.MemberIsLoginService;
import com.example.football_community.domain.member.security.UserDetailsImpl;
import com.example.football_community.domain.post.entity.Post;
import com.example.football_community.domain.post.repository.PostRepository;
import com.example.football_community.global.exception.GlobalErrorCode;
import com.example.football_community.global.exception.GlobalException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;
    private final PostRepository postRepository;
    private final MemberIsLoginService memberIsLoginService;

    @Transactional
    public void createComment(CommentRegisterRequestDto requestDto, UserDetailsImpl userDetails) {
        Member member = memberIsLoginService.isLogin(userDetails);
        Post post = postRepository.findById(requestDto.getPostId())
                .orElseThrow(() -> new GlobalException(GlobalErrorCode.POST_NOT_FOUND));

        Comment comment = Comment.builder()
                .member(member)
                .post(post)
                .content(requestDto.getContent())
                .build();

        post.addComment(comment);
        commentRepository.save(comment);
    }

    @Transactional
    public List<CommentDetailsResponseDto> myComments(UserDetailsImpl userDetails) {
        Member member = memberIsLoginService.isLogin(userDetails);
        List<Comment> comments = commentRepository.findByMember(member);
        List<CommentDetailsResponseDto> dtos = comments.stream()
                .map(CommentDetailsResponseDto::of)
                .collect(Collectors.toList());
        return dtos;
    }

    @Transactional
    public void updateComment(Long commentId, CommentUpdateRequestDto requestDto, UserDetailsImpl userDetails) {
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new GlobalException(GlobalErrorCode.COMMENT_NOT_FOUND));
        Long currentMemberId = userDetails.getMember().getMemberId();

        Long commentAuthorId = comment.getMember().getMemberId();

        if (currentMemberId.equals(commentAuthorId)) {
            comment.updateComment(requestDto);
        } else {
            throw new GlobalException(GlobalErrorCode.ACCESS_DENIED);
        }
    }

    @Transactional
    public void deleteComment(Long commentId, UserDetailsImpl userDetails) {
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new GlobalException(GlobalErrorCode.COMMENT_NOT_FOUND));
        Long currentMemberId = userDetails.getMember().getMemberId();

        Long commentAuthorId = comment.getMember().getMemberId();

        if (currentMemberId.equals(commentAuthorId)) {
            comment.getPost().removeComment(comment);
            commentRepository.delete(comment);
        } else {
            throw new GlobalException(GlobalErrorCode.ACCESS_DENIED);
        }
    }
}
