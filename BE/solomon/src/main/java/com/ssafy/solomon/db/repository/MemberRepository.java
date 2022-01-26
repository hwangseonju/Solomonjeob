package com.ssafy.solomon.db.repository;

import com.ssafy.solomon.db.entity.MemberEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.swing.text.html.Option;
import java.util.Optional;

@Repository
public interface MemberRepository extends JpaRepository<MemberEntity, Long> {
    // select idx from where id="id"
    MemberEntity findByMemberId(String memberId);
    // select idx from member where key="key" and sns="sns'
    MemberEntity findByMemberKey(String memberKey);
}
