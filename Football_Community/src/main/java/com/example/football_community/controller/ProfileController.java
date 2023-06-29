package com.example.football_community.controller;

import com.example.football_community.dto.ProfileDTO;
import com.example.football_community.service.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/football-community/profile")
@RestController
public class ProfileController {

    private final ProfileService profileService;

    @Autowired
    public ProfileController(ProfileService profileService) {
        this.profileService = profileService;
    }

    @GetMapping("/{userId}")
    public ResponseEntity<ProfileDTO> getProfile(
            @PathVariable Long userId
    ) {
        ProfileDTO profileDTO = profileService.getProfile(userId);
        return ResponseEntity.ok(profileDTO);
    }

    @PutMapping("/edit/{profileId}")
    public ResponseEntity<ProfileDTO> updateProfile(
            @PathVariable Long profileId,
            @RequestBody ProfileDTO profileDTO
    ) {
        ProfileDTO updatedProfile = profileService.updateProfile(profileId, profileDTO);
        return ResponseEntity.ok(updatedProfile);
    }
}

