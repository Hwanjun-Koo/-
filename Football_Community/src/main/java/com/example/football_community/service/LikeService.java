package com.example.football_community.service;

import com.example.football_community.entity.Follow;
import com.example.football_community.entity.Like;
import com.example.football_community.entity.Post;
import com.example.football_community.entity.User;
import com.example.football_community.repository.LikeRepository;
import com.example.football_community.repository.PostRepository;
import com.example.football_community.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class LikeService {
    private final LikeRepository likeRepository;
    private final UserRepository userRepository;
    private final PostRepository postRepository;

    @Autowired
    public LikeService(LikeRepository likeRepository, UserRepository userRepository, PostRepository postRepository) {
        this.likeRepository = likeRepository;
        this.userRepository = userRepository;
        this.postRepository = postRepository;
    }


    public void likePost(Long userId, Long postId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("유저를 찾을 수 없습니다."));
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new RuntimeException("게시물을 찾을 수 없습니다."));

        Like like = likeRepository.findByUserAndPost(user, post);
        if (like == null) {
            like = new Like(user, post);
            likeRepository.save(like);
            user.addLike(like);
            post.incrementLikesCount();
        }
    }

    public void unlikePost(Long userId, Long postId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("유저를 찾을 수 없습니다."));
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new RuntimeException("게시물을 찾을 수 없습니다."));

        Like like = likeRepository.findByUserAndPost(user, post);
        if (like != null) {
            likeRepository.delete(like);
            user.removeLike(like);
            post.decrementLikesCount();
        }
    }
}
