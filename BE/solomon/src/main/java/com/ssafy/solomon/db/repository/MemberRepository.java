package com.ssafy.solomon.db.repository;

import com.ssafy.solomon.db.entity.MemberEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberRepository extends JpaRepository<MemberEntity, Long> {

    public MemberEntity findByMemberId(String memberId);
}
