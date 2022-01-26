package com.ssafy.solomon.db.repository;

import com.ssafy.solomon.db.entity.EmailAuthEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmailAuthRepository extends JpaRepository<EmailAuthEntity, Long>, EmailAuthCustomRepository {
}
