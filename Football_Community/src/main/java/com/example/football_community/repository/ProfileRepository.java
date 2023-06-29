package com.example.football_community.repository;

import com.example.football_community.entity.Profile;
import com.example.football_community.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfileRepository extends JpaRepository<Profile, Long> {
    Profile findByUser(User user);
}
