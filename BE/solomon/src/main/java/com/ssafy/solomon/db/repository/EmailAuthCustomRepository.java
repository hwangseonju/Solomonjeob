package com.ssafy.solomon.db.repository;

import com.ssafy.solomon.db.entity.EmailAuthEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Optional;

@Repository
public interface EmailAuthCustomRepository extends JpaRepository<EmailAuthEntity, Long> {
    Optional<EmailAuthEntity> findValidAuthByEmail(String email, String authToken, LocalDateTime current);
}
