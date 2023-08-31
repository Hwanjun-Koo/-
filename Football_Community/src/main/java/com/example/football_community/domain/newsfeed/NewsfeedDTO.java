package com.example.football_community.domain.newsfeed;

import com.example.football_community.domain.matchreview.MatchReviewDTO;
import com.example.football_community.domain.post.PostDTO;

import java.util.ArrayList;
import java.util.List;

public class NewsfeedDTO {
    private List<MatchReviewDTO> matchReviewsDTO;
    private List<PostDTO> postsDTO;

    public NewsfeedDTO() {
        this.matchReviewsDTO = new ArrayList<>();
        this.postsDTO = new ArrayList<>();
    }


    public List<MatchReviewDTO> getMatchReviewsDTO() {
        return matchReviewsDTO;
    }

    public void setMatchReviewsDTO(List<MatchReviewDTO> matchReviewsDTO) {
        this.matchReviewsDTO = matchReviewsDTO;
    }

    public List<PostDTO> getPostsDTO() {
        return postsDTO;
    }

    public void setPostsDTO(List<PostDTO> postsDTO) {
        this.postsDTO = postsDTO;
    }
    public void addMatchReviewDTO(MatchReviewDTO matchReviewDTO) {
        matchReviewsDTO.add(matchReviewDTO);
    }

    public void removeMatchReviewDTO(MatchReviewDTO matchReviewDTO) {
        matchReviewsDTO.remove(matchReviewDTO);
    }

    public void addPostDTO(PostDTO postDTO) {
        postsDTO.add(postDTO);
    }

    public void removePostDTO(PostDTO postDTO) {
        postsDTO.remove(postDTO);
    }
}
