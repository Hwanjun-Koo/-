package com.example.football_community.domain.post.service;

import com.example.football_community.domain.member.entity.Member;
import com.example.football_community.domain.member.security.MemberIsLoginService;
import com.example.football_community.domain.member.security.UserDetailsImpl;
import com.example.football_community.domain.post.dto.request.PostUpdateRequestDto;
import com.example.football_community.domain.post.dto.request.PostUploadRequestDto;
import com.example.football_community.domain.post.dto.response.PostDetailsResponseDto;
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
public class PostService {
    private final PostRepository postRepository;
    private final MemberIsLoginService memberIsLoginService;

    @Transactional
    public void createPost(PostUploadRequestDto requestDto, UserDetailsImpl userDetails) {
        Member member = memberIsLoginService.isLogin(userDetails);
        Post post = Post.builder()
                .member(member)
                .title(requestDto.getTitle())
                .content(requestDto.getContent())
                .build();
        postRepository.save(post);
    }

    @Transactional
    public List<PostDetailsResponseDto> myPosts(UserDetailsImpl userDetails) {
        Member member = memberIsLoginService.isLogin(userDetails);
        List<Post> posts = postRepository.findByMember(member);
        List<PostDetailsResponseDto> dtos = posts.stream()
                .map(PostDetailsResponseDto::of)
                .collect(Collectors.toList());
        return dtos;
    }

    @Transactional
    public void updatePost(Long postId, PostUpdateRequestDto requestDto, UserDetailsImpl userDetails) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new GlobalException(GlobalErrorCode.POST_NOT_FOUND));
        Long currentMemberId = userDetails.getMember().getMemberId();

        // 게시글 작성자 정보 가져오기
        Long postAuthorId = post.getMember().getMemberId();

        if (currentMemberId.equals(postAuthorId)) {
            post.updatePost(requestDto);
        } else {
            throw new GlobalException(GlobalErrorCode.ACCESS_DENIED);
        }
    }

    @Transactional
    public void deletePost(Long postId, UserDetailsImpl userDetails) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new GlobalException(GlobalErrorCode.POST_NOT_FOUND));
        Long currentMemberId = userDetails.getMember().getMemberId();

        // 게시글 작성자 정보 가져오기
        Long postAuthorId = post.getMember().getMemberId();

        if (currentMemberId.equals(postAuthorId)) {
            postRepository.delete(post);
        } else {
            throw new GlobalException(GlobalErrorCode.ACCESS_DENIED);
        }
    }
}
