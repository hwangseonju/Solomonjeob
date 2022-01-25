package com.ssafy.solomon.api.service;

import com.ssafy.solomon.api.dto.MemberDto;
import com.ssafy.solomon.api.repository.MemberRepositoryTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Member;
import java.util.List;

@Service
public class MemberServiceImpl implements MemberService{
    @Autowired
    MemberRepositoryTest memberRepository;

    @Override
    public MemberDto signin(String memberId, String memberPwd) throws Exception {
        if(memberId.equals("kang") && memberPwd.equals("1234")){
            return new MemberDto(memberId, memberPwd);
        }else{
            throw new RuntimeException("그런 사람은 없습니다.");
        }
    }

    @Override
    public String getServerInfo() throws Exception {
        return "정보전달할것";
    }

    @Override
    public MemberDto insertMember(MemberDto member) throws Exception{
        return memberRepository.insertMember(member);
    }

    @Override
    public List<MemberDto> getAllMembers() throws Exception{
        return memberRepository.getAllMembers();
    }

    @Override
    public MemberDto getMemberByUserId(String memberId) throws Exception{
        return memberRepository.getMemberByUserId(memberId);
    }

    @Override
    public void updateMemberPw(String memberId, MemberDto member) throws Exception{
        memberRepository.updateMemberPw(memberId, member);
    }

    @Override
    public void deleteMember(String memberId) throws Exception{
        memberRepository.deleteMember(memberId);
    }


}
