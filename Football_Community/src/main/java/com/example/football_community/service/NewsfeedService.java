package com.example.football_community.service;

import com.example.football_community.dto.MatchReviewDTO;
import com.example.football_community.dto.NewsfeedDTO;
import com.example.football_community.dto.PostDTO;
import com.example.football_community.entity.MatchReview;
import com.example.football_community.entity.Newsfeed;
import com.example.football_community.entity.Post;
import com.example.football_community.entity.User;
import com.example.football_community.repository.MatchReviewRepository;
import com.example.football_community.repository.NewsfeedRepository;
import com.example.football_community.repository.PostRepository;
import com.example.football_community.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class NewsfeedService {
    private final NewsfeedRepository newsfeedRepository;
    private final MatchReviewRepository matchReviewRepository;
    private final PostRepository postRepository;
    private final UserRepository userRepository;

    @Autowired
    public NewsfeedService(NewsfeedRepository newsfeedRepository, MatchReviewRepository matchReviewRepository, PostRepository postRepository, UserRepository userRepository) {
        this.newsfeedRepository = newsfeedRepository;
        this.matchReviewRepository = matchReviewRepository;
        this.postRepository = postRepository;
        this.userRepository = userRepository;
    }

    public NewsfeedDTO createNewsfeed(Long userId) {
        Optional<User> userOptional = userRepository.findById(userId);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            Newsfeed newsfeed= new Newsfeed();
            newsfeed.setUser(user);
            Newsfeed savedNewsfeed = newsfeedRepository.save(newsfeed);
            return convertToDTO(savedNewsfeed);
        } else {
            throw new RuntimeException("유저를 찾을 수 없습니다.");
        }
    }

    public NewsfeedDTO getNewsfeed(Long userId) {
        Newsfeed newsfeed = newsfeedRepository.findByUserId(userId);
        return convertToDTO(newsfeed);
    }

    private MatchReviewDTO convertToMatchReviewDTO(MatchReview review) {
        MatchReviewDTO reviewDTO = new MatchReviewDTO();
        reviewDTO.setId(review.getId());
        reviewDTO.setTitle(review.getTitle());
        reviewDTO.setContent(review.getContent());
        User author = review.getAuthor();
        if (author != null) {
            reviewDTO.setAuthor(author.getUsername());
        }

        return reviewDTO;
    }

    private PostDTO convertToPostDTO(Post post) {
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

    private NewsfeedDTO convertToDTO(Newsfeed newsfeed) {
        NewsfeedDTO newsfeedDTO = new NewsfeedDTO();
        newsfeedDTO.setId(newsfeed.getId());
        for (MatchReview matchReview : matchReviewRepository.findAll()) {
            MatchReviewDTO matchReviewDTO = convertToMatchReviewDTO(matchReview);
            newsfeedDTO.addMatchReviewDTO(matchReviewDTO);
        }
        for (Post post : postRepository.findAll()) {
            PostDTO postDTO = convertToPostDTO(post);
            newsfeedDTO.addPostDTO(postDTO);
        }
        return newsfeedDTO;
    }

}
