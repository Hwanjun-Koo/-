package com.example.football_community.domain.profile;

import com.example.football_community.domain.team.Team;
import com.example.football_community.domain.member.Member;
import com.example.football_community.domain.team.TeamRepository;
import com.example.football_community.domain.member.MemberRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Transactional
public class ProfileService {

    private final ProfileRepository profileRepository;
    private final MemberRepository memberRepository;
    private final TeamRepository teamRepository;

    @Autowired
    public ProfileService(ProfileRepository profileRepository, MemberRepository memberRepository, TeamRepository teamRepository) {
        this.profileRepository = profileRepository;
        this.memberRepository = memberRepository;
        this.teamRepository = teamRepository;
    }


    public ProfileDTO getProfile(Long userId){
        Member member = memberRepository.findById(userId).orElseThrow(() -> new RuntimeException("사용자를 찾을 수 없습니다."));
        Profile profile = profileRepository.findByUser(member);
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
