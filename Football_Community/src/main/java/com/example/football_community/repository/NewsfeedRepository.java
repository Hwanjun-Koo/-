package com.example.football_community.repository;

import com.example.football_community.entity.Newsfeed;
import com.example.football_community.entity.User;
import jdk.jfr.Registered;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface NewsfeedRepository extends JpaRepository<Newsfeed, Long> {

    Newsfeed findByUser(User user);
}
