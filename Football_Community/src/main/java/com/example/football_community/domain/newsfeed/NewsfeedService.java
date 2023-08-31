package com.example.football_community.domain.newsfeed;

import com.example.football_community.domain.matchreview.MatchReviewDTO;
import com.example.football_community.domain.post.PostDTO;
import com.example.football_community.domain.matchreview.MatchReview;
import com.example.football_community.domain.post.Post;
import com.example.football_community.domain.member.entity.Member;
import com.example.football_community.domain.matchreview.MatchReviewRepository;
import com.example.football_community.domain.post.PostRepository;
import com.example.football_community.domain.member.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class NewsfeedService {
    private final NewsfeedRepository newsfeedRepository;
    private final MatchReviewRepository matchReviewRepository;
    private final PostRepository postRepository;
    private final MemberRepository memberRepository;

    @Autowired
    public NewsfeedService(NewsfeedRepository newsfeedRepository, MatchReviewRepository matchReviewRepository, PostRepository postRepository, MemberRepository memberRepository) {
        this.newsfeedRepository = newsfeedRepository;
        this.matchReviewRepository = matchReviewRepository;
        this.postRepository = postRepository;
        this.memberRepository = memberRepository;
    }

    public NewsfeedDTO getNewsfeed(Long userId) {
        Member member = memberRepository.findById(userId).orElseThrow(() -> new RuntimeException("사용자를 찾을 수 없습니다."));
        Newsfeed newsfeed = newsfeedRepository.findByUser(member);
        return convertToDTO(newsfeed);
    }

    private MatchReviewDTO convertToMatchReviewDTO(MatchReview review) {
        MatchReviewDTO reviewDTO = new MatchReviewDTO();
        reviewDTO.setId(review.getId());
        reviewDTO.setTitle(review.getTitle());
        reviewDTO.setContent(review.getContent());
        Member author = review.getAuthor();
        if (author != null) {
            reviewDTO.setAuthor(author.getMemberName());
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
        Member author = post.getAuthor();
        if(author != null) {
            postDTO.setAuthorName(author.getMemberName());
        }

        return postDTO;
    }

    private NewsfeedDTO convertToDTO(Newsfeed newsfeed) {
        NewsfeedDTO newsfeedDTO = new NewsfeedDTO();
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
