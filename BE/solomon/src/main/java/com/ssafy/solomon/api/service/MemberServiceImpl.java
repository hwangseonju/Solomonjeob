package com.ssafy.solomon.api.service;

import com.ssafy.solomon.api.dto.MemberDto;
import com.ssafy.solomon.db.entity.MemberEntity;
import com.ssafy.solomon.db.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.SQLException;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService{

    private final MemberRepository memberRepository;

    @Override
    public MemberEntity insertMember(MemberDto memberDto) throws SQLException {
        MemberEntity member = memberDto.toEntity();
        memberRepository.save(member);
        return memberRepository.findByMemberId(member.getMemberId());
    }
}
