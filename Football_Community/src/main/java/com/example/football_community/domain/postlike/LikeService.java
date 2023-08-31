package com.example.football_community.domain.postlike;

import com.example.football_community.domain.post.Post;
import com.example.football_community.domain.member.Member;
import com.example.football_community.domain.post.PostRepository;
import com.example.football_community.domain.member.MemberRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class LikeService {
    private final LikeRepository likeRepository;
    private final MemberRepository memberRepository;
    private final PostRepository postRepository;

    @Autowired
    public LikeService(LikeRepository likeRepository, MemberRepository memberRepository, PostRepository postRepository) {
        this.likeRepository = likeRepository;
        this.memberRepository = memberRepository;
        this.postRepository = postRepository;
    }


    public void likePost(Long userId, Long postId) {
        Member member = memberRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("유저를 찾을 수 없습니다."));
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new RuntimeException("게시물을 찾을 수 없습니다."));

        Like like = likeRepository.findByUserAndPost(member, post);
        if (like == null) {
            like = new Like(member, post);
            likeRepository.save(like);
            member.addLike(like);
            post.incrementLikesCount();
        }
    }

    public void unlikePost(Long userId, Long postId) {
        Member member = memberRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("유저를 찾을 수 없습니다."));
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new RuntimeException("게시물을 찾을 수 없습니다."));

        Like like = likeRepository.findByUserAndPost(member, post);
        if (like != null) {
            likeRepository.delete(like);
            member.removeLike(like);
            post.decrementLikesCount();
        }
    }
}
