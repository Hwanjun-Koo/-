package com.example.football_community.domain.newsfeed.controller;

import com.example.football_community.domain.newsfeed.service.NewsfeedService;
import com.example.football_community.global.message.ResponseMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/newsfeed")
@RequiredArgsConstructor
public class NewsfeedController {
    private final NewsfeedService newsfeedService;

    @GetMapping()
    public ResponseEntity getPosts(
            @RequestParam("page") int page,
            @RequestParam("size") int size
    ) {
        return ResponseMessage.SuccessResponse("게시글 불러오기 완료", newsfeedService.getPosts(page - 1, size));
    }
}
