package com.ssafy.solomon.api.repository;

import com.ssafy.solomon.api.dto.MemberDto;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class MemberRepositoryTest {
    // db 연동하는 코드..
    // JPA
    static public ArrayList<MemberDto> members;

    static{
        members = new ArrayList<>();
        members.add(new MemberDto("kang", "1234"));
        members.add(new MemberDto("kim", "1234"));
        members.add(new MemberDto("park", "1234"));
    }

    public  MemberDto insertMember(MemberDto member){
        members.add(member);
        return member;
    }

    public List<MemberDto> getAllMembers(){
        return members;
    }

    public MemberDto getMemberByUserId(String memberId){
        return members.stream()
                .filter(memberDto -> memberDto.getMemberId().equals(memberId))
                .findAny()
                .orElse(new MemberDto("", ""));
    }

    public void updateMemberPw(String memberId, MemberDto member){
        members.stream()
                .filter(memberDto -> memberDto.getMemberId().equals(memberId))
                .findAny()
                .orElse(new MemberDto("",""))
                .setMemberPwd(member.getMemberPwd());
    }

    public void deleteMember(String memberId){
        members.removeIf(memberDto -> memberDto.getMemberId().equals(memberId));
    }
}
