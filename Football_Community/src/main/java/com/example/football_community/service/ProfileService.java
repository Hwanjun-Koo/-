package com.example.football_community.service;

import com.example.football_community.dto.ProfileDTO;
import com.example.football_community.entity.Profile;
import com.example.football_community.entity.Team;
import com.example.football_community.entity.User;
import com.example.football_community.repository.ProfileRepository;
import com.example.football_community.repository.TeamRepository;
import com.example.football_community.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Transactional
public class ProfileService {

    private final ProfileRepository profileRepository;
    private final UserRepository userRepository;
    private final TeamRepository teamRepository;

    @Autowired
    public ProfileService(ProfileRepository profileRepository, UserRepository userRepository, TeamRepository teamRepository) {
        this.profileRepository = profileRepository;
        this.userRepository = userRepository;
        this.teamRepository = teamRepository;
    }


    public ProfileDTO getProfile(Long userId){
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("사용자를 찾을 수 없습니다."));
        Profile profile = profileRepository.findByUser(user);
        return convertToDTO(profile);
    }

    public ProfileDTO updateProfile(Long profileId, ProfileDTO profileDetails) {
        Optional<Profile> profileOptional = profileRepository.findById(profileId);

        if (profileOptional.isPresent()) {
            Profile profile = profileOptional.get();

            if (profileDetails.getName() != null) {
                profile.setName(profileDetails.getName());
            }
            if (profileDetails.getGender() != null) {
                profile.setGender(profileDetails.getGender());
            }
            if (profileDetails.getAge() != null) {
                profile.setAge(profileDetails.getAge());
            }
            if (profileDetails.getBio() != null) {
                profile.setBio(profileDetails.getBio());
            }
            if (profileDetails.getFavTeamName() != null) {
                String favTeamName = profileDetails.getFavTeamName();
                if (profile.getFavTeam() == null || !profile.getFavTeam().getTeamName().equals(favTeamName)) {
                    Team favTeam = teamRepository.findByTeamName(favTeamName)
                            .orElseThrow(() -> new RuntimeException("선호하는 팀을 찾을 수 없습니다."));
                    profile.setFavTeam(favTeam);
                }
            }

            Profile updatedProfile = profileRepository.save(profile);
            return convertToDTO(updatedProfile);
        } else {
            throw new RuntimeException("존재하지 않는 프로필입니다.");
        }
    }

    private ProfileDTO convertToDTO(Profile profile) {
        ProfileDTO profileDTO = new ProfileDTO();
        profileDTO.setProfile_id(profile.getProfile_id());
        profileDTO.setName(profile.getName());
        profileDTO.setGender(profile.getGender());
        profileDTO.setAge(profile.getAge());
        profileDTO.setBio(profile.getBio());
        profileDTO.setFollowerCount(profile.getFollowersCount());
        profileDTO.setFollowingCount(profile.getFollowingsCount());
        Team favTeam = profile.getFavTeam();
        if (favTeam != null) {
            profileDTO.setFavTeamName(favTeam.getTeamName());
        }

        return profileDTO;
    }
}
