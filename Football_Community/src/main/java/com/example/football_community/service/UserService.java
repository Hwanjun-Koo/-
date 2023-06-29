package com.example.football_community.service;

import com.example.football_community.dto.UserDTO;
import com.example.football_community.entity.Profile;
import com.example.football_community.entity.User;
import com.example.football_community.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class UserService {
    private final UserRepository userRepository;
    private final ProfileService profileService;

    @Autowired
    public UserService(UserRepository userRepository, ProfileService profileService){
        this.userRepository = userRepository;
        this.profileService = profileService;
    }

    public User createUser(User user){
        LocalDateTime now = LocalDateTime.now();
        user.setCreatedDate(now);
        user.setModifiedDate(now);
        user.setStatus("Active");
        User savedUser = userRepository.save(user);
        Profile profile = new Profile();
        profile.setUser(savedUser);
        profileService.createProfile(user);
        return savedUser;
    }

    public UserDTO getUser(Long userId){
        Optional<User> userOptional = userRepository.findById(userId);
        if(userOptional.isPresent()) {
            User user = userOptional.get();
            return convertToDTO(user);
        } else {
            throw new RuntimeException("존재하지 않는 유저입니다.");
        }
    }

    public List<UserDTO> getAllUser()
    {
        List<User> users = userRepository.findAll();
        return users.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    public UserDTO updateUser(Long userId, UserDTO userDetails){
        Optional<User> userOptional = userRepository.findById(userId);
        if(userOptional.isPresent()) {
            User user = userOptional.get();

            if(userDetails.getUsername() != null) {
                user.setUsername(userDetails.getUsername());
            }
            if(userDetails.getEmail() != null) {
                user.setEmail(userDetails.getEmail());
            }
            if(userDetails.getPhoneNumber() != null) {
                user.setPhoneNumber(userDetails.getPhoneNumber());
            }

            user.setModifiedDate(LocalDateTime.now());
            User updatedUser = userRepository.save(user);
            return convertToDTO(updatedUser);
        } else {
            throw new RuntimeException("존재하지 않는 유저입니다.");
        }
    }

    public void deleteUser(Long userId){
        Optional<User> userOptional =userRepository.findById(userId);
        if(userOptional.isPresent()) {
            User user = userOptional.get();
            user.setModifiedDate(LocalDateTime.now());
            user.setStatus("Deleted");
            userRepository.delete(user);
        } else {
            throw new RuntimeException("유저를 찾을 수 없습니다.");
        }
    }

    private UserDTO convertToDTO(User user) {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(user.getId());
        userDTO.setUsername(user.getUsername());
        userDTO.setEmail(user.getEmail());
        userDTO.setPhoneNumber(user.getPhoneNumber());
        userDTO.setCreatedDate(user.getCreatedDate());
        userDTO.setModifiedDate(user.getModifiedDate());
        userDTO.setStatus(user.getStatus());
        return userDTO;
    }

}
