package com.example.football_community.domain.newsfeed;

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
