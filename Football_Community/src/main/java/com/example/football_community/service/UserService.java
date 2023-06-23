package com.example.football_community.service;

import com.example.football_community.entity.User;
import com.example.football_community.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    public User createUser(User user){
        LocalDateTime now = LocalDateTime.now();
        user.setCreatedDate(now);
        user.setModifiedDate(now);
        user.setStatus("Active");
        return userRepository.save(user);
    }

    public User getUser(Long userId){
        Optional<User> user = userRepository.findById(userId);
        if(user.isPresent()) {
            return user.get();
        } else {
            throw new RuntimeException("존재하지 않는 유저입니다.");
        }
    }

    public List<User> getAllUser() {
        return userRepository.findAll();
    }

    public User updateUser(Long user_id, User userDetails){
        Optional<User> userOptional = userRepository.findById(user_id);
        if(userOptional.isPresent()) {
            User user = userOptional.get();

            if(userDetails.getUsername() != null) {
                user.setUsername(userDetails.getUsername());
            }
            if(userDetails.getEmail() != null) {
                user.setEmail(userDetails.getEmail());
            }
            if(userDetails.getPassword() != null) {
                user.setPassword(userDetails.getPassword());
            }
            if(userDetails.getPhoneNumber() != null) {
                user.setPhoneNumber(userDetails.getPhoneNumber());
            }

            user.setModifiedDate(LocalDateTime.now());
            return user;
        } else {
            throw new RuntimeException("존재하지 않는 유저입니다.");
        }
    }

    public void deleteUser(Long user_id){
        Optional<User> userOptional =userRepository.findById(user_id);
        if(userOptional.isPresent()) {
            User user = userOptional.get();
            user.setModifiedDate(LocalDateTime.now());
            user.setStatus("Deleted");
            userRepository.save(user);
            userRepository.delete(user);
        } else {
            throw new RuntimeException("유저를 찾을 수 없습니다.");
        }
    }

}
