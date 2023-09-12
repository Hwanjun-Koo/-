package com.example.football_community.domain.postlike.service;

import com.example.football_community.domain.member.entity.Member;
import com.example.football_community.domain.member.security.MemberIsLoginService;
import com.example.football_community.domain.member.security.UserDetailsImpl;
import com.example.football_community.domain.post.entity.Post;
import com.example.football_community.domain.post.repository.PostRepository;
import com.example.football_community.domain.postlike.entity.PostLike;
import com.example.football_community.domain.postlike.repository.PostLikeRepository;
import com.example.football_community.global.exception.GlobalErrorCode;
import com.example.football_community.global.exception.GlobalException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PostLikeService {
    private final PostLikeRepository postLikeRepository;
    private final PostRepository postRepository;
    private final MemberIsLoginService memberIsLoginService;

    @Transactional
    public void likePost(Long postId, UserDetailsImpl userDetails) {
        Member member = memberIsLoginService.isLogin(userDetails);

        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new GlobalException(GlobalErrorCode.POST_NOT_FOUND));

        Optional<PostLike> postLikeOptional = postLikeRepository.findByMemberAndPost(member, post);
        if(postLikeOptional.isPresent()){
            PostLike postLike = postLikeOptional.get();
            post.removeLike();
            postLikeRepository.delete(postLike);
        } else {
            PostLike postLike = PostLike.builder()
                    .member(member)
                    .post(post)
                    .build();
            post.addLike();
            postLikeRepository.save(postLike);
        }
    }

    @Transactional
    public boolean isLiked(Long postId, UserDetailsImpl userDetails){
        Member member = memberIsLoginService.isLogin(userDetails);

        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new GlobalException(GlobalErrorCode.POST_NOT_FOUND));

        Optional<PostLike> postLikeOptional = postLikeRepository.findByMemberAndPost(member, post);
        if(postLikeOptional.isPresent()){
            return true;
        } else {
            return false;
        }
    }

}
