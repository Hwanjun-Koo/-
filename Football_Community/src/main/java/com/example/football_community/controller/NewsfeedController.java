package com.example.football_community.controller;

import com.example.football_community.dto.NewsfeedDTO;
import com.example.football_community.service.NewsfeedService;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/football-community/newsfeed")
@RestController
public class NewsfeedController {
    private NewsfeedService newsfeedService;

    public NewsfeedController(NewsfeedService newsfeedService) {
        this.newsfeedService = newsfeedService;
    }


    @GetMapping("/show/{userId}")
    public NewsfeedDTO getNewsfeed( @PathVariable Long userId){
        NewsfeedDTO newsfeedDTO = newsfeedService.getNewsfeed(userId);
        return newsfeedDTO;
    }
}
