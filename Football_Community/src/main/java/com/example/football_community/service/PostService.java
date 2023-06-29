package com.example.football_community.service;

import com.example.football_community.dto.PostDTO;
import com.example.football_community.entity.Post;
import com.example.football_community.entity.User;
import com.example.football_community.repository.PostRepository;
import com.example.football_community.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class PostService {
    private final PostRepository postRepository;
    private final UserRepository userRepository;

    @Autowired
    public PostService(PostRepository postRepository, UserRepository userRepository) {
        this.postRepository = postRepository;
        this.userRepository = userRepository;
    }

    public Post createPost(Long userId, Post post) {
        LocalDateTime now = LocalDateTime.now();
        post.setCreatedDate(now);
        post.setModifiedDate(now);
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("사용자를 찾을 수 없습니다."));
        post.setAuthor(user);
        Post savedPost = postRepository.save(post);
        return savedPost;
    }

    public PostDTO getPost(Long postId) {
        Optional<Post> postOptional = postRepository.findById(postId);
        if (postOptional.isPresent()) {
            Post post = postOptional.get();
            return convertToDTO(post);
        } else {
            throw new RuntimeException("존재하지 않는 게시물입니다.");
        }
    }

    public List<PostDTO> getAllPost(Long userId) {
        User author = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("사용자를 찾을 수 없습니다."));
        List<Post> posts = postRepository.findByAuthor(author);
        return posts.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    public PostDTO updatePost(Long postId, PostDTO postDetails) {
        Optional<Post> postOptional = postRepository.findById(postId);
        if (postOptional.isPresent()) {
            Post post = postOptional.get();

            if(postDetails.getTitle() != null) {
                post.setTitle(postDetails.getTitle());
            }
            if(postDetails.getContent() != null) {
                post.setContent(postDetails.getContent());
            }

            post.setModifiedDate(LocalDateTime.now());
            Post updatedPost = postRepository.save(post);
            return convertToDTO(updatedPost);
        } else {
            throw new RuntimeException("존재하지 않는 게시물입니다.");
        }
    }

    public void deletePost(Long postId) {
        Optional<Post> postOptional = postRepository.findById(postId);
        if (postOptional.isPresent()) {
            Post post = postOptional.get();
            postRepository.delete(post);
        } else {
            throw new RuntimeException("게시물을 찾을 수 없습니다.");
        }
    }

    private PostDTO convertToDTO(Post post) {
        PostDTO postDTO = new PostDTO();
        postDTO.setPost_id(post.getId());
        postDTO.setTitle(post.getTitle());
        postDTO.setContent(post.getContent());
        postDTO.setCreatedDate(post.getCreatedDate());
        postDTO.setModifiedDate(post.getModifiedDate());
        User author = post.getAuthor();
        if(author != null) {
            postDTO.setAuthorName(author.getUsername());
        }

        return postDTO;
    }
}
