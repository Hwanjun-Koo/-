package com.example.football_community.domain.member.repository;

import com.example.football_community.domain.member.security.EmailCode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EmailCodeRepository extends JpaRepository<EmailCode,Long> {
    Optional<EmailCode> findByEmail(String email);

    Optional<EmailCode> findByEmailAndCode(String email, String code);
}
