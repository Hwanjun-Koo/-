package com.example.football_community.domain.profile;

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
    public ResponseEntity<String> updateProfile(
            @PathVariable Long profileId,
            @RequestBody ProfileDTO profileDTO
    ) {
        ProfileDTO updatedProfile = profileService.updateProfile(profileId, profileDTO);
        String message = updatedProfile.getName() + "님의 프로필이 수정되었습니다.";
        return ResponseEntity.ok(message + "\n" + updatedProfile.toString());
    }
}

